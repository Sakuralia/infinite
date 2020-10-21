package io.adana.infinite.user.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author sakura
 * @date 2020/8/6 18:33
 * @description
 */
@TableName("t_role_menu")
@Accessors(chain = true)
@Data
public class RoleMenu implements Serializable {

    private Long id;

    @TableField("f_role_id")
    private Long roleId;

    @TableField("f_menu_id")
    private Long menuId;
}
