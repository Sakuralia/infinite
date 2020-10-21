package io.adana.infinite.common.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author sakura
 * @version 1.0
 * @description basic info which is the entity of user.
 * @date 2020/10/21 10:58
 */
public class SysUserEntity implements Serializable {
    /**
     * the identification of system user.
     */
    private Long id;
    /**
     * the real name
     */
    private String userName;
    /**
     * the nick name
     */
    private String nickName;
    /**
     * Md5 encryption
     */
    private String password;
    /**
     * salt avoid to cracked by rainbowTable.
     */
    private String salt;
    /**
     * gender: 0 -----> male
     * 1 -----> female
     */
    private Integer gender;
    /**
     * the identification of department.
     */
    private Long deptId;
    /**
     * user_status
     * 0 ----> enabled
     * 1 ----> disabled
     * 2 ----> locked
     */
    private Integer status;
    /**
     * the mobile phone number.
     */
    private String mobilePhone;
    /**
     * the collection which relates roles.
     */
    private List<Long> roleIds;
    /**
     * the detail description.
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysUserEntity that = (SysUserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(salt, that.salt) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(deptId, that.deptId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(roleIds, that.roleIds) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, nickName, password, salt, gender, deptId, status, mobilePhone, roleIds, description);
    }

    @Override
    public String toString() {
        return "SysUserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", gender=" + gender +
                ", deptId=" + deptId +
                ", status=" + status +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", roleIds=" + roleIds +
                ", description='" + description + '\'' +
                '}';
    }
}
