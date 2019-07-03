package com.bsuir.cognispect.generator.question.questionGenerator;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.generator.question.QuestionVariantGenerator;
import com.bsuir.cognispect.repository.ChooseAnswerVariantRepository;
import com.bsuir.cognispect.repository.QuestionVariantRepository;
import com.bsuir.cognispect.repository.TestTemplateQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionVariantChooseGeneratorImpl implements QuestionVariantGenerator<ChooseAnswer> {
    @Autowired
    private QuestionVariantRepository questionVariantRepository;
    @Autowired
    private ChooseAnswerVariantRepository chooseAnswerVariantRepository;
    @Autowired
    private TestTemplateQuestionRepository testTemplateQuestionRepository;

    @Override
    public QuestionVariant createQuestionVariant(List<ChooseAnswer> answers, Question question) {
        QuestionVariant questionVariant = new QuestionVariant();
        List<ChooseAnswerVariant> chooseAnswerVariants = new ArrayList<>();
        List<AnswerVariant> answerVariants = new ArrayList<>();

        questionVariant.setDescription(question.getDescription());
        questionVariant.setTopic(question.getTopic());
        questionVariant.setType(question.getType());
        questionVariant.setCost(testTemplateQuestionRepository.findByQuestionId(question.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Question in TestTemplateQuestion", question.getId()))
                .getQuestionCost());
        questionVariantRepository.save(questionVariant);

        answers.forEach(answer -> {
            ChooseAnswerVariant chooseAnswerVariant = new ChooseAnswerVariant();
            chooseAnswerVariant.setPosition(answers.indexOf(answer));
            chooseAnswerVariant.setCorrect(answer.isCorrect());
            chooseAnswerVariant.setText(answer.getText());
            chooseAnswerVariant.setQuestionVariant(questionVariant);
            chooseAnswerVariants.add(chooseAnswerVariant);
            answerVariants.add(chooseAnswerVariant);
        });
        questionVariant.setAnswers(answerVariants);
        chooseAnswerVariantRepository.saveAll(chooseAnswerVariants);
        return questionVariant;
    }
}
