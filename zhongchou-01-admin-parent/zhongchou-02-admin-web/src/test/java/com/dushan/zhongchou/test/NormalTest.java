package com.dushan.zhongchou.test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-persist-mybatis.xml")
public class NormalTest {
@Autowired
private DataSource druidDataSource;
@Test
public void dataConnectionTest() throws SQLException {
	System.out.println(druidDataSource.getConnection());
}
@Test
public void md5Test() throws NoSuchAlgorithmException {
	// 1.获取messageDigest对象，传入的参数是具体加密算法的名称
	MessageDigest messageDigest = MessageDigest.getInstance("md5");
	// 2.获取明文字符串的字节数组
	byte[] inputByteArray = "123123".getBytes();
	// 3.执行加密
	byte[] outputByteArray = messageDigest.digest(inputByteArray);
	// 4.将输出的字节数组使用BigInteger转换为适合数据库保存的字符串
	String encode = new BigInteger(1, outputByteArray).toString(16);
	String md5=new BigInteger(1,MessageDigest.getInstance("md5").digest("123123".getBytes())).toString(16);
	// 5.打印查看结果
	System.out.println(encode);
	System.out.println(md5);
}
@Test
public void debugTest() {
	String a="202CB962AC59075B964B07152D234B70";
	String b="202cb962ac59075b964b07152d234b70";
	System.out.println(Objects.equals(a, b));
}
}
