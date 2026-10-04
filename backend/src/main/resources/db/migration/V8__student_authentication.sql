-- Additive identity only. Historical students and learning records remain untouched.
CREATE SEQUENCE student_code_sequence MINVALUE 1 MAXVALUE 999999 NO CYCLE;
CREATE TABLE student_accounts (
    id uuid PRIMARY KEY,
    student_id uuid NOT NULL UNIQUE REFERENCES students(id),
    student_code varchar(10) NOT NULL UNIQUE CHECK (student_code ~ '^EST-[0-9]{3,6}$'),
    password_hash varchar(100) NOT NULL,
    enabled boolean NOT NULL DEFAULT true,
    created_at timestamptz NOT NULL DEFAULT now(),
    last_login_at timestamptz,
    credential_version bigint NOT NULL DEFAULT 0
);
