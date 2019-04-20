package com.bsuir.cognispect.generator.question.questionGenerator;

import com.bsuir.cognispect.entity.Answer;
import com.bsuir.cognispect.entity.AnswerVariant;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.QuestionVariant;
import com.bsuir.cognispect.generator.question.QuestionVariantGenerator;

import java.util.ArrayList;
import java.util.List;

public class QuestionVariantChooseGeneratorImpl implements QuestionVariantGenerator<Answer> {

    @Override
    public QuestionVariant createQuestionVariant(List<Answer> answers, Question question) {
        QuestionVariant questionVariant = new QuestionVariant();
        List<AnswerVariant> answerVariants = new ArrayList<>();
        questionVariant.setDescription(question.getDescription());
        questionVariant.setTopic(question.getTopic());

        answers.forEach(answer -> {
            AnswerVariant answerVariant = new AnswerVariant();
            answerVariant.setPosition(answers.indexOf(answer));
            answerVariant.setCorrect(answer.isCorrect());
            answerVariant.setText(answer.getText());
            answerVariants.add(answerVariant);
        });

        questionVariant.setAnswers(answerVariants);
        return questionVariant;
    }
}
