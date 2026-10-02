package com.project.student.infrastructure;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;
@Entity @Table(name="attempts")
public class AttemptRow {
    @Id public UUID id;
    public UUID studentId;
    public String activityId;
    public String conceptId;
    public long studentOrdinal;
    public boolean successful;
    public boolean functionalPassed;
    public long resolutionTime;
    public int hintCount;
    public Instant submittedAt;
    public int policyVersion;
    public double masteryBefore;
    public double masteryDelta;
    public double masteryAfter;
    public String updateReasons;
    @ElementCollection @CollectionTable(name="attempt_error_patterns",joinColumns=@JoinColumn(name="attempt_id"))
    @Column(name="pattern_id") @OrderColumn(name="pattern_order") public List<String> patterns = new ArrayList<>();
}
