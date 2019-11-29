package com.dushan.zhongchou.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dushan.zhongchou.entity.Admin;
import com.dushan.zhongchou.service.api.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-persist-mybatis.xml", 
		"classpath:spring-persist-tx.xml" })
public class ServiceTest {
	@Autowired
	private AdminService adminServiceImpl;

	@Test
	public void saveAdminTest() {
		Admin admin=new Admin();
		admin.setUserName("����");
		admin.setUserPswd("123");
		admin.setEmail("56254");
		admin.setLoginAcct("11");
		System.out.println(adminServiceImpl.saveAdmin(admin));
	}
	@Test
	public void removeAdminTest() {
		List<Integer> ids=new ArrayList<>();
		ids.add(2);
		adminServiceImpl.removeUserById(ids);
	}
}
