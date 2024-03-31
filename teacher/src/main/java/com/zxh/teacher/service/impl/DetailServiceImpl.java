package com.zxh.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxh.teacher.dto.ContentDataDto;
import com.zxh.teacher.entity.Detail;
import com.zxh.teacher.mapper.DetailMapper;
import com.zxh.teacher.service.IDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxh
 * @since 2023-12-22
 */
@Service
public class DetailServiceImpl extends ServiceImpl<DetailMapper, Detail> implements IDetailService {

    @Override
    public List<List<Detail>> getContentById(Integer id) {
        QueryWrapper<Detail> parent_id = new QueryWrapper<Detail>().eq("parent_id", id);
        List<Detail> list = this.list(parent_id);
        //将获取的课件信息按照升序排序并分组
        Map<Integer, List<Detail>> listMap = list.stream().sorted(Comparator.comparing(Detail::getSort)).collect(Collectors.groupingBy(Detail::getSort));
        List<List<Detail>> dataDtoList = new ArrayList<>();
        listMap.forEach((key,value) ->{
            dataDtoList.add(value);
        });
        return dataDtoList;
    }
}
