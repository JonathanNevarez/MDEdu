package com.project.shared.security;
import java.time.Instant;
import java.util.*;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.MDC;
public final class ApiErrors {
 private ApiErrors() {}
 public static Map<String,Object> body(int status) {
  String code=switch(status){case 400,405,413,415,422->"VALIDATION_ERROR";case 401->"UNAUTHORIZED";case 403->"FORBIDDEN";case 404->"RESOURCE_NOT_FOUND";case 409->"CONFLICT";case 429->"RATE_LIMITED";default->"INTERNAL_ERROR";};
  String message=switch(status){case 400,405,413,415,422->"Solicitud inválida o fuera de los límites permitidos.";case 401->"Se requiere una sesión válida.";case 403->"Solicitud no autorizada.";case 404->"Recurso no encontrado.";case 409->"La solicitud no es compatible con el estado actual.";case 429->"Límite temporal alcanzado.";default->"No se pudo completar la solicitud.";};
  return Map.of("timestamp",Instant.now().toString(),"status",status,"code",code,"message",message,"requestId",Objects.toString(MDC.get("requestId"),""));
 }
 public static void write(HttpServletResponse response,int status)throws java.io.IOException {
  response.setStatus(status);response.setContentType("application/json");response.setCharacterEncoding("UTF-8");
  new ObjectMapper().writeValue(response.getOutputStream(),body(status));
 }
}
