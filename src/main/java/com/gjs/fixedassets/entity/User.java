package com.gjs.fixedassets.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Description TODO
 * 用户实体
 * @Author 顾嘉晟
 * @Date 2021-02-18
 *
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 用户姓名/账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户性别
     */
    private String gender;

    /**
     * 用户头像
     */
    private String userFace;

    /**
     * 用户电话
     */
    private Integer userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户是否在线
     */
    private String userOnline;

    /**
     * 用户所属部门编码
     */
    private Integer departmentId;

    /**
     * 用户职位编码
     */
    private Integer jobId;

    /**
     * 用户角色编码
     */
    private Integer roleId;

    /**
     * 用户是否在职
     */
    private String isStatus;

    /**
     * 账户注册时间
     */
    private Date resgistertime;

    /**
     * 所属公司
     */
    private Integer companyId;

    /*
     * @Description TODO
     * 用户角色枚举类
     * @Author
     * @Date 2021-02-18
     * @params
     * @Return
     **/
    public enum Role {

    }

}