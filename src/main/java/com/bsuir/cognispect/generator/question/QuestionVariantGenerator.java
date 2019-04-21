package com.bsuir.cognispect.generator.question;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.QuestionVariant;
import com.bsuir.cognispect.entity.TestVariant;

import java.util.List;

public interface QuestionVariantGenerator<T> {

    QuestionVariant createQuestionVariant(List<T> answers, Question question, TestVariant testVariant);
}
