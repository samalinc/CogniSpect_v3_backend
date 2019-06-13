package com.bsuir.cognispect.generator.answers.answersGenerators;

import com.bsuir.cognispect.entity.ChooseAnswer;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.generator.answers.AnswerConstants;
import com.bsuir.cognispect.generator.answers.AnswerGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnswerChooseGeneratorImpl implements AnswerGenerator<ChooseAnswer> {

    @Override
    public List<ChooseAnswer> generateAnswer(Question question, int substitutionsIndex) {
        Random rand = new Random();
        List<ChooseAnswer> answers = (List<ChooseAnswer>) (List<?>) new ArrayList<>(question.getAnswers()).
                subList(AnswerConstants.BOTTOM_BOUND, AnswerConstants.TOP_BOUND);

        ChooseAnswer rightAnswer = question.getSubstitutions().get(substitutionsIndex).getRightChooseAnswer();
        if (answers.stream().noneMatch(ChooseAnswer::isCorrect)) {
            answers.set(rand.nextInt(answers.size()), rightAnswer);
        }
        return answers;

    }

    @Override
    public QuestionTypeEnum getAnswerType() {
        return QuestionTypeEnum.CHOOSE;
    }
}
