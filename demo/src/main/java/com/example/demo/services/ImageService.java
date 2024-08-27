package com.example.demo.services;

import com.example.demo.models.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setContent(file.getBytes());
        image.setMimeType(file.getContentType()); // Ensure MIME type is set correctly
        return imageRepository.save(image);
    }

    // Method to retrieve image by ID
    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }
}
