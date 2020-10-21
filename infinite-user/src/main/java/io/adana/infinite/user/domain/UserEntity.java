package io.adana.infinite.user.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.adana.infinite.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author simon
 * @date 2020/8/6 10:59
 * @description <pre>
 *
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
public class UserEntity extends BaseEntity {

    /**
     * user_name
     */
    @TableField("f_user_name")
    private String userName;
    /**
     * nick_name
     */
    @TableField("f_nick_name")
    @NotBlank(message = "用户名不能为空")
    private String nickName;
    /**
     * password
     */
    @TableField("f_password")
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * salt
     */
    @TableField("f_salt")
    private String salt;
    /**
     * gender: 0 -----> male
     * 1 -----> female
     */
    @TableField("f_gender")
    private Integer gender;
    /**
     * head portrait
     */
    @TableField("f_avatar")
    private String avatar;

    /**
     * user_status
     * 0 ----> enabled
     * 1 ----> disabled
     * 2 ----> locked
     */
    @TableField("f_status")
    private Integer status;
    /**
     * get the ip address when login out.
     */
    @TableField("f_ip_address")
    private String ipAddress;
    /**
     * e-mail
     */
    @TableField("f_email")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * tel-phone number.
     */
    @TableField("f_telephone")
    private String telPhone;
    /**
     * mobile-phone number.
     */
    @TableField("f_mobile_phone")
    private String mobilePhone;
    /**
     * QQ
     */
    @TableField("f_qq")
    private String qq;
    /**
     * Address
     */
    @TableField("f_connect_address")
    private String ConnectAddress;
    /**
     * the department identification
     */
    @TableField("f_dept_id")
    private Long deptId;
    /**
     * translate to display its name by the department identification
     */
    @TableField(exist = false)
    private String deptNameStr;

    @TableField(exist = false)
    private List<Long> roleIds;

}
