package com.project.student.infrastructure;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;
@Entity @Table(name="error_patterns")
public class ErrorPatternRow {
    @Id public String id;
    public String conceptId;
    public String severity;
    @Column(columnDefinition="text") public String pedagogicalMeaning;
}
