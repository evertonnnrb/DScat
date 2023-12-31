package com.dscat.model.dto;

import com.dscat.model.Category;
import com.dscat.model.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO implements Serializable {

    private Long id;

    @NotBlank(message = "data cannot be empty")
    private String name;

    @NotBlank(message = "data cannot be empty")
    private String description;

    @Positive(message = "priva must be positive")
    @NotBlank(message = "data cannot be empty")
    private Double price;

    private String imgUrl;

    @PastOrPresent(message = "date is invalid")
    private Instant date;

    @Setter(AccessLevel.NONE)
    private List<CategoryDTO> categories = new ArrayList<CategoryDTO>();

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.date = entity.getDate();
    }

    public ProductDTO(Product entity, Set<Category> categories) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
    }

}
