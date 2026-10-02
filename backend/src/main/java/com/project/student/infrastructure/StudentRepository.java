package com.project.student.infrastructure;
import java.util.*;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
public interface StudentRepository extends JpaRepository<StudentRow,UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from StudentRow s where s.id = :id")
    Optional<StudentRow> lock(@Param("id") UUID id);
}
