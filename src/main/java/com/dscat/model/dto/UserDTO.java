package com.dscat.model.dto;

import com.dscat.model.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    @NotBlank(message = "name cannot be null")
    private String firstName;

    @NotBlank(message = "name cannot be null")
    private String lastName;

    @Email(message = "email invalid")
    private String email;

    @Setter(AccessLevel.NONE)
    Set<RoleDTO> roleDTOS = new HashSet<>();

    public UserDTO(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        user.getRoles().forEach(role -> this.roleDTOS.add(new RoleDTO(role)));
    }


}
