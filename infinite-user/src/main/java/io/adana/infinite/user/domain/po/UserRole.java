package io.adana.infinite.user.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author simon
 * @date 2020/8/6 18:10
 * @description <pre>
 *     the entity of the relation which is user and role.
 * </pre>
 */
@Data
@Accessors(chain = true)
@TableName("t_user_role")
public class UserRole implements Serializable {

    private Long id;

    @TableField("f_user_id")
    private Long userId;

    @TableField("f_role_id")
    private Long roleId;
}
