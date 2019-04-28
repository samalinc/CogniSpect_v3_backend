package com.bsuir.cognispect.dto;

import com.bsuir.cognispect.entity.enums.TestVariantStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestVariantDto {
    private UUID id;
    private TestVariantStatusEnum testVariantStatus;
    private List<QuestionVariantDto> questionVariants;
}
