package com.driver.controller;

import com.driver.ResponseDto.ImageResponseDto;
import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/create")
    public ResponseEntity<ImageResponseDto> createAndReturn(@RequestBody() Blog blog,
                                                            @RequestParam("description") String description,
                                                            @RequestParam("dimensions") String dimensions) {
        ImageResponseDto image = imageService.createAndReturn(blog,description,dimensions);
        return new ResponseEntity<>(image, HttpStatus.CREATED);
    }

    @GetMapping("/countImagesInScreen/{id}/{screenDimensions}")
    public ResponseEntity<Integer> countImagesInScreen(@PathVariable("id") int id, @PathVariable("screenDimensions") String screenDimensions){
       int count= imageService.countImagesInScreen(id,screenDimensions);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable("id") int id) {
        imageService.findById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



