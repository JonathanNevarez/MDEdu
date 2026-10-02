package com.project.student.infrastructure;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;
@Entity @Table(name="student_concept_mastery")
public class MasteryRow {
    @Id public UUID id;
    public UUID studentId;
    public String conceptId;
    public double masteryScore;
    public int attemptCount;
    public int successCount;
    public int failureCount;
    public int consecutiveFailures;
    public double averageResolutionTime;
    public int hintCount;
    public Instant lastUpdated;
}
