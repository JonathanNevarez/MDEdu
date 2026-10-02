package com.project.student.infrastructure;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;
@Entity @Table(name="student_activity_progress")
public class ProgressRow {
    @Id public UUID id;
    public UUID studentId;
    public String activityId;
    public Instant completedAt;
    public Instant unlockedAt;
}
