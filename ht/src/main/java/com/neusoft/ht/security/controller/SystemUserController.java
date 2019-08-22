package com.neusoft.ht.security.controller;

import java.util.Base64;
import java.util.List;
import java.util.Base64.Encoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.message.ResultMessage;
import com.neusoft.ht.security.model.SystemUserModel;
import com.neusoft.ht.security.service.ISystemUserService;

import ch.qos.logback.core.net.LoginAuthenticator;


/**模块：系统安全模块
 * 操作员的控制类
 * @author 吴澍淼
 *
 */
@RestController
@RequestMapping("/user")
public class SystemUserController {
	
	@Autowired
	private ISystemUserService systemUserService=null;
	
	//增加操作员
	@PostMapping("/add")
	public ResultMessage<SystemUserModel> add(SystemUserModel systemUserModel) throws Exception{
		try {
			systemUserService.add(systemUserModel);
		} catch (Exception e) {
			return new ResultMessage<SystemUserModel>("ERROR", systemUserModel.getUuserid()+"操作员已存在！");
		}
			return new ResultMessage<SystemUserModel>("Success!","增加操作员成功");
	}
	
	//修改系统功能
	@PostMapping("/modify")
	public ResultMessage<SystemUserModel> modify(SystemUserModel systemUserModel) throws Exception{
		systemUserService.modify(systemUserModel);
		return new ResultMessage<SystemUserModel>("Success!","修改操作员信息成功！");
	}
	
	//删除系统功能
	@PostMapping("/delete")
	public ResultMessage<SystemUserModel> delete(SystemUserModel systemUserModel) throws Exception{
		systemUserService.delete(systemUserModel);
		return new ResultMessage<SystemUserModel>("Success!","删除操作员成功！");
	}
	
	//登录
	@PostMapping("/login")
	public ResultMessage<SystemUserModel> login(String uuserid, String upassword, HttpSession httpSession) throws Exception{
		System.out.println("login");
		System.out.println(httpSession);
		
		boolean validate = false;
		SystemUserModel systemUserModel = systemUserService.getById(uuserid);
		if(systemUserModel != null && systemUserModel.getUpassword().equals(upassword))
			validate = true;
		
		if(!validate) throw new Exception("登录失败");
		
		Encoder encoder = Base64.getEncoder();
		String ukey = uuserid+"-"+encoder.encodeToString(upassword.getBytes("UTF-8"));
		String encode_ukey = encoder.encodeToString(ukey.getBytes("UTF-8"));
		
		return new ResultMessage<SystemUserModel>("OK", encode_ukey);
	}
	
	// 用户退出
	@PostMapping("/logout")
	public ResultMessage<SystemUserModel> logout(String uuserid, HttpSession httpSession) throws Exception {
		
		httpSession.removeAttribute("login_user");
		return new ResultMessage<SystemUserModel>("OK",  "用户注销成功");
	}
	
	//取得系统功能所有列表，无分页
	@GetMapping(value = "/list/all")
	public List<SystemUserModel> getListByAll() throws Exception{
		return systemUserService.getListByAll();
	}
	
	//取得系统功能所有列表，有分页
	@GetMapping(value = "/list/all/page")
	public ResultMessage<SystemUserModel> getListByAllWithPage(@RequestParam(required = false, defaultValue = "4") int rows, @RequestParam(required = false, defaultValue = "1") int page) throws Exception{
		ResultMessage<SystemUserModel> result = new ResultMessage<SystemUserModel>("Success!","取得操作员列表分页模式成功！");
		result.setCount(systemUserService.getCountByAll());
		result.setPageCount(systemUserService.getPageCountByAll(rows));
		result.setList(systemUserService.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	
	//取得指定功能编号的功能信息
	@GetMapping("/getSystemUserById")
	public ResultMessage<SystemUserModel> getById(String uuserid) throws Exception{
		ResultMessage<SystemUserModel> result = new ResultMessage<SystemUserModel>("Success!","取得指定ID的操作员信息成功！");
		result.setModel(systemUserService.getById(uuserid));
		return result;
	}

}
