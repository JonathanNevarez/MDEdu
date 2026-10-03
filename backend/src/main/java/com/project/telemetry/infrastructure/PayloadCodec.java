package com.project.telemetry.infrastructure;
import com.project.telemetry.domain.TelemetryTypes.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import org.springframework.stereotype.Component;
@Component
public class PayloadCodec {
 private final ObjectMapper json;
 public PayloadCodec(ObjectMapper json){this.json=json;}
 public String encode(Type type,Payload payload){
  if(payload==null || payload.getClass()!=type.payloadClass)throw new IllegalArgumentException("EVENT_PAYLOAD_TYPE");
  try {var node=json.valueToTree(payload);validate(node);String value=json.writeValueAsString(canonical(node));
   if(value.getBytes(StandardCharsets.UTF_8).length>4096)throw new IllegalArgumentException("EVENT_PAYLOAD_LIMIT");return value;
  }catch(IllegalArgumentException e){throw e;}catch(Exception e){throw new IllegalArgumentException("EVENT_PAYLOAD_INVALID");}
 }
 public Payload decode(Type type,String value){try {var result=json.readValue(value,type.payloadClass);encode(type,result);return result;}catch(Exception e){throw new IllegalArgumentException("EVENT_PAYLOAD_INVALID");}}
 private void validate(JsonNode node){
  if(node.isTextual() && (node.textValue().length()>200 || node.textValue().chars().anyMatch(c->c<32)))throw new IllegalArgumentException("EVENT_TEXT_LIMIT");
  if(node.isArray() && node.size()>32)throw new IllegalArgumentException("EVENT_ARRAY_LIMIT");
  if(node.isFloatingPointNumber() && !Double.isFinite(node.asDouble()))throw new IllegalArgumentException("EVENT_NUMBER_INVALID");
  node.elements().forEachRemaining(this::validate);
 }
 private JsonNode canonical(JsonNode node){if(node.isObject()){var result=json.createObjectNode();var keys=new TreeSet<String>();node.fieldNames().forEachRemaining(keys::add);keys.forEach(k->result.set(k,canonical(node.get(k))));return result;}
  if(node.isArray()){var result=json.createArrayNode();node.forEach(v->result.add(canonical(v)));return result;}return node;}
 public String hash(String value){try{return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(value.getBytes(StandardCharsets.UTF_8)));}catch(Exception e){throw new IllegalStateException("HASH_UNAVAILABLE");}}
}
