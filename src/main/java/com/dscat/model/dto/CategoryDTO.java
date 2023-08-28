package com.dscat.model.dto;

import com.dscat.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO implements Serializable {
    private Long id;
    private String name;

    public CategoryDTO(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }
}
