package com.niit.imageService.repository;

import com.niit.imageService.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ImageRepository extends MongoRepository<Image,String> {

    List<Image> findByUsername(String username);

    String findByImageNameAndUsername(String imageName, String username);

    Image findByImageName(String imageName);
}
