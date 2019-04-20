package com.bsuir.cognispect.generator.answers;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.generator.answers.answersGenerators.AnswerChooseGeneratorImpl;
import com.bsuir.cognispect.generator.answers.answersGenerators.MatchAnswerGeneratorImpl;
import com.bsuir.cognispect.generator.answers.answersGenerators.SortAnswerGeneratorImpl;

public class AnswerFactory {

    public AnswerGenerator getAnswerGeneratorType(Question question) {
        switch (question.getType()) {
            case SORT:
                return new SortAnswerGeneratorImpl();
            case CHOOSE:
                return new AnswerChooseGeneratorImpl();
            case MATCH:
                return new MatchAnswerGeneratorImpl();
            default:
                //TODO: add all types
                return null;
        }
    }
}
