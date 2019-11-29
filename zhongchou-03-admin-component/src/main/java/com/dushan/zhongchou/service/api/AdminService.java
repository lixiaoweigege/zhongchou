package com.dushan.zhongchou.service.api;

import java.util.List;

import com.dushan.zhongchou.entity.Admin;
import com.dushan.zhongchou.exception.LoginAcctAlreadyInUseExceptionForUpdate;
import com.github.pagehelper.PageInfo;

public interface AdminService {
	public int saveAdmin(Admin admin);

	public Admin findAdminByLoginAcct(String loginAcct);

	public PageInfo<Admin> getAdminPage(Integer pageNum, Integer pageSize, String keyword);

	public void removeUserById(List<Integer> ids);

	public Admin findAdminById(int adminId);

	public void updateAdmin(Admin admin, String oldLoginAcct);

}
