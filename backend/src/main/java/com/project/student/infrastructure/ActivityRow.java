package com.project.student.infrastructure;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;
@Entity @Table(name="learning_activities")
public class ActivityRow {
    @Id public String id;
    public String title;
    public String conceptId;
    public boolean reinforcement;
}
