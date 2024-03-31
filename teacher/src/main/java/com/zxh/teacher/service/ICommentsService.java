package com.zxh.teacher.service;

import com.zxh.teacher.entity.Comments;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxh
 * @since 2024-02-04
 */
public interface ICommentsService extends IService<Comments> {

    List<Comments> getCommentsByCourseId(Integer id);

    void delCommentById(Integer id);
}
