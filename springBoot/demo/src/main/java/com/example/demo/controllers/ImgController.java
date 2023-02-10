package com.example.demo.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Img;
import com.example.demo.entities.Product;

import com.example.demo.services.ImgService;
import com.example.demo.services.ProductService;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
public class ImgController {
    
    @Autowired
    private ImgService imgService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/img")
    public ResponseEntity<Img> save(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        
        Img img = imgService.save(multipartFile);

        return ResponseEntity.ok().body(img);
    }

    @PostMapping(value = "/img/{id_product}")
    public ResponseEntity<Img> setToProduct(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id_product) throws IOException {
        
        Img img = imgService.save(multipartFile);

        Product product = productService.addImg(id_product, img.getId());
        productService.save(product);
       

        return ResponseEntity.ok().body(img);
    }


    @RequestMapping(value = "/img/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void showImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        
        Img img = imgService.findById(id);

        response.setContentType(img.getType());
        response.getOutputStream().write(img.getFileData());
        response.getOutputStream().close();
    }


}
