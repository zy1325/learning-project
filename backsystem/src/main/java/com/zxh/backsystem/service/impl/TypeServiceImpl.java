package com.zxh.backsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxh.backsystem.entity.Type;
import com.zxh.backsystem.mapper.TypeMapper;
import com.zxh.backsystem.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxh.backsystem.vo.addTypeVo;
import com.zxh.backsystem.vo.delTypeVo;
import com.zxh.backsystem.vo.typeListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zxh
 * @since 2023-12-12
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Autowired
    TypeMapper typeMapper;

    @Override
    public List<Type> getTypeList() {
        QueryWrapper<Type> TypeQueryWrapper = new QueryWrapper<>();
        List<Type> types = typeMapper.selectList(TypeQueryWrapper);
        //过滤收集所有父节点
        List<Type> pType = types.stream().filter(item -> item.getPid() == 0).collect(Collectors.toList());
        //过滤收集所有子节点
        List<Type> cType = types.stream().filter(item -> item.getPid() != 0).collect(Collectors.toList());

        List<Type> list = new ArrayList<>();
        for (Type type : pType) {
            List<Type> cList = new ArrayList<>();
            for (Type type1 : cType) {
                if (type1.getPid() == type.getId()) {
                    type1.setLabel(type1.getName());
                    type1.setValue(type1.getId().toString());
                    cList.add(type1);
                }
            }
            type.setChildren(cList);
            type.setLabel(type.getName());
            type.setValue(type.getId().toString());
            list.add(type);
        }
        return list;
    }

    @Override
    public void addType(addTypeVo add) {
        Type type = new Type();
        type.setName(add.getName());
        type.setPid(add.getId());
        this.save(type);
    }

    @Override
    public void delType(delTypeVo del) {
        if (del.getPid() == 0) {
            QueryWrapper<Type> typeQueryWrapper = new QueryWrapper<Type>().eq("pid", del.getId());
            this.remove(typeQueryWrapper);
            this.removeById(del.getId());
        } else {
            this.removeById(del.getId());
        }
    }

    @Override
    public List<Type> getTypesById(Integer id) {
        Type type = this.getById(id);
        if(type.getPid()!= 0){
            QueryWrapper<Type> typeQueryWrapper = new QueryWrapper<Type>().eq("pid", type.getPid());
            List<Type> types = typeMapper.selectList(typeQueryWrapper);
            return types;
        }else{
            QueryWrapper<Type> typeQueryWrapper = new QueryWrapper<Type>().eq("pid",id);
            List<Type> types = typeMapper.selectList(typeQueryWrapper);
            return types;
        }
    }

    @Override
    public List<Integer> getTypeIdsByPid(Integer id) {
        QueryWrapper<Type> pid = new QueryWrapper<Type>().eq("pid", id);
        List<Type> list = this.list(pid);
        List<Integer> collect = list.stream().map(i -> i.getId()).collect(Collectors.toList());
        return collect;
    }
}
