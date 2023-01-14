package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;


    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog

        int id=blog.getId();
        Blog newBlog=blogRepository.findById(id).get();

        List<Image> list=newBlog.getImageList();

      Image image=new Image();
      image.setDimensions(dimensions);
      image.setDescription(description);

      list.add(image);

      newBlog.setImageList(list);
      image.setBlog(newBlog);

      blogRepository.save(newBlog);

//      ImageResponseDto imageDto=new ImageResponseDto();
//
//      imageDto.setId(image.getId());
//      imageDto.setDescription(image.getDescription());
//      imageDto.setDimensions(image.getDimensions());


      return image;
    }

    public void deleteImage(Image image){

       // Image image=findById(image1.getId());

        Blog blog=image.getBlog();

        List<Image> list= blog.getImageList();

        list.remove(image);

        blog.setImageList(list);

        imageRepository2.delete(image);
    }

    public Image findById(int id) {
        Image image=imageRepository2.findById(id).get();
        deleteImage(image);
        return image;
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        if(!imageRepository2.existsById(image)){
            return 0;
        }
        Image image= imageRepository2.findById(image).get();

        int imageSize= Integer.parseInt(image.getDimensions());
        int screenSize= Integer.parseInt(screenDimensions);

        int ans= screenSize/imageSize;
        return ans;
    }
}
