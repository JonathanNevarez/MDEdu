package com.project.telemetry.application;
import com.project.telemetry.domain.TelemetryTypes.*;
import com.project.telemetry.infrastructure.PayloadCodec;
import java.util.*;
import org.springframework.stereotype.Component;
@Component
public class AttemptTraceConsistencyChecker {
 private final PayloadCodec codec;public AttemptTraceConsistencyChecker(PayloadCodec codec){this.codec=codec;}
 public record Check(TraceStatus status,List<String> gaps){}
 public Check check(UUID student,UUID attempt,List<Event> events,boolean adaptationPresent,boolean feedbackPresent,String programHash){
  if(events.isEmpty())return new Check(TraceStatus.PARTIAL,List.of("LEGACY_NO_EVENTS"));
  var gaps=new ArrayList<String>();boolean inconsistent=false;long sequence=0;
  for(var event:events){
   if(event.source()!=event.type().source){gaps.add("EVENT_SOURCE_MISMATCH");inconsistent=true;}
   if(!event.studentId().equals(student)||!Objects.equals(event.attemptId(),attempt)||event.sequence()!=++sequence){gaps.add("EVENT_SEQUENCE_OR_OWNER");inconsistent=true;}
   if(event.payload()==null || !codec.hash(codec.encode(event.type(),event.payload())).equals(event.payloadHash())){gaps.add("PAYLOAD_HASH");inconsistent=true;}
   if(event.payload() instanceof ModelPayload model && programHash!=null && !programHash.equals(model.programHash())){gaps.add("PROGRAM_HASH");inconsistent=true;}
  }
  var core=List.of(Type.ATTEMPT_CREATED,Type.PROGRAM_MODEL_CREATED,Type.PROGRAM_MODEL_VALIDATED,Type.EXECUTION_STARTED,Type.EXECUTION_COMPLETED,Type.EVALUATION_COMPLETED,Type.STUDENT_MODEL_UPDATED,Type.ADAPTATION_STARTED,Type.ADAPTATION_COMPLETED);
  int previous=-1;
  for(var type:core){int index=-1;for(int i=0;i<events.size();i++)if(events.get(i).type()==type){index=i;break;}
   if(index<0){gaps.add("MISSING_"+type);continue;}if(index<previous){gaps.add("IMPOSSIBLE_ORDER_"+type);inconsistent=true;}previous=index;
  }
  if(!adaptationPresent){gaps.add("ADAPTATION_REFERENCE_MISSING");inconsistent=true;}
  boolean auditedFeedback=events.stream().anyMatch(e->e.type()==Type.FEEDBACK_GENERATED||e.type()==Type.FEEDBACK_FALLBACK_USED);
  if(feedbackPresent&&!auditedFeedback)gaps.add("FEEDBACK_EVENT_MISSING");
  if(auditedFeedback&&!feedbackPresent){gaps.add("FEEDBACK_REFERENCE_MISSING");inconsistent=true;}
  return new Check(inconsistent?TraceStatus.INCONSISTENT:gaps.isEmpty()?TraceStatus.COMPLETE:TraceStatus.PARTIAL,List.copyOf(gaps));
 }
}
