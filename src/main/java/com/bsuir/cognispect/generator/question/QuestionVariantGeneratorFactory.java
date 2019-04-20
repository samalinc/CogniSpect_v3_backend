package com.bsuir.cognispect.generator.question;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.generator.question.questionGenerator.QuestionVariantChooseGeneratorImpl;
import com.bsuir.cognispect.generator.question.questionGenerator.QuestionVariantMatchGeneratorImpl;
import com.bsuir.cognispect.generator.question.questionGenerator.QuestionVariantSortGeneratorImpl;

public class QuestionVariantGeneratorFactory {

    public QuestionVariantGenerator getQuestionVariantType(QuestionTypeEnum questionType) {
        switch (questionType) {
            case CHOOSE:
                return new QuestionVariantChooseGeneratorImpl();
            case MATCH:
                return new QuestionVariantMatchGeneratorImpl();
            case SORT:
                return new QuestionVariantSortGeneratorImpl();
            default:
                // TODO : add all
                return null;

        }

    }
}
