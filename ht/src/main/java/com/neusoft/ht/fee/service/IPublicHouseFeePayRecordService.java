package com.neusoft.ht.fee.service;

import java.util.List;

import com.neusoft.ht.fee.model.PublicHouseFeePayRecordModel;

/**
 * 模块：供热缴费管理
 * 用途：公建供热记录管理的Service接口
 * 
 * @author 张晓龙
 * */

public interface IPublicHouseFeePayRecordService {
	//C
	public void add(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception;
	//R
	public void modify(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception;
	//D
	public void delete(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception;
	//R1
	public List<PublicHouseFeePayRecordModel> getListByAll() throws Exception;
	//R2
	public PublicHouseFeePayRecordModel getByNo(int recordno) throws Exception;
	//R3
	public List<PublicHouseFeePayRecordModel> getListByAllWithPage(int start, int rows) throws Exception;
	
	//取得列表对象个数
	public int getCountByAll() throws Exception;
	//取得对象页数
	public int getCountPageByAll(int rows) throws Exception;
}
