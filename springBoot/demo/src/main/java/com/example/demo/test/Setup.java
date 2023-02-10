package com.example.demo.test;

import java.util.Arrays;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import com.example.demo.entities.Category;
import com.example.demo.entities.Img;
import com.example.demo.entities.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ImgService;

@Configuration 
@Profile("test") 
public class Setup implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImgService imgService;

    @Override
    public void run(String... args) throws Exception {
    
        Category c1 = new Category(null, "Transistores", null);
        Category c2 = new Category(null, "Power", null);
        Category c3 = new Category(null, "Microcontrollers", null);

        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));

        Product p1 = new Product(null, "Pnp", 20d, 10d, null, null);
        Product p2 = new Product(null, "Npn", 20d, 6d, null, null);
        Product p3 = new Product(null, "Pic16f877a", 40d, 10d, null, null);
        Product p4 = new Product(null, "Arduino", 20d, 5d, null, null);
        Product p5 = new Product(null, "Tomahawk 500W", 100d, 10d, null, null);
        Product p6 = new Product(null, "PowerX 230W", 120d, 7d, null, null);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

        p1.setCategory(c1);
        p2.setCategory(c1);
        p3.setCategory(c3);
        p4.setCategory(c3);
        p5.setCategory(c2);
        p6.setCategory(c2);
       
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));


        BufferedImage bImage1 = ImageIO.read(new File("demo/src/main/java/com/example/demo/test/img1.jpg"));
        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        ImageIO.write(bImage1, "jpg", bos1);
        byte [] data1 = bos1.toByteArray();

        BufferedImage bImage2 = ImageIO.read(new File("demo/src/main/java/com/example/demo/test/img2.jpg"));
        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        ImageIO.write(bImage2, "jpg", bos2);
        byte [] data2 = bos2.toByteArray();

        BufferedImage bImage3 = ImageIO.read(new File("demo/src/main/java/com/example/demo/test/img3.jpg"));
        ByteArrayOutputStream bos3 = new ByteArrayOutputStream();
        ImageIO.write(bImage3, "jpg", bos3);
        byte [] data3 = bos3.toByteArray();

        BufferedImage bImage4 = ImageIO.read(new File("demo/src/main/java/com/example/demo/test/img4.jpg"));
        ByteArrayOutputStream bos4 = new ByteArrayOutputStream();
        ImageIO.write(bImage4, "jpg", bos4);
        byte [] data4 = bos4.toByteArray();

        BufferedImage bImage5 = ImageIO.read(new File("demo/src/main/java/com/example/demo/test/img5.jpg"));
        ByteArrayOutputStream bos5 = new ByteArrayOutputStream();
        ImageIO.write(bImage5, "jpg", bos5);
        byte [] data5 = bos5.toByteArray();

        BufferedImage bImage6 = ImageIO.read(new File("demo/src/main/java/com/example/demo/test/img6.jpg"));
        ByteArrayOutputStream bos6 = new ByteArrayOutputStream();
        ImageIO.write(bImage6, "jpg", bos6);
        byte [] data6 = bos6.toByteArray();

        Img img1 = new Img(null, "bebop", "jpg", data1);
        Img img2 = new Img(null, "spike", "jpg", data2);
        Img img3 = new Img(null, "feye", "jpg", data3);
        Img img4 = new Img(null, "ed", "jpg", data4);
        Img img5 = new Img(null, "jetblack", "jpg", data5);
        Img img6 = new Img(null, "ein", "jpg", data6);

        imgService.saveByByte(img1);
        imgService.saveByByte(img2);
        imgService.saveByByte(img3);
        imgService.saveByByte(img4);
        imgService.saveByByte(img5);
        imgService.saveByByte(img6);

        p1.setImg(img1);
        p2.setImg(img2);
        p3.setImg(img3);
        p4.setImg(img4);
        p5.setImg(img5);
        p6.setImg(img6);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

    }
}