package com.bsuir.cognispect.generator.question.questionGenerator;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.QuestionVariant;
import com.bsuir.cognispect.entity.SortAnswer;
import com.bsuir.cognispect.entity.TestVariant;
import com.bsuir.cognispect.generator.question.QuestionVariantGenerator;

import java.util.List;

public class QuestionVariantSortGeneratorImpl implements QuestionVariantGenerator<SortAnswer> {

    @Override
    public QuestionVariant createQuestionVariant(List<SortAnswer> answers, Question question, TestVariant testVariant) {
        return null;
    }
}
