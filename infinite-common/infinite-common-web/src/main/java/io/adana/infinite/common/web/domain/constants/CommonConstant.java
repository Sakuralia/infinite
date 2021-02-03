package io.adana.infinite.common.web.domain.constants;

/**
 * @author ywb
 * @version 1.0
 * @description common-constant class
 * @date 2020/12/21 11:22
 */
public class CommonConstant {
    /**
     * algorithm's name
     */
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * algorithm's signature
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * public key
     */
    public static final String PUBLIC_KEY = "RSA_PUBLIC_KEY";

    /**
     * private key
     */
    public static final String PRIVATE_KEY = "RSA_PRIVATE_KEY";

    /**
     * token type --- jwt
     */
    public static final String TOKEN_TYPE = "JWT_";

    /**
     * token type --- session(need to set value that is suited for distributed and microservice project)
     */
    public static final String TOKEN_TYPE_SESSION = "SESSION_";


    public static final String COMMON_ACCESS_FORBIDDEN = "COMMON_ACCESS_FORBIDDEN";
}
