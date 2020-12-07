package io.adana.infinite.user.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ywb
 * @version 1.0
 * @description
 * @date 2020/12/7 18:46
 */
@Data
@TableName("user_token")
public class UserToken implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    @TableField("token")
    private String token;

    @TableField("expire_time")
    private LocalDateTime expireTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
