package com.sinn.dao;

import com.sinn.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/17
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
