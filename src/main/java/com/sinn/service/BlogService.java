package com.sinn.service;

import com.sinn.pojo.Blog;
import com.sinn.utils.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/15
 */
public interface BlogService {
    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Page<Blog> listBlog(String query ,Pageable pageable);

    //专门用于Markdown语法的转换
    Blog getAndCovert(Long id);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    List<Blog> listRecommendBlogTop(Integer size);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();
    void deleteBlog(Long id);

}
