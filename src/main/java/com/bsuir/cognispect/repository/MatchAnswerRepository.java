package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.MatchAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface MatchAnswerRepository
        extends JpaRepository<MatchAnswer, UUID> {
    @Transactional
    @Query(value = "DELETE FROM match_answer m WHERE m.id=?1 RETURNING m.*", nativeQuery = true)
    Optional<MatchAnswer> deleteMatchAnswerById(UUID matchAnswerId);
}
