package com.dscat.service;

import com.dscat.exceptions.EntityNotFoundException;
import com.dscat.model.Category;
import com.dscat.model.dto.CategoryDTO;
import com.dscat.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        Category entity = category.orElseThrow(()->new EntityNotFoundException("Entity not found"));
        return new CategoryDTO(entity);
    }
}
