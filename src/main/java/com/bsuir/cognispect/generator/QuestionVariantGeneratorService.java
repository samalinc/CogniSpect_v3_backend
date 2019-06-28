package com.bsuir.cognispect.generator;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.QuestionVariant;
import com.bsuir.cognispect.entity.Substitution;
import com.bsuir.cognispect.generator.answers.AnswerFactory;
import com.bsuir.cognispect.generator.answers.AnswerGenerator;
import com.bsuir.cognispect.generator.answers.answersGenerators.AnswerChooseGeneratorImpl;
import com.bsuir.cognispect.generator.question.QuestionVariantGenerator;
import com.bsuir.cognispect.generator.question.QuestionVariantGeneratorFactory;
import com.bsuir.cognispect.generator.question.questionGenerator.QuestionVariantChooseGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionVariantGeneratorService {
    private static final String SUBSTITUTION_SIGNAL_VALUE = "%substitution%";
    @Autowired
    private AnswerChooseGeneratorImpl answerChooseGenerator;
    @Autowired
    private QuestionVariantChooseGeneratorImpl questionVariantChooseGenerator;

    public QuestionVariant generateQuestionVariant(Question question) {

        /*Random rand = new Random();
        int subCount = question.getSubstitutions().size();
        if(subCount > 0) {
            int substitutionsIndex = rand.nextInt(question.getSubstitutions().size());
            question.setDescription(generateDescription(question, substitutionsIndex));
        } else {
            question.setDescription(question.getDescription());
        }*/
        int substitutionsIndex = 0;
        AnswerFactory answerFactory = new AnswerFactory();
        AnswerGenerator answerGenerator = answerChooseGenerator;

        QuestionVariantGeneratorFactory questionVariantGeneratorFactory = new QuestionVariantGeneratorFactory();
        QuestionVariantGenerator questionVariantGenerator = questionVariantChooseGenerator;
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
