package com.project.student.infrastructure;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;
@Entity @Table(name="concepts")
public class ConceptRow {
    @Id public String id;
    public String name;
    public double minimumMasteryToUnlock;
    public int sortOrder;
    @ElementCollection @CollectionTable(name="concept_prerequisites",joinColumns=@JoinColumn(name="concept_id"))
    @Column(name="prerequisite_id") public Set<String> prerequisites = new LinkedHashSet<>();
}
