package com.sinn.service;

import com.sinn.pojo.Comment;

import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/17
 */
public interface CommentService {

    //获取列表
    List<Comment> listCommentByBlogId(Long blogId);

    //保存
    Comment saveComment(Comment comment);
}
