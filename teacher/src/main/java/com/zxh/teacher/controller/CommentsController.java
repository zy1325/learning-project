package com.zxh.teacher.controller;

import com.zxh.common.common.ResultData;
import com.zxh.teacher.entity.Comments;
import com.zxh.teacher.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxh
 * @since 2024-02-04
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    ICommentsService commentsService;

    @PostMapping("/addPcomment")
    public ResultData addPcomment(@RequestBody Comments comments){
        comments.setCreateTime(LocalDateTime.now());
        commentsService.save(comments);
        return ResultData.ok();
    }

    @GetMapping("/all/{id}")
    public ResultData getCommentsByCourseId(@PathVariable Integer id){
        List<Comments> r = commentsService.getCommentsByCourseId(id);
        return ResultData.ok().setData(r);
    }

    @GetMapping("/delComment/{id}")
    public ResultData delCommentById(@PathVariable Integer id){
        commentsService.delCommentById(id);
        return ResultData.ok();
    }


}
