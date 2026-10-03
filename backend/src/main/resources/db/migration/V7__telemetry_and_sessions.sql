-- Observational audit. V1-V6 and existing educational records remain unchanged.
CREATE TABLE sessions (
 id uuid PRIMARY KEY, student_id uuid NOT NULL REFERENCES students,
 started_at timestamptz NOT NULL, ended_at timestamptz,
 status varchar(16) NOT NULL CHECK(status IN ('ACTIVE','ENDED')),
 created_at timestamptz NOT NULL DEFAULT clock_timestamp(),
 CHECK((status='ACTIVE' AND ended_at IS NULL) OR (status='ENDED' AND ended_at>=started_at))
);
CREATE INDEX sessions_student ON sessions(student_id,started_at);
CREATE TABLE attempt_events (
 id uuid PRIMARY KEY, attempt_id uuid REFERENCES attempts, session_id uuid REFERENCES sessions,
 student_id uuid NOT NULL REFERENCES students, request_id uuid NOT NULL,
 sequence_number bigint NOT NULL CHECK(sequence_number>0),
 event_type varchar(48) NOT NULL CHECK(event_type IN ('SESSION_STARTED','SESSION_ENDED','ACTIVITY_OPENED','NAVIGATION_PRESENTED','ATTEMPT_CREATED','PROGRAM_MODEL_CREATED','PROGRAM_MODEL_VALIDATED','EXECUTION_STARTED','EXECUTION_COMPLETED','EVALUATION_COMPLETED','ERROR_PATTERN_DETECTED','STUDENT_MODEL_UPDATED','ADAPTATION_STARTED','ADAPTATION_COMPLETED','FEEDBACK_REQUESTED','FEEDBACK_CLASSIFICATION_COMPLETED','FEEDBACK_GENERATED','FEEDBACK_FALLBACK_USED','UI_CONFIGURATION_CREATED','ACTIVITY_COMPLETED','TECHNICAL_ERROR')),
 event_version integer NOT NULL CHECK(event_version=1),
 source varchar(16) NOT NULL CHECK(source IN ('BACKEND','EXECUTION','EVALUATION','LEARNING','ADAPTATION','LLM','UI','SYSTEM')),
 occurred_at timestamptz NOT NULL, payload jsonb NOT NULL CHECK(jsonb_typeof(payload)='object' AND octet_length(payload::text)<=8192),
 payload_hash varchar(64) NOT NULL CHECK(payload_hash ~ '^[a-f0-9]{64}$'),
 event_key varchar(200) NOT NULL, created_at timestamptz NOT NULL DEFAULT clock_timestamp(),
 CHECK(attempt_id IS NOT NULL OR session_id IS NOT NULL),
 UNIQUE(attempt_id,sequence_number), UNIQUE(student_id,event_key)
);
CREATE UNIQUE INDEX events_session_sequence ON attempt_events(session_id,sequence_number) WHERE attempt_id IS NULL;
CREATE INDEX events_session ON attempt_events(session_id,occurred_at);
CREATE INDEX events_student ON attempt_events(student_id,occurred_at);
CREATE INDEX events_occurred ON attempt_events(occurred_at);
CREATE FUNCTION reject_event_mutation() RETURNS trigger LANGUAGE plpgsql AS $$
BEGIN RAISE EXCEPTION 'AUDIT_APPEND_ONLY'; END $$;
CREATE TRIGGER attempt_events_append_only BEFORE UPDATE OR DELETE ON attempt_events FOR EACH ROW EXECUTE FUNCTION reject_event_mutation();
