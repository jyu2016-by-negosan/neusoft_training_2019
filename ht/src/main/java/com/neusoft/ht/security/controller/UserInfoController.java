package com.neusoft.ht.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.message.ResultMessage;
import com.neusoft.ht.security.model.UserInfoModel;
import com.neusoft.ht.security.service.IUserInfoService;

/**模块：系统安全模块
 * 系统管理员辅助信息的控制类
 * @author 吴澍淼
 *
 */
@RestController
@RequestMapping(value="/security/userinfo")
public class UserInfoController {
	
	@Autowired
	private IUserInfoService userInfoService=null;
	
	//增加系统管理员辅助信息
	@PostMapping("/add")
	public ResultMessage<UserInfoModel> add(UserInfoModel userInfoModel) throws Exception{
		if (userInfoModel != null) {
			userInfoService.add(userInfoModel);
		} else {
			return new ResultMessage<UserInfoModel>("ERROR","添加失败！");
		}
		return new ResultMessage<UserInfoModel>("OK","添加成功！");
		/*
		try {
			userInfoService.add(userInfoModel);
		} catch (Exception e) {
			return new ResultMessage<UserInfoModel>("ERROR", userInfoModel.getUuserid()+"信息已存在！");
		}
			return new ResultMessage<UserInfoModel>("Success!","增加成功");
	*/
		}
	
	//修改系统管理员辅助信息
	@PostMapping("/modify")
	public ResultMessage<UserInfoModel> modify(UserInfoModel userInfoModel) throws Exception{
		userInfoService.modify(userInfoModel);
		return new ResultMessage<UserInfoModel>("OK","修改成功！");
	}
	
	//删除系统管理员辅助信息
	@PostMapping("/delete")
	public ResultMessage<UserInfoModel> delete(UserInfoModel userInfoModel) throws Exception{
		userInfoService.delete(userInfoModel);
		return new ResultMessage<UserInfoModel>("OK","删除成功！");
	}
	
	//取得系统管理员辅助信息的所有列表，无分页
	@GetMapping(value = "/list/all")
	public List<UserInfoModel> getListByAll() throws Exception{
		return userInfoService.getListByAll();
	}
	
	//取得系统管理员辅助信息的所有列表，有分页
	@GetMapping(value = "/list/all/page")
	public ResultMessage<UserInfoModel> getListByAllWithPage(@RequestParam(required = false, defaultValue = "4") int rows, @RequestParam(required = false, defaultValue = "1") int page) throws Exception{
		ResultMessage<UserInfoModel> result = new ResultMessage<UserInfoModel>("OK","取得系统管理员辅助信息列表分页模式成功！");
		result.setCount(userInfoService.getCountByAll());
		result.setPageCount(userInfoService.getPageCountByAll(rows));
		result.setList(userInfoService.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	
	//取得指定ID的系统管理员信息
	@GetMapping("/getUserInfoById")
	public ResultMessage<UserInfoModel> getById(String uuserid) throws Exception{
		ResultMessage<UserInfoModel> result = new ResultMessage<UserInfoModel>("OK","取得指定ID的系统管理员辅助信息成功！");
		result.setModel(userInfoService.getById(uuserid));
		return result;
	}
	
	//检查系统管理员登陆账号是否已经存在
	@GetMapping(value = "/checkid")
	public boolean CheckId(String uuserid) throws Exception{
		boolean result = true;
		if (userInfoService.getCountById(uuserid)==1) {
			result = false;
		}
		return result;
	}

}
