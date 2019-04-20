package com.bsuir.cognispect.generator;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.QuestionVariant;
import com.bsuir.cognispect.entity.Substitution;
import com.bsuir.cognispect.generator.answers.AnswerFactory;
import com.bsuir.cognispect.generator.answers.AnswerGenerator;
import com.bsuir.cognispect.generator.question.QuestionVariantGenerator;
import com.bsuir.cognispect.generator.question.QuestionVariantGeneratorFactory;

import java.util.List;
import java.util.Random;

public class QuestionVariantGeneratorService {
    private static final String SUBSTITUTION_SIGNAL_VALUE = "%substitution%";


    public QuestionVariant generateQuestionVariant(Question question) {

        Random rand = new Random();
        int substitutionsIndex = rand.nextInt(question.getSubstitutions().size());

        AnswerFactory answerFactory = new AnswerFactory();
        AnswerGenerator answerGenerator = answerFactory.getAnswerGeneratorType(question);

        QuestionVariantGeneratorFactory questionVariantGeneratorFactory = new QuestionVariantGeneratorFactory();
        QuestionVariantGenerator questionVariantGenerator = questionVariantGeneratorFactory.
                getQuestionVariantType(question.getType());

        return questionVariantGenerator.createQuestionVariant(answerGenerator.generateAnswer(question,
                substitutionsIndex), question);
    }


    private String generateDescription(Question question, int substitutionsIndex) {
        List<Substitution> substitutions = question.getSubstitutions();

        return question.getDescription().contains(SUBSTITUTION_SIGNAL_VALUE) ? question.getDescription().
                replace(SUBSTITUTION_SIGNAL_VALUE, substitutions.get(substitutionsIndex).getText()) :
                question.getDescription();

    }
}
