package com.dscat.model.dto;

import com.dscat.service.validation.UserInsertValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@UserInsertValid
public class UserInsertDTO extends UserDTO{

    @NotBlank(message = "password is required")
    private String password;
}
