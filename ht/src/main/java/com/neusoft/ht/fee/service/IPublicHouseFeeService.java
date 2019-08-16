package com.neusoft.ht.fee.service;

import java.util.List;

import com.neusoft.ht.fee.model.PublicHouseFeeModel;

/**
 * 模块：供热缴费管理
 * 用途：公建供热记录管理的Service接口
 * 
 * @author 张晓龙
 * */

public interface IPublicHouseFeeService {
	//C
	public void add(PublicHouseFeeModel publicHouseFeeModel) throws Exception;
	//R
	public void modify(PublicHouseFeeModel publicHouseFeeModel) throws Exception;
	//D
	public void delete(PublicHouseFeeModel publicHouseFeeModel) throws Exception;
	//R1
	public List<PublicHouseFeeModel> getListByAll() throws Exception;
	//R2
	public PublicHouseFeeModel getByNo(int houseno) throws Exception;
	//R3
	public List<PublicHouseFeeModel> getListByAllWithPage(int start, int rows) throws Exception;
	
	//取得列表对象个数
	public int getCountByAll() throws Exception;
}
