package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.QuestionVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface QuestionVariantRepository
        extends JpaRepository<QuestionVariant, UUID> {
}
