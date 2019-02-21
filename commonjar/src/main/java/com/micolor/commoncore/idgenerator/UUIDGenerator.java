/*
 * Copyright (c) 2015. MiColor
 */

package com.micolor.commoncore.idgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.security.SecureRandom;
import java.util.Calendar;

public class UUIDGenerator {
	private static SecureRandom seederStatic = null;
	private static byte[] address = null;
	private static String midValueStatic = null;
	private String midValue = null;
	private SecureRandom seeder = null;

	private static Logger logger = LoggerFactory.getLogger(UUIDGenerator.class);

	public static final String UUIDGenerate(){
		return UUIDGenerator.generate(Calendar.getInstance().getTimeInMillis());
	}

	static {
		try {
			address = InetAddress.getLocalHost().getAddress();
			StringBuilder buffer = new StringBuilder(8);
			buffer.append(toHex(toInt(address), 8));
			midValueStatic = buffer.toString();
			seederStatic = new SecureRandom();
			seederStatic.nextInt();
		} catch (Exception ex) {
			logger.error("发送短信配置信息失败，原因：{}",ex);
		}
	}

	public UUIDGenerator() {
		StringBuilder buffer = new StringBuilder(16);
		buffer.append(midValueStatic);
		buffer.append(toHex(System.identityHashCode(this), 8));
		midValue = buffer.toString();
		seeder = new SecureRandom();
		seeder.nextInt();
	}

	/**
	 * 该方法用来产生一个32位的唯一的标记String
	 * 
	 * @param obj
	 *            传入一个参考的对象
	 * @return
	 */
	public static String generate(Object obj) {
		StringBuilder uid = new StringBuilder(32);

		// get the system time
		long currentTimeMillis = System.currentTimeMillis();
		uid.append(toHex((int) (currentTimeMillis & -1L), 8));

		// get the internet address
		uid.append(midValueStatic);

		// get the object hash value
		uid.append(toHex(System.identityHashCode(obj), 8));

		// get the random number
		uid.append(toHex(getRandom(), 8));

		return uid.toString();
	}

	/**
	 * 该方法用来产生一个32位的String唯一标记
	 */
	public String generate() {
		StringBuilder uid = new StringBuilder(32);

		// get the system time
		long currentTimeMillis = System.currentTimeMillis();
		uid.append(toHex((int) (currentTimeMillis & -1L), 8));

		// get the internet address
		uid.append(midValue);

		// get the random number
		uid.append(toHex(seeder.nextInt(), 8));

		return uid.toString();
	}

	private static String toHex(int value, int length) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuilder buffer = new StringBuilder(length);
		int shift = length - 1 << 2;
		for (int i = -1; ++i < length;) {
			buffer.append(hexDigits[value >> shift & 0xf]);
			value <<= 4;
		}

		return buffer.toString();
	}

	private static int toInt(byte[] bytes) {
		int value = 0;
		for (int i = -1; ++i < bytes.length;) {
			value <<= 8;
			value |= (bytes[i] & 0xff);//未加0xff findbug会报：Bitwise OR of signed byte value
		}

		return value;
	}

	private static synchronized int getRandom() {
		return seederStatic.nextInt();
	}
}