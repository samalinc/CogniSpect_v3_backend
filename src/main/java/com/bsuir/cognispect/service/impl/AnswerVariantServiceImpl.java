package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.AnswerVariant;
import com.bsuir.cognispect.entity.ChooseAnswerVariant;
import com.bsuir.cognispect.entity.MatchAnswerVariant;
import com.bsuir.cognispect.entity.SortAnswerVariant;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.model.answer.test.UserAnswerModel;
import com.bsuir.cognispect.repository.AnswerVariantRepository;
import com.bsuir.cognispect.repository.ChooseAnswerVariantRepository;
import com.bsuir.cognispect.repository.MatchAnswerVariantRepository;
import com.bsuir.cognispect.repository.SortAnswerVariantRepository;
import com.bsuir.cognispect.service.AnswerVariantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void submitAnswer(UserAnswerModel userAnswerModel) {
        AnswerVariant answerVariant = answerVariantRepository.findById(userAnswerModel.getId())
                .orElseThrow(() -> new ResourceNotFoundException("AnswerVariant", userAnswerModel.getId()));

        switch (answerVariant.getQuestionVariant().getType()) {
            case CHOOSE:
            case MULTICHOOSE:
            case SUBSTITUTION:
                submitChooseAnswerVariant(userAnswerModel.getId());
                break;
            case MATCH:
                submitMatchAnswerVariant(userAnswerModel.getId(), userAnswerModel.getValue());
                break;
            case SORT:
                submitSortAnswerVariant(userAnswerModel.getId(), userAnswerModel.getPosition());
                break;
        }
    }

    private ChooseAnswerVariant submitChooseAnswerVariant(UUID chooseAnswerVariantId) {
        ChooseAnswerVariant chooseAnswerVariant = chooseAnswerVariantRepository.findById(chooseAnswerVariantId)
                .orElseThrow(() -> new ResourceNotFoundException("ChooseAnswerVariant", chooseAnswerVariantId));

        chooseAnswerVariant.setStudentChose(true);

        return answerVariantRepository.save(chooseAnswerVariant);
    }

    private MatchAnswerVariant submitMatchAnswerVariant(UUID matchAnswerVariantId, String value) {
        MatchAnswerVariant matchAnswerVariant = matchAnswerVariantRepository.findById(matchAnswerVariantId)
                .orElseThrow(() -> new ResourceNotFoundException("MatchAnswerVariant", matchAnswerVariantId));

        matchAnswerVariant.setStudentValue(value);

        return matchAnswerVariantRepository.save(matchAnswerVariant);
    }

    private SortAnswerVariant submitSortAnswerVariant(UUID sortAnswerVariantId, int position) {
        SortAnswerVariant sortAnswerVariant = sortAnswerVariantRepository.findById(sortAnswerVariantId)
                .orElseThrow(() -> new ResourceNotFoundException("SortAnswerVariant", sortAnswerVariantId));

        sortAnswerVariant.setStudentPosition(position);

        return sortAnswerVariantRepository.save(sortAnswerVariant);
    }
}
