package com.dushan.zhongchou.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.dushan.zhongchou.constant.ZhongChouConstant;

public class StringUtil {
	
	/**
	 * 执行MD5加密的工具方法
	 * @param source
	 * @return
	 */
	public static String md5(String source) {
		
		if(source == null || source.length() == 0) {
			throw new RuntimeException(ZhongChouConstant.MESSAGE_INVALIDE_STR);
		}
		
		String md5 = null;
		try {
			md5 = new BigInteger(1, MessageDigest.getInstance("md5").digest(source.getBytes())).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

}
