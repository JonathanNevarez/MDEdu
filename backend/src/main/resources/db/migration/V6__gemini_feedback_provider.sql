-- Additive provider metadata only. Existing records, tables and V1-V5 remain unchanged.
ALTER TABLE feedback_records DROP CONSTRAINT feedback_records_source_check;
ALTER TABLE feedback_records ADD CONSTRAINT feedback_records_source_check CHECK(source IN ('OPENAI','GEMINI','FAKE','FALLBACK'));
ALTER TABLE feedback_records DROP CONSTRAINT feedback_records_provider_check;
ALTER TABLE feedback_records ADD CONSTRAINT feedback_records_provider_check CHECK(provider IN ('OPENAI','GEMINI','FAKE','DISABLED'));
ALTER TABLE feedback_records DROP CONSTRAINT feedback_records_check;
ALTER TABLE feedback_records ADD CONSTRAINT feedback_records_check CHECK(NOT llm_used OR source IN ('OPENAI','GEMINI'));
