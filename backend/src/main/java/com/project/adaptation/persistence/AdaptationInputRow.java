package com.project.adaptation.persistence;
import jakarta.persistence.*;
import java.util.UUID;
@Entity @Table(name="adaptation_attempt_inputs")
public class AdaptationInputRow {
    @Id public UUID attemptId;
    public UUID studentId;
    public String activityId;
    @Column(columnDefinition="text") public String evidenceJson;
}
