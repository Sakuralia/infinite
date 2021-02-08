package io.adana.infinite.user.domain.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author sakura
 * @version 1.1
 * @date 2021-02-08 11:00
 * @description UserVo
 * @Scope
 */
@Data
public class UserVo implements Serializable {

    private Long id;

    private Long userNo;
    /**
     * user_name
     */
    private String userName;
    /**
     * nick_name
     */
    @NotBlank(message = "用户名不能为空")
    private String nickName;
    /**
     * password
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * gender: 0 -----> male
     * 1 -----> female
     */
    private Integer gender;
    /**
     * head portrait
     */
    private String avatar;

    /**
     * the time when login in the system.
     */
    private LocalDateTime lastLoginTime;

    /**
     * user_status
     * 0 ----> enabled
     * 1 ----> disabled
     * 2 ----> locked
     */
    private Integer status;
    /**
     * get the ip address when login out.
     */
    private String ipAddress;
    /**
     * e-mail
     */
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * tel-phone number.
     */
    private String telPhone;
    /**
     * mobile-phone number.
     */
    private String mobilePhone;
    /**
     * QQ
     */
    private String qq;
    /**
     * Address
     */
    private String connectAddress;
    /**
     * the department identification
     */
    private Long deptId;
    /**
     * translate to display its name by the department identification
     */
    private String deptNameStr;
}
