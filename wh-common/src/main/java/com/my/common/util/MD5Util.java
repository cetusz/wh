package com.my.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 提供MD5操作
 * 
 * @author 907897
 * 
 */
public class MD5Util {

	private MD5Util() {
	}

	public static String byte2hex(byte[] b) {// 二行制转十六进制字符串
		if (b == null) {
			return "";
		}
		StringBuffer hs = new StringBuffer();
		String stmp = null;
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				hs.append("0");
			}
			hs.append(stmp);
		}
		return hs.toString();
	}

	public static String MD5(String src) {
		if (src == null) {
			return "";
		}
		byte[] result = null;
		try {
			MessageDigest alg = MessageDigest.getInstance("MD5");
			result = alg.digest(src.getBytes());
		} catch (NoSuchAlgorithmException e) {

		}
		return byte2hex(result);
	}

	public static String MD5(byte[] src) {
		if (src == null) {
			return "";
		}
		byte[] result = null;
		try {
			MessageDigest alg = MessageDigest.getInstance("MD5");
			result = alg.digest(src);
		} catch (NoSuchAlgorithmException e) {

		}
		return byte2hex(result);
	}
}
