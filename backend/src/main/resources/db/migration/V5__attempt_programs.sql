-- Original validated input for read-only Acceleo code projection. No backfill:
-- historical attempts without a snapshot explicitly have no generated code.
CREATE TABLE attempt_programs (
    attempt_id uuid PRIMARY KEY REFERENCES attempts,
    program_dto_json text NOT NULL,
    program_hash varchar(64) NOT NULL CHECK (program_hash ~ '^[0-9a-f]{64}$')
);
