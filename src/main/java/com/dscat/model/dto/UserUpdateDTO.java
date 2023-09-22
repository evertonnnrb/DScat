package com.dscat.model.dto;

import com.dscat.service.validation.UserInsertValid;
import com.dscat.service.validation.UserUpdateValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@UserUpdateValid
public class UserUpdateDTO extends UserDTO{

}
