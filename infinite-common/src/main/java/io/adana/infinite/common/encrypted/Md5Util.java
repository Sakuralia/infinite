package io.adana.infinite.common.encrypted;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static java.security.MessageDigest.getInstance;

/**
 * @author simon
 * @email merin@outlook.com
 * @date 2019/2/27
 * @description Encode by md5
 */
public class Md5Util {
    public static String encodeByMd5(String str) {

        try {
            //Algorithm MD5
            MessageDigest md5 = getInstance("MD5");
            byte[] digest = md5.digest(str.getBytes(StandardCharsets.UTF_8));
            //Encode by base64
            return Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
}
