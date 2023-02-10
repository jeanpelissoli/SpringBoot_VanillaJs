package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        try {
            return categoryRepository.findById(id).get();
        } catch(NoSuchElementException e) {
            
            throw new EntityNotFoundException("EntityNotFound Category id:" + id);
        }
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category update(Long id, Category category) {
        Category categoryEntity = categoryRepository.getReferenceById(id);

        categoryEntity.setDescription(category.getDescription());
        return categoryRepository.save(categoryEntity);
    }

    @Autowired
    private ProductRepository productRepository;

    public Category addProduct(Long idCategory, Long idProduct) {
        Product product = productRepository.findById(idProduct).get();
        Category category = categoryRepository.findById(idCategory).get();

        category.getProducts().add(product);
        categoryRepository.save(category);

        return category;
    }

    public Category removeProduct(Long idCategory, Long idProduct) {
        Product product = productRepository.findById(idProduct).get();
        Category category = categoryRepository.findById(idCategory).get();

        category.getProducts().remove(product);
        categoryRepository.save(category);
        return category;
    }
}
