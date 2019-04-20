package com.bsuir.cognispect.generator.question;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.QuestionVariant;

import java.util.List;

public interface QuestionVariantGenerator<T> {

    QuestionVariant createQuestionVariant(List<T> answers, Question question);
}
