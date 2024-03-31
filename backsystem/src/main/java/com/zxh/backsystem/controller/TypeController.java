package com.zxh.backsystem.controller;

import com.zxh.backsystem.entity.Type;
import com.zxh.backsystem.service.ITypeService;
import com.zxh.backsystem.vo.addTypeVo;
import com.zxh.backsystem.vo.delTypeVo;
import com.zxh.backsystem.vo.typeListVo;
import com.zxh.common.common.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zxh
 * @since 2023-12-12
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    ITypeService typeService;

    @GetMapping("/list")
    public ResultData getTypeList() {
        List<Type> list = typeService.getTypeList();
        return ResultData.ok().setData(list);
    }

    @PostMapping("/add")
    public ResultData addType(@RequestBody addTypeVo add) {

        typeService.addType(add);
        return ResultData.ok();
    }

    @PostMapping("/delete")
    public ResultData delType(@RequestBody delTypeVo del) {
        typeService.delType(del);
        return ResultData.ok();
    }

    @GetMapping("/type/{id}")
    public ResultData getTypesById(@PathVariable Integer id){
        List<Type> types = typeService.getTypesById(id);
        return ResultData.ok().setData(types);
    }

    @GetMapping("/{id}")
    public ResultData getTypeById(@PathVariable Integer id){
        Type byId = typeService.getById(id);
        return ResultData.ok().setData(byId);
    }

    @GetMapping("/pid/{id}")
    public ResultData getTypeIdsByPid(@PathVariable Integer id){
        List<Integer> ids = typeService.getTypeIdsByPid(id);
        return ResultData.ok().setData(ids);
    }
}
