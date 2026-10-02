package com.project.adaptation.persistence;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;
@Entity @Table(name="adaptation_decisions")
public class AdaptationDecisionRow {
    @Id public UUID id;
    public UUID studentId,attemptId;
    public int rulesetVersion,parametersVersion;
    public String selectedRuleId,contextHash,rulesetHash,parametersHash,decisionFingerprint;
    public boolean llmUsed;
    public Instant createdAt;
    @Column(columnDefinition="text") public String semanticJson;
}
