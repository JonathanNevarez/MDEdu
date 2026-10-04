// Test-only TLS termination on loopback. Not part of the production image.
const https = require('node:https');
const http = require('node:http');
const fs = require('node:fs');
const [cert, key] = process.argv.slice(2);
https.createServer({cert:fs.readFileSync(cert),key:fs.readFileSync(key)}, (req,res) => {
  const headers = {...req.headers, 'x-forwarded-proto':'https', 'x-forwarded-host':req.headers.host,
    'x-forwarded-port':'8443', 'x-forwarded-for':'127.0.0.1'};
  const upstream = http.request({hostname:'127.0.0.1',port:8089,path:req.url,method:req.method,headers}, response => {
    res.writeHead(response.statusCode,response.headers);response.pipe(res);
  });
  upstream.on('error',()=>{res.writeHead(503);res.end('Test upstream unavailable');});
  req.pipe(upstream);
}).listen(8443,'127.0.0.1',()=>console.log('Local test TLS proxy ready'));
