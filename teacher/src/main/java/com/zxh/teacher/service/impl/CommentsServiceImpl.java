package com.zxh.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxh.teacher.entity.Comments;
import com.zxh.teacher.mapper.CommentsMapper;
import com.zxh.teacher.service.ICommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxh
 * @since 2024-02-04
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

    @Override
    public List<Comments> getCommentsByCourseId(Integer id) {
        //找出所有父评论
        QueryWrapper<Comments> course_id = new QueryWrapper<Comments>().eq("course_id",id).eq("parent_id",0);
        //找出所有子评论
        QueryWrapper<Comments> course_id2 = new QueryWrapper<Comments>().eq("course_id",id).ne("parent_id",0);
        List<Comments> listParent = this.list(course_id);
        List<Comments> listChildren = this.list(course_id2);
        for (Comments comments : listParent) {
            List<Comments> collect = listChildren.stream().filter(i -> i.getParentId() == comments.getId()).collect(Collectors.toList());
            comments.setChildren(collect);
        }
        return listParent;
    }

    @Override
    public void delCommentById(Integer id) {
        Comments delCommentEntity = this.getById(id);
        //如果是有回复的评论,需要把子评论一同删除
        if(delCommentEntity.getParentId() == 0){
            QueryWrapper<Comments> delQueryWrapper = new QueryWrapper<Comments>().eq("parent_id", delCommentEntity.getId());
            this.remove(delQueryWrapper);
        }
        this.removeById(id);
    }
}
