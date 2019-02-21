/*
 * Copyright (c) 2015. MiColor
 */

/**
 * @Title: DSACoder.java
 * @Package common.method
 * @Description:
 * @author 葛钰鹏
 * @date 2014年10月31日 下午5:11:44
 * @version V1.0
 */

package com.micolor.commoncore.encryption;

import com.micolor.commoncore.exception.definition.CustomException;

import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DSACoder
 * @Description:
 * @author: 葛钰鹏
 * @date 2014年10月31日 下午5:11:44
 *
 */

public class DSACoder {
    private DSACoder() {
    }

    public static final String ALGORITHM = "DSA";

    /**
     * 默认密钥字节数
     *
     * <pre>
     * DSA
     * Default Keysize 1024
     * Keysize must be a multiple of 64, ranging from 512 to 1024 (inclusive).
     * </pre>
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 默认种子
     */
    private static final String DEFAULT_SEED = "GBANzZwg7e7JVS51sbzGOVNcnXIOTklis";

    private static final String PUBLIC_KEY = "MRgkXM8UJowrg";
    private static final String PRIVATE_KEY = "npgHCns36";

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data
     *            加密数据
     * @param privateKey
     *            私钥
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws CustomException {
        String sign = "";
        try {
// 解密由base64编码的私钥
            byte[] keyBytes = Coder.decryptBASE64(privateKey);

            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
            signature.initSign(priKey);
            signature.update(data);
            sign = Coder.encryptBASE64(signature.sign());
        } catch (Exception e) {
            throw new CustomException("异常：", e);
        }


        return sign;
    }

    /**
     * 校验数字签名
     *
     * @param data
     *            加密数据
     * @param publicKey
     *            公钥
     * @param sign
     *            数字签名
     *
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws CustomException {
        boolean verifyValue = false;
        try {
// 解密由base64编码的公钥
            byte[] keyBytes = Coder.decryptBASE64(publicKey);

            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

            // ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

            // 取公钥匙对象
            PublicKey pubKey = keyFactory.generatePublic(keySpec);

            Signature signature = Signature.getInstance(keyFactory.getAlgorithm());
            signature.initVerify(pubKey);
            signature.update(data);
            verifyValue = signature.verify(Coder.decryptBASE64(sign));
        } catch (Exception e) {
            throw new CustomException("异常：", e);
        }
        // 验证签名是否正常
        return verifyValue;
    }

    /**
     * 生成密钥
     *
     * @param seed
     *            种子
     * @return 密钥对象
     * @throws Exception
     */
    public static Map<String, Object> initKey(String seed) throws CustomException {
        Map<String, Object> map = new HashMap(2);
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALGORITHM);
            // 初始化随机产生器
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.setSeed(seed.getBytes(Charset.forName("UTF-8")));
            keygen.initialize(KEY_SIZE, secureRandom);
            KeyPair keys = keygen.genKeyPair();
            DSAPublicKey publicKey = (DSAPublicKey) keys.getPublic();
            DSAPrivateKey privateKey = (DSAPrivateKey) keys.getPrivate();
            map.put(PUBLIC_KEY, publicKey);
            map.put(PRIVATE_KEY, privateKey);
        } catch (Exception e) {
            throw new CustomException("异常：", e);
        }
        return map;
    }

    /**
     * 默认生成密钥
     *
     * @return 密钥对象
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws CustomException {
        Map<String, Object> map = null;
        try {
            map = initKey(DEFAULT_SEED);
        } catch (Exception e) {
            throw new CustomException("异常：", e);
        }
        return map;
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws CustomException {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Coder.encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws CustomException {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Coder.encryptBASE64(key.getEncoded());
    }
}
