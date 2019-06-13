package com.bsuir.cognispect.generator.question.questionGenerator;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.generator.question.QuestionVariantGenerator;
import com.bsuir.cognispect.repository.AnswerVariantRepository;
import com.bsuir.cognispect.repository.QuestionVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class QuestionVariantChooseGeneratorImpl implements QuestionVariantGenerator<ChooseAnswer> {
    @Autowired
    private QuestionVariantRepository questionVariantRepository;
    @Autowired
    private AnswerVariantRepository answerVariantRepository;

    @Override
    public QuestionVariant createQuestionVariant(List<ChooseAnswer> answers, Question question) {
        QuestionVariant questionVariant = new QuestionVariant();
        List<AnswerVariant> chooseAnswerVariants = new ArrayList<>();
        questionVariant.setDescription(question.getDescription());
        questionVariant.setTopic(question.getTopic());

        answers.forEach(answer -> {
            ChooseAnswerVariant chooseAnswerVariant = new ChooseAnswerVariant();
            chooseAnswerVariant.setPosition(answers.indexOf(answer));
            chooseAnswerVariant.setCorrect(answer.isCorrect());
            chooseAnswerVariant.setText(answer.getText());
            chooseAnswerVariants.add(chooseAnswerVariant);
        });
        questionVariant.setAnswers(chooseAnswerVariants);
        questionVariantRepository.save(questionVariant);
        answerVariantRepository.saveAll(chooseAnswerVariants);
        return questionVariant;
    }
}
