package com.dushan.zhongchou.mvc.handler;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dushan.zhongchou.constant.ZhongChouConstant;
import com.dushan.zhongchou.entity.Admin;
import com.dushan.zhongchou.exception.LoginException;
import com.dushan.zhongchou.service.api.AdminService;
import com.dushan.zhongchou.util.ResultEntity;
import com.dushan.zhongchou.util.StringUtil;
import com.github.pagehelper.PageInfo;

@Controller
public class AdminHandler {
	@Autowired
	private AdminService adminServiceImpl;

	@RequestMapping("/admin/do/login.html")
	public String adminLogin(String loginAcct, String userPswd, HttpSession session) {
		Admin admin = adminServiceImpl.findAdminByLoginAcct(loginAcct);
		// 判断用户是否位空,如果为空则抛出异常
		
		if (admin == null) {
			throw new LoginException(ZhongChouConstant.MESSAGE_LOGIN_FAILED);
		}
		String userPswdDB = admin.getUserPswd().toUpperCase();
		// 加密表单密码
		String userPswdMd5 = StringUtil.md5(userPswd).toUpperCase();
		if (Objects.equals(userPswdMd5,userPswdDB)) {
			session.setAttribute(ZhongChouConstant.ATTR_NAME_LOGIN_ADMIN, admin);
			return "redirect:/admin/to/main/page.html";
		}
		throw new LoginException(ZhongChouConstant.MESSAGE_LOGIN_FAILED);

	}
	//退出登录
	@RequestMapping("/admin/do/loginOut.html")
	public String adminLoginOut(HttpSession session) {
		session.removeAttribute("loginAdmin");
		return "redirect:/admin/to/index.html";
	}
	//显示用户维护页面
	@RequestMapping("/admin/user.html")
	public String findUser(// 给pageNum参数设置默认值，在浏览器没有提供这个参数时，使用1作为默认值
			@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
			// 给pageSize参数设置默认值，在浏览器没有提供这个参数时，使用5作为默认值
			@RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
			// 给keyword参数设置默认值，在浏览器没有提供这个参数时，使用空字符串作为默认值
			@RequestParam(value="keyword", defaultValue="") String keyword,Model model) {
			PageInfo<Admin> pageInfo = adminServiceImpl.getAdminPage(pageNum, pageSize, keyword);

			model.addAttribute(ZhongChouConstant.ATTR_NAME_PAGE_INFO, pageInfo);
		return "admin-user";
	}
	//删除用户
	@ResponseBody
	@RequestMapping("/admin/removeUser.json")
	public ResultEntity<String> removeUser(@RequestBody List<Integer> ids){
		adminServiceImpl.removeUserById(ids);
		return ResultEntity.successWithoutData();
	}
	//新增用户
	@RequestMapping("/admin/saveUser.html")
	public String saveUser(Admin admin){
		adminServiceImpl.saveAdmin(admin);
		return  "redirect:/admin/user.html?pageNum="+Integer.MAX_VALUE;
	}
	//根据id查询用户并跳转到编辑页面
	@RequestMapping("/admin/to/editUser.html")
	public String toEditUser(int adminId,Model model) {
		Admin admin=adminServiceImpl.findAdminById(adminId);
		model.addAttribute("admin", admin);
		return "admin-edit";
	}
	//编辑用户
	@RequestMapping("admin/editUser.html")
	public String editUser(Admin admin,@RequestParam("pageNum") Integer pageNum, 
			@RequestParam("keyword") String keyword,
			@RequestParam("oldLoginAcct") String oldLoginAcct){
		System.out.println(admin.toString());
		System.out.println(oldLoginAcct);
		adminServiceImpl.updateAdmin(admin,oldLoginAcct);
		return  "redirect:/admin/user.html?pageNum="+pageNum+"&keyword="+keyword;
	}
	
	//显示角色维护页面
		@RequestMapping("/admin/role.html")
		public String roleManage() {
			System.out.println(666);
			return "admin-role";
		}
}
