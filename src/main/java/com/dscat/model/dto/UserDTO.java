package com.dscat.model.dto;

import com.dscat.model.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
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
