package io.adana.infinite.user.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.adana.infinite.common.domain.po.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author sakura
 * @date 2020/8/6 17:41
 * @description <pre>
 *     the entity of role
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_role")
public class Role extends BaseEntity {

    @TableField("f_role_name")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @NotNull(message = "排序不能为空")
    @TableField("f_order_num")
    private Integer orderNum;

    @JsonIgnore
    @TableField("f_existed_count")
    private Integer existedCount;
    @NotNull(message = "部门id不能为空")
    private Long deptId;

    @TableField(exist = false)
    private String deptNameStr;

    @TableField(exist = false)
    private List<Long> menuIds;

    @TableField(exist = false)
    private List<Long> deptIds;

}
