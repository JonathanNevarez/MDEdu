package com.project.llm.infrastructure;
import javax.sql.DataSource;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

/** Session advisory lock, not an educational row/transaction lock. Works across backend instances. */
@Component
public class FeedbackGenerationLock {
    private final DataSource source;
    public FeedbackGenerationLock(DataSource source){this.source=source;}
    public <T>T withLock(String hash,Supplier<T> operation) {
        long key=Long.parseUnsignedLong(hash.substring(0,16),16);
        try(var connection=source.getConnection()) {
            boolean acquired=false;
            try {
                long deadline=System.nanoTime()+java.util.concurrent.TimeUnit.SECONDS.toNanos(65);
                while(!acquired) {
                    try(var statement=connection.prepareStatement("select pg_try_advisory_lock(?)")) {statement.setLong(1,key);try(var rs=statement.executeQuery()){rs.next();acquired=rs.getBoolean(1);}}
                    if(!acquired){if(System.nanoTime()>deadline)throw new ResponseStatusException(CONFLICT,"FEEDBACK_IN_PROGRESS");Thread.sleep(25);}
                }
                return operation.get();
            } finally {
                if(acquired)try(var statement=connection.prepareStatement("select pg_advisory_unlock(?)")){statement.setLong(1,key);statement.execute();}
            }
        }catch(InterruptedException e){Thread.currentThread().interrupt();throw new ResponseStatusException(CONFLICT,"FEEDBACK_INTERRUPTED");}
        catch(java.sql.SQLException e){throw new IllegalStateException("Feedback coordination unavailable");}
    }
}
