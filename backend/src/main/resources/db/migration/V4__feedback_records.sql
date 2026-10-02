-- Minimal sanitized evidence captured once; no program, profile, prompt or raw provider body.
CREATE TABLE feedback_attempt_inputs (
 attempt_id uuid PRIMARY KEY REFERENCES attempts,
 evidence_json text NOT NULL
);
CREATE TABLE feedback_records (
 id uuid PRIMARY KEY,
 student_id uuid NOT NULL REFERENCES students,
 attempt_id uuid NOT NULL REFERENCES attempts,
 adaptation_decision_id uuid NOT NULL REFERENCES adaptation_decisions,
 purpose varchar(40) NOT NULL CHECK(purpose='FEEDBACK_GENERATION'),
 source varchar(16) NOT NULL CHECK(source IN ('OPENAI','FAKE','FALLBACK')),
 provider varchar(16) NOT NULL CHECK(provider IN ('OPENAI','FAKE','DISABLED')),
 model varchar(200) NOT NULL,
 prompt_template_version integer NOT NULL CHECK(prompt_template_version>0),
 policy_version integer NOT NULL CHECK(policy_version>0),
 configuration_hash varchar(64) NOT NULL,
 prompt_hash varchar(64) NOT NULL,
 sanitized_context_hash varchar(64) NOT NULL,
 status varchar(32) NOT NULL,
 fallback_reason varchar(100),
 llm_used boolean NOT NULL,
 feedback_text text NOT NULL,
 recognized boolean,
 confidence numeric(4,3),
 created_at timestamptz NOT NULL,
 response_json text NOT NULL,
 UNIQUE(attempt_id,adaptation_decision_id,purpose,prompt_template_version,policy_version,configuration_hash),
 CHECK(NOT llm_used OR source='OPENAI')
);
CREATE TABLE feedback_record_tags (
 feedback_record_id uuid NOT NULL REFERENCES feedback_records ON DELETE CASCADE,
 tag varchar(80) NOT NULL,
 tag_order integer NOT NULL,
 PRIMARY KEY(feedback_record_id,tag_order), UNIQUE(feedback_record_id,tag)
);
CREATE TABLE feedback_provider_calls (
 feedback_record_id uuid NOT NULL REFERENCES feedback_records ON DELETE CASCADE,
 purpose varchar(40) NOT NULL CHECK(purpose IN ('FEEDBACK_GENERATION','UNKNOWN_CASE_CLASSIFICATION')),
 status varchar(32) NOT NULL,
 attempts integer NOT NULL CHECK(attempts>=0 AND attempts<=3),
 prompt_hash varchar(64) NOT NULL,
 sanitized_context_hash varchar(64) NOT NULL,
 provider_request_id varchar(120),
 validation_reason varchar(100),
 PRIMARY KEY(feedback_record_id,purpose)
);
CREATE INDEX feedback_attempt_history ON feedback_records(attempt_id,created_at);
