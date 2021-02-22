package com.gjs.fixedassets.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private Integer isStatus;

    /**
     * 账户注册时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date resgistertime;

    /**
     * 所属公司
     */
    private Integer companyId;

    private Department department;

    private Job job;

    private Role role;

    /**
     * 工号
     */
    private Integer workId;
    /*
     * @Description TODO
     * 用户角色枚举类
     * @Author
     * @Date 2021-02-18
     * @params
     * @Return
//     **/
//    public enum Role {
//        SYS_ADMIN(0, "系统管理员"),
//        FIXED_ADMINISTER(1, "固定资产管理"),
//        ACCOUNT_OFFICER(2, "固定资产会计主管"),
//        STAFF(3, "员工");
//
//        private int code;
//        private String value;
//
//        Role(int code, String value) {
//            this.code = code;
//            this.value = value;
//        }
//
//        public static Role getRoleByCode(int code) {
//
//            for (Role role : values()) {
//                if (role.getCode() == code) {
//                    return role;
//                }
//            }
//            return null;
//        }
//
//        public String getValue() {
//            return value;
//        }
//
//        public int getCode() {
//            return code;
//        }
//    }

}