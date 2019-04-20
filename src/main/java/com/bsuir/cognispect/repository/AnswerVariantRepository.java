package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.AnswerVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnswerVariantRepository extends JpaRepository<AnswerVariant, UUID> {
}
