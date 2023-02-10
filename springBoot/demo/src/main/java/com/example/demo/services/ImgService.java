package com.example.demo.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Img;
import com.example.demo.repositories.ImgRepository;

import org.springframework.util.StringUtils;

@Service
public class ImgService {
    
    @Autowired
    private ImgRepository imgRepository;

    public Img save(MultipartFile file) throws IOException {

        String imgName = StringUtils.cleanPath(file.getOriginalFilename());
        Img f = new Img(null, imgName, file.getContentType(), file.getBytes());

        return imgRepository.save(f);
    }

    public Img saveByByte(Img img) throws IOException {

        return imgRepository.save(img);
    }

    public Img findById(String id) {
        
        return imgRepository.findById(id).get();
    }
}
