package com.neusoft.ht.complain.service;

import java.util.List;

import com.neusoft.ht.complain.model.PublicHouseComplainModel;


public interface IPublicHouseComplainService {

	// 增加投诉类型
	public void add(PublicHouseComplainModel houseComplain) throws Exception;

	// 修改投诉类型
	public void modify(PublicHouseComplainModel houseComplain) throws Exception;

	// 删除投诉类型
	public void delete(PublicHouseComplainModel houseComplain) throws Exception;

	// 查询所有对象
	public List<PublicHouseComplainModel> getListByAll() throws Exception;

	// 根据投诉类型编号查询对象
	public PublicHouseComplainModel getHouseComplainBycomplainNo(int complainNo) throws Exception;

	// 查询对象个数
	public int getCountByAll() throws Exception;
	
	public int getCountByAllWithHouseAndComplainTypeWithPage(String complainstatus, int hoodno, String housename,String typename) throws Exception;

	public int getPageCountByAll(int rows,String complainstatus,int hoodno,String housename,String typename) throws Exception;
	// 查询所有对象（分页）
	public List<PublicHouseComplainModel> getListByAllWithPage(int rows, int pages) throws Exception;

	public int getPageCountByAll(int rows) throws Exception;
	
	//取得特定居民供热记录，取关联的居民表和年度供热价格表
	public PublicHouseComplainModel getByNoWithHouseAndComplainType(int complainno) throws Exception;
	
	public List<PublicHouseComplainModel> getListByConditionWithHouseAndComplainTypeWithPage(String complainstatus,int hoodno,String housename,String typename,int rows, int pages) throws Exception;

}
