package com.zxh.teacher.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class userInfoVo {

    private Integer id;


    private String name;


    private String nickname;


    private String password;


    private String phone;


    private String avatar;

    //0 - 管理员 1 - 学生  2 - 教师
    private Integer role;
}
