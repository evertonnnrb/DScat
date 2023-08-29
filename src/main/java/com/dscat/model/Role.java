package com.dscat.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
}
