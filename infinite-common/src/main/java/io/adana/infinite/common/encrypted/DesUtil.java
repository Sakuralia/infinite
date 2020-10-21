package io.adana.infinite.common.encrypted;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

/**
 * @author simon
 * @email merin@outlook.com
 * @date 2019/2/27
 * @description encode by DES and base64
 */
public class DesUtil {

    public static String encodeByBase64(byte[] target) {
        Base64.Encoder encode64 = Base64.getEncoder();
        return encode64.encodeToString(target);
    }

    public static byte[] decodeByBase64(String target) {
        Base64.Decoder decode64 = Base64.getDecoder();
        try {
            return decode64.decode(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Key getKey(String key, String algorithm) {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(algorithm);
            generator.init(new SecureRandom(key.getBytes()));
            return generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String target, String algorithm, String key) {
        try {
            byte[] targets = target.getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, getKey(key, algorithm));
            return encodeByBase64(cipher.doFinal(targets));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String target, String algorithm, String key) {
        byte[] targets = decodeByBase64(target);
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, getKey(key, algorithm));
            byte[] bytes = cipher.doFinal(Objects.requireNonNull(targets));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
