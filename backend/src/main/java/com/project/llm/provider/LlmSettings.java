package com.project.llm.provider;
import com.project.llm.domain.LlmTypes.Provider;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/** Deliberately not a record: secrets must never appear in generated toString. */
@Component
public class LlmSettings {
    private final Provider provider;
    private final String model, key;
    private final int timeoutMs, maxRetries;
    @org.springframework.beans.factory.annotation.Autowired
    public LlmSettings(Environment env) {
        this(parse(env.getProperty("LLM_PROVIDER","DISABLED")),env.getProperty(variable(env,"MODEL"),""),
            env.getProperty(variable(env,"API_KEY"),""),number(env,"LLM_TIMEOUT_MS",3000,50,10000),number(env,"LLM_MAX_RETRIES",1,0,2));
    }
    public LlmSettings(Provider provider,String model,String key,int timeoutMs,int maxRetries) {
        this.provider=provider;this.model=model==null?"":model;this.key=key==null?"":key;
        if(timeoutMs<50 || timeoutMs>10000 || maxRetries<0 || maxRetries>2)throw new IllegalArgumentException("Invalid bounded LLM settings");
        this.timeoutMs=timeoutMs;this.maxRetries=maxRetries;
    }
    private static Provider parse(String s){try{return Provider.valueOf(s.trim().toUpperCase(java.util.Locale.ROOT));}catch(Exception e){return Provider.DISABLED;}}
    private static int number(Environment e,String n,int d,int min,int max){try{return Math.max(min,Math.min(max,Integer.parseInt(e.getProperty(n,""+d))));}catch(Exception x){return d;}}
    private static String variable(Environment env,String suffix){return (parse(env.getProperty("LLM_PROVIDER","DISABLED"))==Provider.GEMINI?"GEMINI_":"LLM_")+suffix;}
    public com.project.llm.domain.LlmTypes.Source successSource(){return switch(provider){
        case OPENAI -> com.project.llm.domain.LlmTypes.Source.OPENAI;
        case GEMINI -> com.project.llm.domain.LlmTypes.Source.GEMINI;
        case FAKE -> com.project.llm.domain.LlmTypes.Source.FAKE;
        case DISABLED -> com.project.llm.domain.LlmTypes.Source.FALLBACK;
    };}
    public Provider provider(){return provider;}
    public String model(){return model;}
    String key(){return key;}
    public boolean available(){return !key.isBlank() && !model.isBlank();}
    public int timeoutMs(){return timeoutMs;}
    public int maxRetries(){return maxRetries;}
    public String configurationKey(){return provider+":"+model+":"+timeoutMs+":"+maxRetries+":"+available();}
    @Override public String toString(){return "LlmSettings[provider="+provider+"]";}
}
