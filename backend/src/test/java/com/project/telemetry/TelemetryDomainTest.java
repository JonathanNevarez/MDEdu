package com.project.telemetry;
import com.project.telemetry.domain.TelemetryTypes.*;
import com.project.telemetry.infrastructure.PayloadCodec;
import com.project.telemetry.application.CorrelationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TelemetryDomainTest {
 final PayloadCodec codec=new PayloadCodec(new ObjectMapper());
 @Test void typedPayloadRoundtripAndStableHash(){var p=new ExecutionPayload(true,"OK",7,true,0,"a".repeat(64));var s=codec.encode(Type.EXECUTION_COMPLETED,p);assertEquals(p,codec.decode(Type.EXECUTION_COMPLETED,s));assertEquals(codec.hash(s),codec.hash(codec.encode(Type.EXECUTION_COMPLETED,p)));assertEquals(64,codec.hash(s).length());}
 @Test void mismatchedTypeAndUnboundedPayloadRejected(){assertThrows(IllegalArgumentException.class,()->codec.encode(Type.EXECUTION_COMPLETED,new SessionPayload("ACTIVE")));assertThrows(IllegalArgumentException.class,()->codec.encode(Type.TECHNICAL_ERROR,new ErrorPayload("x".repeat(201),"test")));assertThrows(IllegalArgumentException.class,()->codec.encode(Type.TECHNICAL_ERROR,new ErrorPayload("bad\nline","test")));}
 @Test void requestIdAllowlistRejectsInjection(){assertNull(CorrelationContext.uuid("arbitrary\nlog"));assertNull(CorrelationContext.uuid("x".repeat(1000)));assertNotNull(CorrelationContext.uuid("12345678-1234-1234-1234-123456789012"));}
}
