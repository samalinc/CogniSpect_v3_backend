package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.TestVariantDto;
import com.bsuir.cognispect.entity.TestVariant;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {QuestionVariantMapper.class})
public abstract class TestVariantMapper {
    public abstract TestVariantDto testVariantToTestVariantDto(TestVariant testVariant);

    public abstract List<TestVariantDto> testVariantsToTestVariantsDto(
            Collection<TestVariant> testVariants);
}
