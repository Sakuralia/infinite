package io.adana.infinite.user.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.adana.infinite.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author sakura
 * @date 2020/8/6 18:02
 * @description the entity of permission
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_permission")
public class Permission extends BaseEntity {

    @TableField("f_parent_pid")
    private Long parentPid;

    @NotBlank(message = "角色名称不能为空")
    @TableField("f_permission_name")
    private String permsName;

    @TableField("f_permission_value")
    private String permsVal;

    @TableField("f_icon")
    private String icon;

    @TableField("f_type")
    private Integer type;

    @TableField("f_url")
    private String url;

    @NotNull(message = "排序不能为空")
    @TableField("f_order_num")
    private Integer orderNum;

}
