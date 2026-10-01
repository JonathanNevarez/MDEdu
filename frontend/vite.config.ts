import react from '@vitejs/plugin-react';
import { defineConfig } from 'vite';
import { readFileSync, readdirSync } from 'node:fs';

const mediaDir = new URL('./node_modules/blockly/media/', import.meta.url);
const media = new Map(readdirSync(mediaDir).map(name => [name, readFileSync(new URL(name, mediaDir))]));

export default defineConfig({
  plugins: [react(), {
    name: 'blockly-local-media',
    configureServer(server) {
      server.middlewares.use('/blockly-media', (req, res, next) => {
        const name = (req.url ?? '').replace(/^\//, '').split('?')[0] ?? '';
        const source = media.get(name);
        if (!source) { next(); return; }
        if (name.endsWith('.svg')) res.setHeader('Content-Type', 'image/svg+xml');
        res.end(source);
      });
    },
    generateBundle() {
      for (const [name, source] of media) this.emitFile({ type: 'asset', fileName: `blockly-media/${name}`, source });
    },
  }],
});
