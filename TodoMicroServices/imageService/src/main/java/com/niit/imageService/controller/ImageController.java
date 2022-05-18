package com.niit.imageService.controller;

import com.niit.imageService.exception.ImageNotFoundException;
import com.niit.imageService.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/image")
@CrossOrigin(origins = "*")
public class ImageController {

    private ResponseEntity responseEntity;
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("addImage/{username}")
    public ResponseEntity<?> addImage(@RequestParam("image") MultipartFile image,@PathVariable("username") String username) throws Exception {
        try {
            imageService.saveImage(image,username);
            return new ResponseEntity<>("Uploaded successfully...",HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("getImages/{username}")
    public ResponseEntity<?> getImages(@PathVariable("username") String username)
    {
        return new ResponseEntity<>(imageService.getAllImages(username), HttpStatus.OK);
    }

    @DeleteMapping("deleteImage/{username}/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable("imageId") String imageName,@PathVariable("username") String username) throws ImageNotFoundException
    {
        try {
            imageService.deleteImage(imageName,username);
            responseEntity=new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
        }
        catch (ImageNotFoundException e)
        {
            throw new ImageNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("getImage/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable("imageId") String imageName) throws ImageNotFoundException
    {
        try {
            responseEntity=new ResponseEntity<>(imageService.findByImageName(imageName),HttpStatus.OK);
        }
        catch (ImageNotFoundException e)
        {
            throw new ImageNotFoundException();
        }
        return responseEntity;
    }



}
