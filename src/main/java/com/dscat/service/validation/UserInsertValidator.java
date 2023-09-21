package com.dscat.service.validation;

import com.dscat.exceptions.FieldMessage;
import com.dscat.model.User;
import com.dscat.model.dto.UserInsertDTO;
import com.dscat.repository.UserRepository;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserInsertDTO userInsertDTO,
                           ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessageList = new ArrayList<>();

        User email = userRepository.findByEmail(userInsertDTO.getEmail());
        if (email != null) {
            fieldMessageList.add(new FieldMessage("email","Email already exists"));
        }

        for (FieldMessage e : fieldMessageList) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return fieldMessageList.isEmpty();
    }
}
