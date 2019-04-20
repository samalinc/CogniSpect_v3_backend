package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.QuestionVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionVariantRepository extends JpaRepository<QuestionVariant, UUID> {
}
