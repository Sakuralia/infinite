package io.adana.infinite.user.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author sakura
 * @date 2020/8/6 18:16
 * @description <pre>
 *     the entity of the relation which is user and permission.
 * </pre>
 */
@Data
@Accessors(chain = true)
@TableName("t_user_permission")
public class UserPermission implements Serializable {

    private Long id;

    @TableField("f_user_id")
    private Long userId;

    @TableField("f_permission_id")
    private Long permsId;

    @TableField("f_type")
    private Integer type;
}
