package com.bsuir.cognispect.generator.answers.answersGenerators;

import com.bsuir.cognispect.entity.MatchAnswer;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.generator.answers.AnswerGenerator;

import java.util.List;

public class MatchAnswerGeneratorImpl implements AnswerGenerator<MatchAnswer> {
    @Override
    public List<MatchAnswer> generateAnswer(Question question, int substitutionsIndex) {
        return null;
    }

    @Override
    public QuestionTypeEnum getAnswerType() {
        return QuestionTypeEnum.MATCH;
    }
}
