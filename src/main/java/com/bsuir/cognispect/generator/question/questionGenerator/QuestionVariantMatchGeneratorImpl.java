package com.bsuir.cognispect.generator.question.questionGenerator;

import com.bsuir.cognispect.entity.MatchAnswer;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.QuestionVariant;
import com.bsuir.cognispect.entity.TestVariant;
import com.bsuir.cognispect.generator.question.QuestionVariantGenerator;

import java.util.List;

public class QuestionVariantMatchGeneratorImpl implements QuestionVariantGenerator<MatchAnswer> {

    @Override
    public QuestionVariant createQuestionVariant(List<MatchAnswer> answers, Question question,
                                                 TestVariant testVariant) {
        return null;
    }
}
