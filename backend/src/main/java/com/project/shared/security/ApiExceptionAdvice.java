package com.project.shared.security;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionAdvice {
 @ExceptionHandler({org.springframework.web.bind.MethodArgumentNotValidException.class,org.springframework.http.converter.HttpMessageNotReadableException.class,
  org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,org.springframework.web.bind.MissingServletRequestParameterException.class,
  jakarta.validation.ConstraintViolationException.class,IllegalArgumentException.class})
 public ResponseEntity<Map<String,Object>> invalid(Exception e){return ResponseEntity.badRequest().body(ApiErrors.body(400));}
 @ExceptionHandler(ResponseStatusException.class) public ResponseEntity<Map<String,Object>> status(ResponseStatusException e){int code=e.getStatusCode().value();return ResponseEntity.status(code).body(ApiErrors.body(code));}
 @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class) public ResponseEntity<Map<String,Object>> method(Exception e){return ResponseEntity.status(405).body(ApiErrors.body(405));}
 @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class) public ResponseEntity<Map<String,Object>> missing(Exception e){return ResponseEntity.status(404).body(ApiErrors.body(404));}
 @ExceptionHandler(Exception.class) public ResponseEntity<Map<String,Object>> internal(Exception e){org.slf4j.LoggerFactory.getLogger(getClass()).error("event=HTTP_ERROR status=500");return ResponseEntity.internalServerError().body(ApiErrors.body(500));}
}
