package com.example.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Category;
import com.example.demo.services.CategoryService;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;


    //READ
    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        
        return ResponseEntity.ok().body(categories);
    }

    //READ
    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);

        return ResponseEntity.ok().body(category);
    }

    //CREATE
    @PostMapping(value = "/categories")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        Category categorySaved = categoryService.save(category);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/categorias/{id}")
        .buildAndExpand(categorySaved.getId()).toUri();

        return ResponseEntity.created(uri).body(categorySaved);
    }

    

    //DELETE
    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //UPDATE
    @PutMapping(value = "/categorias/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.update(id, category);

        return ResponseEntity.ok().body(updatedCategory);
    }

    @GetMapping(value = "/categories/{id_category}/addProduct/{id_product}")
    public ResponseEntity<Category> addProduto(@PathVariable Long id_product, @PathVariable Long id_category) {
        Category category = categoryService.addProduct(id_category, id_product);

        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping(value = "/categories/{id_category}/removeProduct/{id_product}")
    public ResponseEntity<Category> removeCategoria(@PathVariable Long id_product, @PathVariable Long id_category) {
        Category category = categoryService.removeProduct(id_category, id_product);

        return ResponseEntity.ok().body(category);
    }
}
