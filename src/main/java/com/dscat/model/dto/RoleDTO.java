package com.dscat.model.dto;

import com.dscat.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {
    private Long id;
    private String authority;

    public RoleDTO(Role role){
        super();
        id = role.getId();
        authority = role.getAuthority();
    }

}
