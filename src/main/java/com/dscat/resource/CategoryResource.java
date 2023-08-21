package com.dscat.resource;

import com.dscat.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Eletronicos"));
        categories.add(new Category(2, "Computação"));
        return ResponseEntity.ok().body(categories);
    }
}
