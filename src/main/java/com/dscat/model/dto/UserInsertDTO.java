package com.dscat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO extends UserDTO{
    private String passwd;
}
