package com.bsuir.cognispect.generator.answers.answersGenerators;

import com.bsuir.cognispect.entity.Answer;
import com.bsuir.cognispect.entity.ChooseAnswer;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.generator.answers.AnswerConstants;
import com.bsuir.cognispect.generator.answers.AnswerGenerator;
import com.bsuir.cognispect.repository.ChooseAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class AnswerChooseGeneratorImpl implements AnswerGenerator<ChooseAnswer> {
    @Autowired
    private ChooseAnswerRepository chooseAnswerRepository;

    @Override
    public List<ChooseAnswer> generateAnswer(Question question, int substitutionsIndex) {
        Random rand = new Random();
        List<ChooseAnswer> answers = new ArrayList<>();

        for (Answer answer : question.getAnswers()) {
            Optional<ChooseAnswer> a = chooseAnswerRepository.findById(answer.getId());
            answers.add(a.get());
        }

        answers = answers.subList(AnswerConstants.BOTTOM_BOUND, AnswerConstants.TOP_BOUND);

        /*ChooseAnswer rightAnswer = question.getSubstitutions().get(substitutionsIndex).getRightChooseAnswer();
        if (answers.stream().noneMatch(ChooseAnswer::isCorrect)) {
            answers.set(rand.nextInt(answers.size()), rightAnswer);
        }*/
        return answers;

    }

    @Override
    public QuestionTypeEnum getAnswerType() {
        return QuestionTypeEnum.CHOOSE;
    }
}
