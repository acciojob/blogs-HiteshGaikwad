package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    @Autowired
    ImageRepository imageRepository;

    public List<Blog> showBlogs(){
        //find all blogs
        List<Blog> list=new ArrayList<>();
        list=blogRepository1.findAll();
        return list;
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {

       // create a blog at the current time
        Blog blog= new Blog();
        blog.setContent(content);
        blog.setTitle(title);
        List<Blog> lisOfBlog=new ArrayList<>();

        User user= userRepository1.findById(userId).get();
        lisOfBlog=user.getBlogList();

        lisOfBlog.add(blog);

        user.setBlogList(lisOfBlog);

        blog.setUser(user);
        userRepository1.save(user);

       // updating the blog details

      // Updating the userInformation and changing its blogs

    }

    public Blog findBlogById(int blogId){
        //find a blog
        Blog blog=new Blog();
        if(blogRepository1.existsById(blogId)) {
            blog = blogRepository1.findById(blogId).get();
        }
        return  blog;
    }

    public void addImage(Integer blogId, String description, String dimensions){

        //add an image to the blog after creating it

        Image image=new Image();
        image.setDimensions(dimensions);
        image.setDescription(description);

        Blog blog= blogRepository1.findById(blogId).get();

        List<Image> imageList=new ArrayList<>();

        imageList= blog.getImageList();

        imageList.add(image);

        blog.setImageList(imageList);
        image.setBlog(blog);

        //blogRepository1.save(blog);
        //8888888888888888888888
        imageRepository.save(image);
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog= blogRepository1.findById(blogId).get();

        List<Image> imageList=new ArrayList<>();

        imageList=blog.getImageList();
        for(Image image: imageList){
            imageRepository.delete(image);
        }

        User user= new User();
              user=blog.getUser();
        List<Blog> blogList=new ArrayList<>();

        blogList =user.getBlogList();

        blogList.remove(blog);

        user.setBlogList(blogList);

        blogRepository1.delete(blog);

        userRepository1.save(user);
    }
}
