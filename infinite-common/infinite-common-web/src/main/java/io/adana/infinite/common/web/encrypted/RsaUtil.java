package io.adana.infinite.common.web.encrypted;

import io.adana.infinite.common.web.domain.consts.CommonConstant;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author simon
 * @Email merin@outlook.com
 * @Date 2019/2/27
 * @Description encode by algorithm RSA
 */
public class RsaUtil {
    /**
     * generate a digital signature using the private key.
     *
     * @param data       encrypted data .
     * @param privateKey private key
     * @return {@link String}
     * @throws Exception 1. decode by private key with encoded in base64;
     *                   2. construct a object that named PKCS8EncodedKeySpec;
     *                   3. give a algorithm to KEY_Algorithm;
     *                   4. get a object of private key;
     *                   5. generate a digital-signature using the private key.
     */
    public static String getSignature(byte[] data, String privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(CommonConstant.KEY_ALGORITHM);
        PrivateKey privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance(CommonConstant.SIGNATURE_ALGORITHM);
        signature.initSign(privateKey1);
        signature.update(data);

        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * check the digital-signature
     *
     * @param data      data
     * @param publicKey public key
     * @param sign      signature
     * @return {@link Boolean}
     * @throws Exception 1.decode by public key that encode in base64;
     *                   2.construct a object which named X509EncodedKeySpec;
     *                   3. get the object of public key;
     *                   4. check the signature which is normal or not.
     */
    public static Boolean checkSignature(byte[] data, String publicKey, String sign) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(CommonConstant.KEY_ALGORITHM);
        PublicKey publicKey1 = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance(CommonConstant.SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey1);
        signature.update(data);
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    /**
     * decode by private key
     *
     * @param data encrypted data
     * @param key  key
     * @return {@link Byte[]}
     * @throws Exception {@link NoSuchAlgorithmException}
     */
    public static byte[] decodeByPrivateKey(byte[] data, String key) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(CommonConstant.KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * decode by public key
     *
     * @param data encrypted data
     * @param key  key
     * @return {@link Byte[]}
     * @throws Exception {@link NoSuchAlgorithmException}
     */
    public static byte[] decodeByPublicKey(byte[] data, String key) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(CommonConstant.KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * encode by private key
     *
     * @param data primary data
     * @param key  private key
     * @return {@link Byte[]}
     * @throws Exception{@link NoSuchAlgorithmException}
     */
    public static byte[] encodeByPrivateKey(byte[] data, String key) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(CommonConstant.KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * encode by public key
     *
     * @param data primary data
     * @param key  public key
     * @return byte[]
     * @throws Exception {@link NoSuchAlgorithmException}
     */
    public static byte[] encodeByPublicKey(byte[] data, String key) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(CommonConstant.KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * get the private secret-key
     *
     * @param mapKey key-value map
     * @return {@link String}
     */
    public static String getPrivateKey(Map<String, Object> mapKey) {
        Key key = (Key) mapKey.get(CommonConstant.PRIVATE_KEY);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * get the public secret-key
     *
     * @param mapKey key-value map
     * @return {@link String}
     */
    public static String getPulicKey(Map<String, Object> mapKey) {
        Key key = (Key) mapKey.get(CommonConstant.PUBLIC_KEY);
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * init the secret-key
     *
     * @return {@link Map<String, Object>}
     * @throws Exception {@link NoSuchAlgorithmException}
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(CommonConstant.KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> mapKey = new HashMap<>(4);
        mapKey.put(CommonConstant.PUBLIC_KEY, publicKey);
        mapKey.put(CommonConstant.PRIVATE_KEY, privateKey);
        return mapKey;
    }
}
