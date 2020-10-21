package io.adana.infinite.user.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * function description:
 * <pre>
 *      the entity of the relation of which role and dept.
 *  </pre>
 *
 * @author sakura
 * @date 2020/10/21 10:52
 */
@Data
@Accessors(chain = true)
@TableName("t_role_dept")
public class RoleDept implements Serializable {

    private Long id;
    @TableField("f_role_id")
    private Long roleId;
    @TableField("f_dept_id")
    private Long deptId;
}
