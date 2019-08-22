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
		try {
			userInfoService.add(userInfoModel);
		} catch (Exception e) {
			return new ResultMessage<UserInfoModel>("ERROR", userInfoModel.getUuserid()+"信息已存在！");
		}
			return new ResultMessage<UserInfoModel>("Success!","增加成功");
	}
	
	//修改系统管理员辅助信息
	@PostMapping("/modify")
	public ResultMessage<UserInfoModel> modify(UserInfoModel userInfoModel) throws Exception{
		userInfoService.modify(userInfoModel);
		return new ResultMessage<UserInfoModel>("Success!","修改成功！");
	}
	
	//删除系统管理员辅助信息
	@PostMapping("/delete")
	public ResultMessage<UserInfoModel> delete(UserInfoModel userInfoModel) throws Exception{
		userInfoService.delete(userInfoModel);
		return new ResultMessage<UserInfoModel>("Success!","删除成功！");
	}
	
	//取得系统管理员辅助信息的所有列表，无分页
	@GetMapping(value = "/list/all")
	public List<UserInfoModel> getListByAll() throws Exception{
		return userInfoService.getListByAll();
	}
	
	//取得系统管理员辅助信息的所有列表，有分页
	@GetMapping(value = "/list/all/page")
	public ResultMessage<UserInfoModel> getListByAllWithPage(@RequestParam(required = false, defaultValue = "4") int rows, @RequestParam(required = false, defaultValue = "1") int page) throws Exception{
		ResultMessage<UserInfoModel> result = new ResultMessage<UserInfoModel>("Success!","取得系统管理员辅助信息列表分页模式成功！");
		result.setCount(userInfoService.getCountByAll());
		result.setPageCount(userInfoService.getPageCountByAll(rows));
		result.setList(userInfoService.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	
	//取得指定功能编号的功能信息
	@GetMapping("/getUserInfoById")
	public ResultMessage<UserInfoModel> getById(String uuserid) throws Exception{
		ResultMessage<UserInfoModel> result = new ResultMessage<UserInfoModel>("Success!","取得指定ID的系统管理员辅助信息成功！");
		result.setModel(userInfoService.getById(uuserid));
		return result;
	}

}
