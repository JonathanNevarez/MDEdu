package com.project.student.infrastructure;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;
@Entity @Table(name="students")
public class StudentRow {
    @Id public UUID id;
    public String displayName;
    public Instant createdAt;
    public Instant lastUpdated;
    public long attemptSequence;
    @Version public long version;
}
