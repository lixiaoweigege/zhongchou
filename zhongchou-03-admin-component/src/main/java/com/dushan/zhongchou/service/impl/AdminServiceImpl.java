package com.dushan.zhongchou.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dushan.zhongchou.constant.ZhongChouConstant;
import com.dushan.zhongchou.entity.Admin;
import com.dushan.zhongchou.entity.AdminExample;
import com.dushan.zhongchou.entity.AdminExample.Criteria;
import com.dushan.zhongchou.exception.LoginAcctAlreadyInUseException;
import com.dushan.zhongchou.exception.LoginAcctAlreadyInUseExceptionForUpdate;
import com.dushan.zhongchou.mapper.AdminMapper;
import com.dushan.zhongchou.service.api.AdminService;
import com.dushan.zhongchou.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public int saveAdmin(Admin admin) {
		// 1.检查账号是否被占用
				String loginAcct = admin.getLoginAcct();
				
				AdminExample example = new AdminExample();
				
				Criteria criteria = example.createCriteria();
				
				criteria.andLoginAcctEqualTo(loginAcct);
				
				int count = adminMapper.countByExample(example);
				
				if(count > 0) {
					throw new LoginAcctAlreadyInUseException(ZhongChouConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
				}
				
				// 2.对密码进行加密
				String userPswd = admin.getUserPswd();
				String md5 = StringUtil.md5(userPswd);
				admin.setUserPswd(md5);
				
				// 3.设置创建时间
				String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				admin.setCreateTime(createTime);
				
				// 4.执行保存
				int result=adminMapper.insert(admin);
		return result;
	}

	/*
	 * 根据账号查询用户信息 (non-Javadoc)
	 * 
	 * @see
	 * com.dushan.zhongchou.service.api.AdminService#findAdminByLoginAcct(java.lang.
	 * String)
	 */
	@Override
	public Admin findAdminByLoginAcct(String loginAcct) {
		AdminExample adminExample = new AdminExample();
		adminExample.createCriteria().andLoginAcctEqualTo(loginAcct);
		List<Admin> list = adminMapper.selectByExample(adminExample);
		if (list == null || list.size() == 0) {
			return null;
		}
		//判断是否存在多个同名用户
		if (list.size() > 1) {
			throw new RuntimeException(ZhongChouConstant.MESSAGE_FATAL_ADMIN_COUNT_INVALIDE);
		}
		//返回有效用户
		return list.get(0);
	}

	@Override
	public PageInfo<Admin> getAdminPage(Integer pageNum, Integer pageSize, String keyword) {
		// 1.调用PageHelper的工具方法，开启分页功能
		PageHelper.startPage(pageNum, pageSize);
		
		// 2.执行分页查询
		List<Admin> list = adminMapper.selectAdminListByKeyword(keyword);
		return  new PageInfo<>(list);
	}

	@Override
	public void removeUserById(List<Integer> ids) {
		adminMapper.removeUserById(ids);
	}
	//根据id获得用户信息
	@Override
	public Admin findAdminById(int adminId) {
		return adminMapper.selectByPrimaryKey(adminId);
	}
	//更新用户
	@Override
	public void updateAdmin(Admin admin, String oldLoginAcct) {
		//1.检查新账户与旧账户是否冲突
		String newLoginAcct=admin.getLoginAcct();
		if(!Objects.equals(newLoginAcct, oldLoginAcct)) {
			AdminExample example = new AdminExample();
			Criteria criteria = example.createCriteria();
			criteria.andLoginAcctEqualTo(newLoginAcct);
			int count = adminMapper.countByExample(example);
			
			if(count>0) {
				throw new LoginAcctAlreadyInUseExceptionForUpdate(ZhongChouConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}
		//2新密码加密
		String userPswd=admin.getUserPswd();
		String md5Pswd=StringUtil.md5(userPswd);
		admin.setUserPswd(md5Pswd);
		// 3.执行更新
		// Selective的效果是空值不更新，保持createTime字段不变
		int result=adminMapper.updateByPrimaryKeySelective(admin);
	}

}
