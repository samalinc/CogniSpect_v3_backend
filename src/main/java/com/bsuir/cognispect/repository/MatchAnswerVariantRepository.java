package com.bsuir.cognispect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface MatchAnswerVariantRepository
        extends JpaRepository<MatchAnswerRepository, UUID> {
}
