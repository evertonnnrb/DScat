package com.dscat.service.validation;

import com.dscat.exceptions.FieldMessage;
import com.dscat.model.User;
import com.dscat.model.dto.UserInsertDTO;
import com.dscat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserInsertDTO> {


    private HttpServletRequest request;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserUpdateValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserInsertDTO userInsertDTO,
                           ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessageList = new ArrayList<>();

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));


        User email = userRepository.findByEmail(userInsertDTO.getEmail());
        if (email != null && userId != userInsertDTO.getId()) {
            fieldMessageList.add(new FieldMessage("email", "Email already exists"));
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
