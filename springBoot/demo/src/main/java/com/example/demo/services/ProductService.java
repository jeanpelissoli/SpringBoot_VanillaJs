package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Img;
import com.example.demo.entities.Product;
import com.example.demo.repositories.ImgRepository;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImgRepository fileRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        try {
            return productRepository.findById(id).get();
        } catch(NoSuchElementException e) {
            
            throw new EntityNotFoundException("EntityNotFound Product id:" + id);
        }
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product product) {
        Product productEntity = productRepository.getReferenceById(id);

        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setAmount(product.getAmount());
        productEntity.setCategory(product.getCategory());

        return productRepository.save(productEntity);
    }

    public Product addImg(Long idProduct, String idImg) {
        Product product = productRepository.findById(idProduct).get();

        Img file = fileRepository.findById(idImg).get();

        product.setImg(file);

        return productRepository.save(product);
        
    }
   
}
