package io.adana.infinite.gateway.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ywb
 * @version 1.0
 * @description
 * @date 2020/12/7 15:39
 */
@Data
@Builder
public class LoginUser implements Serializable {

    private Long userNo;

    private String userName;

    private String nickName;

    private String userToken;

    private LocalDateTime lastLoginTime;
}
