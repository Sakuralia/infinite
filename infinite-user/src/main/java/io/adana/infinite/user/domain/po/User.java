package io.adana.infinite.user.domain.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
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
@ApiModel(description = "用户记录表")
public class User extends BaseEntity<User> {

    @TableId
    private Long id;

    @ApiModelProperty(value = "用户编号")
    @TableField("user_no")
    private Long userNo;
    /**
     * user_name
     */
    @ApiModelProperty(value = "用户真实名")
    @TableField("user_name")
    private String userName;
    /**
     * nick_name
     */
    @TableField("nick_name")
    @NotBlank(message = "用户名不能为空")
    private String nickName;
    /**
     * password
     */
    @TableField("password")
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * gender: 0 -----> male
     * 1 -----> female
     */
    @TableField("gender")
    private Integer gender;
    /**
     * head portrait
     */
    @TableField("avatar")
    private String avatar;

    /**
     * the time when login in the system.
     */
    @TableField(value = "last_login_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastLoginTime;

    /**
     * user_status
     * 0 ----> enabled
     * 1 ----> disabled
     * 2 ----> locked
     */
    @TableField("status")
    private Integer status;
    /**
     * get the ip address when login out.
     */
    @TableField("ip_address")
    private String ipAddress;
    /**
     * e-mail
     */
    @TableField("email")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * tel-phone number.
     */
    @TableField("telephone")
    private String telPhone;
    /**
     * mobile-phone number.
     */
    @TableField("mobile_phone")
    private String mobilePhone;
    /**
     * QQ
     */
    @TableField("qq")
    private String qq;
    /**
     * Address
     */
    @TableField("connect_address")
    private String connectAddress;
    /**
     * the department identification
     */
    @TableField("dept_id")
    private Long deptId;
    /**
     * translate to display its name by the department identification
     */
    @TableField(exist = false)
    private String deptNameStr;

    @TableField(exist = false)
    private List<Long> roleIds;

}
