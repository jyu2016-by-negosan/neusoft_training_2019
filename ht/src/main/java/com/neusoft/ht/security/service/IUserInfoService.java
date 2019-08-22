package com.neusoft.ht.security.service;

import java.util.List;

import com.neusoft.ht.security.model.UserInfoModel;

/**模块：系统安全模块
 * 系统管理员辅助信息的Service接口
 * @author 吴澍淼
 *
 */
public interface IUserInfoService {
	//增加
	public void add(UserInfoModel userInfoModel) throws Exception;
	//修改
	public void modify(UserInfoModel userInfoModel) throws Exception;
	//删除
	public void delete(UserInfoModel userInfoModel) throws Exception;
	
	//取得所有对象
	public List<UserInfoModel> getListByAll() throws Exception; 
	//取得所有对象,分页模式
	public List<UserInfoModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得单个对象
	public UserInfoModel getById(String uuserid) throws Exception;
	//取得对象的个数
	public int getCountByAll() throws Exception;
	//取得对象的页数
	public int getPageCountByAll(int rows) throws Exception; 

}
