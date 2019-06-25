package com.bsuir.cognispect.validation.validator;

import com.bsuir.cognispect.entity.enums.RoleEnum;
import com.bsuir.cognispect.util.error.ApiSubError;
import com.bsuir.cognispect.util.error.ApiValidationError;
import com.bsuir.cognispect.validation.groups.AccountGroupsValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
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

    public <T> List<ApiSubError> validateByUserRole(T object, RoleEnum role) {
        List<ApiSubError> apiSubErrorList;

        switch (role) {
            case STUDENT: {
                apiSubErrorList = validate(
                        object, AccountGroupsValidation.StudentValidation.class, Default.class);
                break;
            }

            case TEACHER: {
                apiSubErrorList = validate(
                        object, AccountGroupsValidation.TeacherValidation.class, Default.class);
                break;
            }
            default:
                apiSubErrorList = validate(
                        object, AccountGroupsValidation.TeacherValidation.class, Default.class);
        }

        return apiSubErrorList;
    }
}
