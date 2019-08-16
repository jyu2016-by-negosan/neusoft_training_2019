
package com.neusoft.ht.complain.service;

import java.util.List;

import com.neusoft.ht.complain.model.ComplainTypeModel;

public interface IComplainTypeService {

	//增加投诉类型
	public void add(ComplainTypeModel complainType) throws Exception;

	//修改投诉类型
	public void modify(ComplainTypeModel complainType) throws Exception;

	//删除投诉类型
	public void delete(ComplainTypeModel complainType) throws Exception;

	//查询所有对象
	public List<ComplainTypeModel> getListByAll() throws Exception;

	//根据投诉类型编号查询对象
	public ComplainTypeModel getComplainTypeBytypeNo(int typeno) throws Exception;

	//查询对象个数
	public int getCountByAll() throws Exception;

	//查询所有对象（分页）
	public List<ComplainTypeModel> getListByAllWithPage(int rows, int pages) throws Exception;
	
	public int getPageCountByAll(int rows) throws Exception;

}
