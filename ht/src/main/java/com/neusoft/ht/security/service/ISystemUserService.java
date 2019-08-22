package com.neusoft.ht.security.service;

import java.util.List;

import com.neusoft.ht.security.model.SystemUserModel;

/**模块：系统安全模块
 * 操作员的Service接口
 * @author 吴澍淼
 *
 */
public interface ISystemUserService {
	
	//增加
	public void add(SystemUserModel systemUserModel) throws Exception;
	//修改
	public void modify(SystemUserModel systemUserModel) throws Exception;
	//删除
	public void delete(SystemUserModel systemUserModel) throws Exception;
	
	//验证合法性
	public boolean validate(String uuserid, String upassword) throws Exception;
	
	//取得所有对象
	public List<SystemUserModel> getListByAll() throws Exception; 
	//取得所有对象,分页模式
	public List<SystemUserModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得单个对象
	public SystemUserModel getById(String uuserid) throws Exception;
	//取得对象的个数
	public int getCountByAll() throws Exception;
	//取得对象的页数
	public int getPageCountByAll(int rows) throws Exception; 
	

}
