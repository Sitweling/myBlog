package com.sinn.service;

import com.sinn.pojo.Blog;
import com.sinn.utils.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/15
 */
public interface BlogService {
    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query ,Pageable pageable);



    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    List<Blog> listRecommendBlogTop(Integer size);
    void deleteBlog(Long id);

}
