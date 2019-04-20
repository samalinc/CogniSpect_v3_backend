package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.MatchAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface MatchAnswerRepository
        extends JpaRepository<MatchAnswer, UUID> {
}
