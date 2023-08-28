package com.dscat.service;

import com.dscat.exceptions.DatabaseIntegrityException;
import com.dscat.exceptions.ResourceNotFoundException;
import com.dscat.model.Product;
import com.dscat.model.dto.ProductDTO;
import com.dscat.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(x -> new ProductDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> Product = productRepository.findById(id);
        Product entity = Product.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDate(product.getDate());
        product.setPrice(product.getPrice());
        product.setDescription(product.getDescription());
        product.setImgUrl(product.getImgUrl());
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product product = productRepository.getReferenceById(id);
            product.setName(dto.getName());
            product = productRepository.save(product);
            return new ProductDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found {" + id + "}");
        }
    }

    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found {" + id + "}");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("Integrity database violation");
        }
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPages(PageRequest pageRequest) {
        Page<Product> products = productRepository.findAll(pageRequest);
        return products.map(x -> new ProductDTO(x));

    }
}
