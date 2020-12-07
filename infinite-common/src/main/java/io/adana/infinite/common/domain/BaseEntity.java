package io.adana.infinite.common.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author sakura
 * @description: <pre>
 *      the basic entity which is used as a parent class
 *  </pre>
 * @date 2020-10-21 10:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity<T extends Model<?>> extends Model<T> {

    private static final long serialVersionUID = 4933533988518907212L;
    /**
     * the dateTime which generated the creator operated the record.
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     * the identification of the creator.
     */
    @TableField("creator_id")
    protected Long creatorId;

    /**
     * the creator's name
     */
    @TableField("creator_name")
    protected String creatorName;

    /**
     * the department's identification which is the creator.
     */
    @TableField("creator_dept_id")
    protected Long creatorDeptId;

    /**
     * the dateTime which generated the updater update the record.
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    protected LocalDateTime updateTime;

    /**
     * the identification of the updater.
     */
    @TableField("updater_id")
    protected Long updaterId;

    /**
     * the updater's name
     */
    @TableField("updater_name")
    protected String updaterName;

    /**
     * the department's identification which is the updater.
     */
    @TableField("updater_dept_id")
    protected Long updaterDeptId;

    /**
     * the detail description of the record
     */
    @TableField("remark")
    private String remark;
    /**
     * the flag which represents that is deleted.
     */
    @TableField("del_flag")
    private Integer delFlag;

}
