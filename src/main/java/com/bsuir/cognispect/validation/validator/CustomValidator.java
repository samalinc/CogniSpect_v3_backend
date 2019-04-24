package com.bsuir.cognispect.validation.validator;

import com.bsuir.cognispect.util.error.ApiSubError;
import com.bsuir.cognispect.util.error.ApiValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Component
public class CustomValidator {
    @Autowired
    private Validator validator;

    public <T> List<ApiSubError> validate(T object, Class<?>... groups) {
        List<ApiSubError> apiSubErrorList = null;

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
        if (constraintViolations.size() > 0) {
            apiSubErrorList = new ArrayList<>();
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> constraintViolation = iterator.next();
                apiSubErrorList.add(new ApiValidationError(
                        constraintViolation.getRootBeanClass().toString(),
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getInvalidValue(),
                        constraintViolation.getMessage()
                ));
            }
        }
        return apiSubErrorList;
    }
}
