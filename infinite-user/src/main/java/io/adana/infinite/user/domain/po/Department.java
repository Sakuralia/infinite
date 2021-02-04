package io.adana.infinite.user.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * function description:
 * <pre>
 *      the entity of Department.
 *  </pre>
 *
 * @author sakura
 * @date 2020/10/21 10:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Accessors(chain = true)
@TableName("t_department")
public class Department extends BaseEntity {
    /**
     * the identification of parent department.
     */
    @TableField("f_parent_dept_id")
    private Long parentDeptId;
    /**
     * the name of parent department.
     */
    @TableField("f_parent_dept_name")
    private String parentDeptName;
    /**
     * the name of current department
     */
    @TableField("f_dept_name")
    private String deptName;
    /**
     * the code of current department.
     */
    @TableField("f_dept_code")
    private String deptCode;
    /**
     * the order number is used by query the data of department in page.
     */
    @TableField("f_order_num")
    private Integer orderNum;


}



