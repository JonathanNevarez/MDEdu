-- Immutable pedagogical evidence for replay; not StudentModel or XMI, no personal profile.
CREATE TABLE adaptation_attempt_inputs (
 attempt_id uuid PRIMARY KEY REFERENCES attempts,
 student_id uuid NOT NULL REFERENCES students,
 activity_id varchar(64) NOT NULL REFERENCES learning_activities,
 evidence_json text NOT NULL
);
CREATE TABLE adaptation_decisions (
 id uuid PRIMARY KEY,
 student_id uuid NOT NULL REFERENCES students,
 attempt_id uuid NOT NULL REFERENCES attempts,
 ruleset_version integer NOT NULL CHECK(ruleset_version>0),
 parameters_version integer NOT NULL CHECK(parameters_version>0),
 selected_rule_id varchar(120),
 context_hash varchar(64) NOT NULL,
 ruleset_hash varchar(64) NOT NULL,
 parameters_hash varchar(64) NOT NULL,
 decision_fingerprint varchar(64) NOT NULL,
 llm_used boolean NOT NULL DEFAULT false CHECK(llm_used=false),
 created_at timestamptz NOT NULL,
 semantic_json text NOT NULL,
 UNIQUE(attempt_id,ruleset_version,parameters_version)
);
CREATE TABLE adaptation_decision_rules (
 decision_id uuid NOT NULL REFERENCES adaptation_decisions ON DELETE CASCADE,
 source_order integer NOT NULL,
 rule_id varchar(120) NOT NULL,
 rule_version integer NOT NULL,
 priority integer NOT NULL,
 specificity integer NOT NULL,
 severity varchar(16) NOT NULL,
 status varchar(24) NOT NULL,
 reason_code varchar(100) NOT NULL,
 evidence_json text NOT NULL,
 PRIMARY KEY(decision_id,source_order), UNIQUE(decision_id,rule_id)
);
CREATE TABLE adaptation_decision_actions (
 decision_id uuid NOT NULL REFERENCES adaptation_decisions ON DELETE CASCADE,
 action_order integer NOT NULL,
 rule_id varchar(120) NOT NULL,
 source_action_order integer NOT NULL,
 action_type varchar(80) NOT NULL,
 hint_level varchar(20),
 feedback_style varchar(24),
 target_concept_id varchar(64) REFERENCES concepts,
 status varchar(24) NOT NULL,
 reason_code varchar(100) NOT NULL,
 PRIMARY KEY(decision_id,action_order)
);
CREATE INDEX adaptation_student_history ON adaptation_decisions(student_id,created_at);
