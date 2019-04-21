package com.bsuir.cognispect.generator.question.questionGenerator;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.generator.question.QuestionVariantGenerator;
import com.bsuir.cognispect.repository.AnswerVariantRepository;
import com.bsuir.cognispect.repository.QuestionVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class QuestionVariantChooseGeneratorImpl implements QuestionVariantGenerator<Answer> {
    @Autowired
    private QuestionVariantRepository questionVariantRepository;
    @Autowired
    private AnswerVariantRepository answerVariantRepository;

    @Override
    public QuestionVariant createQuestionVariant(List<Answer> answers, Question question, TestVariant testVariant) {
        QuestionVariant questionVariant = new QuestionVariant();
        List<AnswerVariant> answerVariants = new ArrayList<>();
        questionVariant.setDescription(question.getDescription());
        questionVariant.setTopic(question.getTopic());
        questionVariant.setTestVariant(testVariant);

        answers.forEach(answer -> {
            AnswerVariant answerVariant = new AnswerVariant();
            answerVariant.setPosition(answers.indexOf(answer));
            answerVariant.setCorrect(answer.isCorrect());
            answerVariant.setText(answer.getText());
            answerVariants.add(answerVariant);
        });
        questionVariant.setAnswers(answerVariants);
        questionVariantRepository.save(questionVariant);
        answerVariantRepository.saveAll(answerVariants);
        return questionVariant;
    }
}
