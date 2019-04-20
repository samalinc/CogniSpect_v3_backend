package com.bsuir.cognispect.generator.answers.answersGenerators;

import com.bsuir.cognispect.entity.Answer;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.generator.answers.AnswerConstants;
import com.bsuir.cognispect.generator.answers.AnswerGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnswerChooseGeneratorImpl implements AnswerGenerator<Answer> {

    @Override
    public List<Answer> generateAnswer(Question question, int substitutionsIndex) {
        Random rand = new Random();
        List<Answer> answers = new ArrayList<>(question.getAnswers()).
                subList(AnswerConstants.BOTTOM_BOUND, AnswerConstants.TOP_BOUND);

        Answer rightAnswer = question.getSubstitutions().get(substitutionsIndex).getRightAnswer();
        if (answers.stream().noneMatch(Answer::isCorrect)) {
            answers.set(rand.nextInt(answers.size()), rightAnswer);
        }
        return answers;

    }

    @Override
    public QuestionTypeEnum getAnswerType() {
        return QuestionTypeEnum.CHOOSE;
    }
}
