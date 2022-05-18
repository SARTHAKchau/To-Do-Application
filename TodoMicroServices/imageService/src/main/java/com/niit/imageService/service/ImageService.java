package com.niit.imageService.service;

import com.niit.imageService.exception.ImageNotFoundException;
import com.niit.imageService.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    Image saveImage(MultipartFile image,String username) throws Exception;
    List<Image> getAllImages(String username);
     boolean deleteImage(String imageName,String username) throws ImageNotFoundException;
     Image findByImageName(String imageName) throws ImageNotFoundException;
}
