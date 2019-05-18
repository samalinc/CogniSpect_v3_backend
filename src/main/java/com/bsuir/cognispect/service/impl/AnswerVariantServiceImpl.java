package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.ChooseAnswerVariant;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.model.answer.UserAnswersModel;
import com.bsuir.cognispect.repository.AnswerVariantRepository;
import com.bsuir.cognispect.repository.ChooseAnswerVariantRepository;
import com.bsuir.cognispect.repository.MatchAnswerVariantRepository;
import com.bsuir.cognispect.repository.SortAnswerVariantRepository;
import com.bsuir.cognispect.service.AnswerVariantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class AnswerVariantServiceImpl implements AnswerVariantService {
    @Autowired
    private AnswerVariantRepository answerVariantRepository;
    @Autowired
    private ChooseAnswerVariantRepository chooseAnswerVariantRepository;
    @Autowired
    private SortAnswerVariantRepository sortAnswerVariantRepository;
    @Autowired
    private MatchAnswerVariantRepository matchAnswerVariantRepository;

    @Override
    public void submitAnswers(UserAnswersModel userAnswersModel) {
        List<ChooseAnswerVariant> chooseAnswerVariants = submitChooseAnswers(userAnswersModel.getAnswersIds());
    }

    private List<ChooseAnswerVariant> submitChooseAnswers(List<UUID> chooseAnswersIds) {
        List<ChooseAnswerVariant> userAnswers = new ArrayList<>();

        for (UUID answerId : chooseAnswersIds) {
            ChooseAnswerVariant chooseAnswerVariant = chooseAnswerVariantRepository.findById(answerId)
                    .orElseThrow(() -> new ResourceNotFoundException("ChooseAnswerVariant", answerId));
            chooseAnswerVariant.setStudentChose(true);

            userAnswers.add(answerVariantRepository.save(chooseAnswerVariant));
        }

        return userAnswers;
    }
}
