package com.niit.imageService.service;

import com.niit.imageService.exception.ImageNotFoundException;
import com.niit.imageService.model.Image;
import com.niit.imageService.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(MultipartFile file,String username) throws Exception {
        try {
            Image image=new Image( file.getOriginalFilename(), file.getContentType(),file.getBytes());
            image.setUsername(username);
            return  imageRepository.save(image);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public List<Image> getAllImages(String username) {
        return imageRepository.findByUsername(username);
    }

    @Override
    public boolean deleteImage(String imageName,String username) throws ImageNotFoundException {
        boolean flag=false;
        System.out.println("hi");
        if(imageRepository.findByImageNameAndUsername(imageName,username).isEmpty())
        {
            throw new ImageNotFoundException();
        }
        else {
            imageRepository.deleteById(imageName);
            flag = true;
        }
        return flag;
    }

    @Override
    public Image findByImageName(String imageName) throws ImageNotFoundException {
        Image image=imageRepository.findByImageName(imageName);
        if(image==null)
        {
            throw new ImageNotFoundException();
        }
        return image;
    }
}
