package io.adana.infinite.common.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author sakura
 * @description: <pre>
 *      the basic entity which is used as a parent class
 *  </pre>
 * @date 2020-10-21 10:33
 */
public class BaseEntity implements Serializable {
    /**
     * the primary key that is a unique identification on database
     */
    protected Long id;
    /**
     * the dateTime which generated the creator operated the record.
     */
    protected LocalDateTime createTime;
    /**
     * the identification of the creator.
     */
    protected Long creatorId;
    /**
     * the creator's name
     */
    protected String creatorName;
    /**
     * the department's identification which is the creator.
     */
    protected Long creatorDeptId;
    /**
     * the dateTime which generated the updater update the record.
     */
    protected LocalDateTime updateTime;
    /**
     * the identification of the updater.
     */
    protected Long updaterId;
    /**
     * the updater's name
     */
    protected String updaterName;
    /**
     * the department's identification which is the updater.
     */
    protected Long updaterDeptId;
    /**
     * the flag which function is the status of deleted logically.
     * status:
     * 0 -----> disable
     * 1 -----> enable
     */
    private Integer status;
    /**
     * the detail description of the record
     */
    private String description;
    /**
     * the flag which represents that is deleted.
     */
    private Integer delFlag;

    public BaseEntity() {
    }

    public void setCreater(SysUserEntity user) {
        this.createTime = LocalDateTime.now();
        if (Objects.nonNull(user)) {
            this.creatorId = user.getId();
            this.creatorName = user.getUserName();
            this.creatorDeptId = user.getDeptId();
        }
    }

    public void setUpdater(SysUserEntity user) {
        this.updateTime = LocalDateTime.now();
        if (Objects.nonNull(user)) {
            this.updaterId = user.getId();
            this.updaterName = user.getUserName();
            this.updaterDeptId = user.getDeptId();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Long getCreatorDeptId() {
        return creatorDeptId;
    }

    public void setCreatorDeptId(Long creatorDeptId) {
        this.creatorDeptId = creatorDeptId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public Long getUpdaterDeptId() {
        return updaterDeptId;
    }

    public void setUpdaterDeptId(Long updaterDeptId) {
        this.updaterDeptId = updaterDeptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
