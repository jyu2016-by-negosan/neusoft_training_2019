package com.neusoft.ht.security.service;

import java.util.List;

import com.neusoft.ht.security.model.SystemFunctionModel;

/**模块：系统安全模块
 * 系统功能的Service接口
 * @author 吴澍淼
 *
 */
public interface ISystemFunctionService {
	//增加
	public void add(SystemFunctionModel systemFunctionModel) throws Exception;
	//修改
	public void modify(SystemFunctionModel systemFunctionModel) throws Exception;
	//删除
	public void delete(SystemFunctionModel systemFunctionModel) throws Exception;
	
	//取得所有对象
	public List<SystemFunctionModel> getListByAll() throws Exception; 
	//取得所有对象,分页模式
	public List<SystemFunctionModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得单个对象
	public SystemFunctionModel getByNo(int funno) throws Exception;
	//取得对象的个数
	public int getCountByAll() throws Exception;
	//取得对象的页数
	public int getPageCountByAll(int rows) throws Exception; 

}
