package com.project.shared.security;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.*;
import java.util.*;
/** Retains legacy domain diagnostics while supplying the common HTTP error envelope. */
@ControllerAdvice
public class ErrorEnvelopeAdvice implements ResponseBodyAdvice<Object> {
 private final ObjectMapper json;public ErrorEnvelopeAdvice(ObjectMapper json){this.json=json;}
 public boolean supports(MethodParameter method,Class<? extends HttpMessageConverter<?>> converter){return org.springframework.http.converter.json.MappingJackson2HttpMessageConverter.class.isAssignableFrom(converter);}
 public Object beforeBodyWrite(Object body,MethodParameter method,MediaType type,Class<? extends HttpMessageConverter<?>> converter,ServerHttpRequest request,ServerHttpResponse response){
  if(!(response instanceof ServletServerHttpResponse servlet)||servlet.getServletResponse().getStatus()<400)return body;
  var result=new LinkedHashMap<String,Object>(ApiErrors.body(servlet.getServletResponse().getStatus()));
  if(body!=null){var tree=json.valueToTree(body);if(tree.isObject())tree.fields().forEachRemaining(e->{if(!Set.of("timestamp","requestId").contains(e.getKey()))result.put(e.getKey(),json.convertValue(e.getValue(),Object.class));});}
  result.put("httpStatus",servlet.getServletResponse().getStatus());
  // A JSON tree prevents a legacy Map<String,String> return type from forcing
  // numeric HTTP status fields through a String serializer.
  return json.valueToTree(result);
 }
}
