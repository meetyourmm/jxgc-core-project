/*
 * Copyright (c) 2015. MiColor
 */

/**
 * @Title: Coder.java 
 * @Package common.interfaces 
 * @Description: 加密类
 * @author 葛钰鹏
 * @date 2014年10月31日 下午4:22:27 
 * @version V1.0  
 */

package com.micolor.commoncore.encryption;

import com.micolor.commoncore.exception.definition.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import static com.micolor.commoncore.statics.StringStatics.INNERERROR;

/**
 * @ClassName: Coder
 * @Description:
 * @author: 葛钰鹏
 * @date 2014年10月31日 下午4:22:27
 * 
 */
public class Coder {
	private Coder(){}
	private static Logger logger = LoggerFactory.getLogger(Coder.class);
	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";

	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 * HmacMD5  
	 * HmacSHA1  
	 * HmacSHA256  
	 * HmacSHA384  
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key){
		return org.apache.commons.codec.binary.Base64.decodeBase64(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key){
		return org.apache.commons.codec.binary.Base64.encodeBase64String(key);
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws CustomException {
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance(KEY_MD5);
			md5.update(data);
		}catch (NoSuchAlgorithmException e){
			logger.error(INNERERROR,e);
			throw new CustomException("异常：",e);
		}
		return md5.digest();

	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws CustomException {
		MessageDigest sha = null;
		try{
			sha = MessageDigest.getInstance(KEY_SHA);
			sha.update(data);
		}catch (NoSuchAlgorithmException e){
			logger.error(INNERERROR+"，原因：{}",e);
			throw new CustomException("异常：",e);
		}
		return sha.digest();



	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws CustomException {
		KeyGenerator keyGenerator = null;


		try{
			keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		}catch (NoSuchAlgorithmException e){
			logger.error(INNERERROR+"，原因：{}",e);
			throw new CustomException("异常：",e);
		}
		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws CustomException {
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = null;
		try{
			mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
		}catch (NoSuchAlgorithmException|InvalidKeyException e){
			logger.error("内部错误，原因：{}",e);
			throw new CustomException("异常：",e);
		}
		return mac.doFinal(data);
	}

	public static String getMD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			logger.error("内部错误，原因：{}",e);
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuilder hexValue = new StringBuilder();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	/**
	 * 微信小程序解密
	 * @param sSrc
	 * @param sKey
	 * @param ivParameter
	 * @return
	 * @throws Exception
	 */
	public static String wx_Decrypt(String sSrc, String sKey, String ivParameter) throws Exception {
		try {
			byte[] raw = Coder.decryptBASE64(sKey) ;
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			IvParameterSpec iv = new IvParameterSpec(Coder.decryptBASE64(ivParameter) );
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Coder.decryptBASE64(sSrc);//先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original,"UTF-8");
			return originalString;
		} catch (Exception ex) {
			throw new Exception("微信解析失败!");
		}
	}
}
