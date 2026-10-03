package com.project.telemetry.application;
import java.util.UUID;
import org.slf4j.MDC;
public final class CorrelationContext {
 private CorrelationContext(){}
 public static UUID requestId(){UUID value=uuid(MDC.get("requestId"));return value==null?UUID.randomUUID():value;}
 public static UUID sessionId(){return uuid(MDC.get("sessionId"));}
 public static UUID uuid(String value){try{if(value==null || !value.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}"))return null;return UUID.fromString(value);}catch(Exception e){return null;}}
}
