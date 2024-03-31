package com.zxh.teacher.controller;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxh.common.common.ResultData;
import com.zxh.teacher.dto.ContentDataDto;
import com.zxh.teacher.entity.Detail;
import com.zxh.teacher.service.IDetailService;
import com.zxh.teacher.vo.contentDataVo;
import com.zxh.teacher.vo.projectNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxh
 * @since 2023-12-22
 */
@RestController
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    IDetailService detailService;

    @PostMapping("/save")
    public void save(@RequestBody List<Detail> contentDataVoList){
        Integer parentId = contentDataVoList.get(0).getParentId();
        QueryWrapper detailQueryWrapper = new QueryWrapper<Detail>().eq("parent_id",parentId);
        detailService.remove(detailQueryWrapper);
        detailService.saveBatch(contentDataVoList);
    }

    @GetMapping("/content/{id}")
    public ResultData getContentById(@PathVariable Integer id){
        List<List<Detail>> dataDtoList = detailService.getContentById(id);
        return ResultData.ok().setData(dataDtoList);
    }

    @PostMapping("/delete")
    public void delContentByUrl(@RequestBody projectNameVo vo){
        QueryWrapper<Detail> delWrapper = new QueryWrapper<Detail>().eq("project_name", vo.getProjectName());
        detailService.remove(delWrapper);
    }

}
