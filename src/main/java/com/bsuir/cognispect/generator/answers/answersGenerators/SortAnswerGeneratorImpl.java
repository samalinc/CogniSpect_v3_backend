package com.bsuir.cognispect.generator.answers.answersGenerators;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.SortAnswer;
import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.generator.answers.AnswerGenerator;

import java.util.List;

public class SortAnswerGeneratorImpl implements AnswerGenerator<SortAnswer> {

    @Override
    public List<SortAnswer> generateAnswer(Question question, int substitutionsIndex) {
        return null;
    }

    @Override
    public QuestionTypeEnum getAnswerType() {
        return QuestionTypeEnum.SORT;
    }
}
