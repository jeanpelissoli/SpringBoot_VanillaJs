package com.example.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    
    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        
        return ResponseEntity.ok().body(products);
    }

    
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);

        return ResponseEntity.ok().body(product);
    }

    
    @PostMapping(value = "/products")
    public ResponseEntity<Product> save(@RequestBody Product product) {

        if(product.getDescription() == "") {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();         
        }
        
        Product productSaved = productService.save(product);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}")
        .buildAndExpand(productSaved.getId()).toUri();

        return ResponseEntity.created(uri).body(productSaved);
    }

    
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //UPDATE
    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.update(id, product);

        return ResponseEntity.ok().body(updatedProduct);
    }

}
