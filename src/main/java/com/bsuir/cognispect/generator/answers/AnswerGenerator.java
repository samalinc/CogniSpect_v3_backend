package com.bsuir.cognispect.generator.answers;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;

import java.util.List;

public interface AnswerGenerator<T> {

    List<T> generateAnswer(Question question, int substitutionsIndex);

    QuestionTypeEnum getAnswerType();
}
