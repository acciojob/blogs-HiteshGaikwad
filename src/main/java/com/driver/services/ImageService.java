package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;


    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog

        Image image = new Image();
        image.setDimensions(dimensions);
        image.setDescription(description);

        List<Image> list=new ArrayList<>();

        int id=blog.getId();

            Blog newBlog = blogRepository.findById(id).get();

             list = newBlog.getImageList();
            list.add(image);

            newBlog.setImageList(list);

            image.setBlog(newBlog);
            blogRepository.save(newBlog);

      return image;
    }

    public void deleteImage(Image image){

       // Image image=findById(image1.getId());
        //888888888888888888
        //if(imageRepository2.existsById(image.getId())){

            Blog blog = image.getBlog();

            List<Image> list = blog.getImageList();

            list.remove(image);

            blog.setImageList(list);

            imageRepository2.delete(image);
       // }
    }

    public Image findById(int id) {

        Image image=new Image();
        //if(imageRepository2.existsById(id)) {
            image = imageRepository2.findById(id).get();
           // deleteImage(image);
       // }
        return image;
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
//        if(!imageRepository2.existsById(image)){
//            return 0;
//        }
//        Image image= imageRepository2.findById(image).get();
//
//        int imageSize= Integer.parseInt(image.getDimensions());
//        int screenSize= Integer.parseInt(screenDimensions);
//
//        int ans= screenSize/imageSize;
        return 0;
    }
}
