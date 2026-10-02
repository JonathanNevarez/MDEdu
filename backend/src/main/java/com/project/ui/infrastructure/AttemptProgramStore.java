package com.project.ui.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.programming.api.ProgramDto;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** Immutable snapshot; belongs to the attempt transaction, never sent to an LLM. */
@Repository
public class AttemptProgramStore {
    private final JdbcTemplate jdbc;
    private final ObjectMapper json;

    public AttemptProgramStore(JdbcTemplate jdbc, ObjectMapper json) {
        this.jdbc = jdbc;
        this.json = json;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void capture(UUID attemptId, ProgramDto validatedProgram) {
        try {
            String value = json.writeValueAsString(validatedProgram);
            jdbc.update("insert into attempt_programs(attempt_id,program_dto_json,program_hash) values (?,?,?)",
                    attemptId, value, hash(value));
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException("Cannot encode validated attempt program", ex);
        }
    }

    /** Ownership is enforced in the query, including historical attempts. */
    public Optional<ProgramDto> find(UUID studentId, UUID attemptId) {
        return jdbc.query("""
                select p.program_dto_json,p.program_hash from attempt_programs p
                join attempts a on a.id=p.attempt_id where a.student_id=? and a.id=?
                """, (rs, row) -> {
            String value = rs.getString(1);
            if (!hash(value).equals(rs.getString(2)))
                throw new IllegalStateException("Attempt program integrity failure");
            try {
                return json.readValue(value, ProgramDto.class);
            } catch (JsonProcessingException ex) {
                throw new IllegalStateException("Invalid persisted attempt program", ex);
            }
        }, studentId, attemptId).stream().findFirst();
    }

    static String hash(String value) {
        try {
            return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256")
                    .digest(value.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 unavailable", ex);
        }
    }
}
