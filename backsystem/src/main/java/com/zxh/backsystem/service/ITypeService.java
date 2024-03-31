package com.zxh.backsystem.service;

import com.zxh.backsystem.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxh.backsystem.vo.addTypeVo;
import com.zxh.backsystem.vo.delTypeVo;
import com.zxh.backsystem.vo.typeListVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zxh
 * @since 2023-12-12
 */
public interface ITypeService extends IService<Type> {

    List<Type> getTypeList();

    void addType(addTypeVo add);

    void delType(delTypeVo del);

    List<Type> getTypesById(Integer id);

    List<Integer> getTypeIdsByPid(Integer id);
}
