package com.zxh.teacher.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxh.common.common.ResultData;
import com.zxh.teacher.Feign.SystemFeignService;
import com.zxh.teacher.entity.Course;
import com.zxh.teacher.mapper.CourseMapper;
import com.zxh.teacher.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxh.teacher.vo.TypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxh
 * @since 2023-12-22
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    SystemFeignService systemFeignService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<Course> getCourseListByUserId( Integer id) {

        //根据教师id查询所有教师的课程
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<Course>().eq("teacher_id", id);
        List<Course> list = this.list(courseQueryWrapper);
        return list;
    }

    @Override
    public List<Course> getCourseByTypeId(Integer id) {
        ResultData r = systemFeignService.getTypeById(id);
        TypeVo data = r.getData(new TypeReference<TypeVo>() {
        });
        if(data.getPid()!=0){
            String json = stringRedisTemplate.opsForValue().get(id.toString());
            List<Course> list = new ArrayList<>();
            if(json!=null){
                list = JSONObject.parseArray(json,Course.class);
            }else{
                QueryWrapper<Course> type_id = new QueryWrapper<Course>().eq("type_id", id);
                list = this.list(type_id);
                if(list.size()>0){
                    String s = JSON.toJSON(list).toString();
                    stringRedisTemplate.opsForValue().set((list.get(0).getTypeId()).toString(),s);
                }
            }
            return list;
        }else{
            ResultData byPid = systemFeignService.getTypeIdsByPid(id);
            List<Integer> courseTypeIds = byPid.getData(new TypeReference<List<Integer>>() {
            });
            List<Course> list = new ArrayList<>();
            for (Integer courseTypeId : courseTypeIds) {
                String json = stringRedisTemplate.opsForValue().get(courseTypeId.toString());
                if(json!=null){
                    list.addAll(JSONObject.parseArray(json,Course.class));
                }else{
                    QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<Course>().eq("type_id", courseTypeId);
                    List<Course> courseList = this.list(courseQueryWrapper);
                    if(courseList.size()>0){
                        String s = JSON.toJSON(courseList).toString();
                        stringRedisTemplate.opsForValue().set((courseList.get(0).getTypeId()).toString(),s);
                        list.addAll(courseList);
                    }
                }
            }
            return list;
        }
    }


}
