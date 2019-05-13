package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.ChooseAnswerVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ChooseAnswerVariantRepository
        extends JpaRepository<ChooseAnswerVariant, UUID> {
}
