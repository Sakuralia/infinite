package io.adana.infinite.user.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.adana.infinite.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author sakura
 * @date 2020/8/6 17:59
 * @description <pre>
 *     the entity of menu
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_menu")
public class Menu extends BaseEntity {
    /**
     * parent_id,一级为0
     */
    @TableField("f_parent_id")
    private Long parentId;
    /**
     * the parent menu‘s name
     */
    @TableField(exist = false)
    private String parentMenuNameStr;
    /**
     * menu level
     */
    @TableField("f_level")
    @NotEmpty(message = "菜单等级不能为空")
    private Integer level;
    /**
     * the order number is used by query the data of department in page.
     */
    @TableField("f_order_num")
    @NotEmpty(message = "排序不能为空")
    private Integer orderNum;
    /**
     * the menu name.
     */
    @TableField("f_name")
    @NotBlank(message = "菜单名称不能为空")
    private String name;
    /**
     * the menu's icon.
     */
    @JsonIgnore
    @TableField("f_icon")
    private String icon;
    /**
     * display or not.
     */
    @JsonIgnore
    @TableField("f_reveal")
    @NotNull(message = "显示标志不能为空")
    private Integer reveal;

}
