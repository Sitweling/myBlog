package com.sinn.service;

import com.sinn.dao.CommentRepository;
import com.sinn.pojo.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/17
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        List<Comment> commentList = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return eachComment(commentList);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommendId=comment.getParentComment().getId();
        if(parentCommendId!=-1){
            comment.setParentComment(commentRepository.getById(parentCommendId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    /**
     * 循环每个顶级评论节点(顶级评论节点：parentComment==null)
     * 复制一份评论，进行修改，避免在原有的列表上修改
     * @param commentList
     * @return
     */
    private List<Comment> eachComment(List<Comment> commentList){
        List<Comment> commentsView =new ArrayList<>();
        //Copy一个新的List集合
        for(Comment comment : commentList){
            Comment newCom = new Comment();
            BeanUtils.copyProperties(comment,newCom);
            commentsView.add(newCom);
        }
        //随后进行多叉树的扁平化，将多层评论合并到第一级的子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * 多叉树的扁平化，将多层评论合并到第一级的子代集合中
     * 需要递归调用另一个函数
     * @param commentList
     */
    private void combineChildren (List<Comment> commentList){
        for(Comment comment : commentList){
            List<Comment> replyCommentsList = comment.getReplyComments();
            for(Comment replyComment : replyCommentsList){
                recursively(replyComment);
            }
            comment.setReplyComments(tempReplys);
            tempReplys =new ArrayList<>();
        }
    }


    //存放所有迭代出的子代的集合
    private List<Comment> tempReplys =new ArrayList<>();
    /**
     * 递归查找
     * @param comment
     */
    private void recursively(Comment comment){
        //顶节点添加到临时存放集合
        tempReplys.add(comment);
        if(comment.getReplyComments().size()>0){
            List<Comment> commentList = comment.getReplyComments();
            for(Comment reply :commentList){
                recursively(reply);
            }
        }
    }
}
