package com.neusoft.ht.fee.service;

import java.util.List;

import com.neusoft.ht.fee.model.PublicHouseFeeReturnRecordModel;

/**
 * 模块：供热缴费管理
 * 用途：公建供热记录管理的Service接口
 * 
 * @author 张晓龙
 * */

public interface IPublicHouseFeeReturnRecordService {
	//C
	public void add(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception;
	//R
	public void modify(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception;
	//D
	public void delete(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception;
	//R1
	public List<PublicHouseFeeReturnRecordModel> getListByAll() throws Exception;
	//R2
	public PublicHouseFeeReturnRecordModel getByNo(int recordno) throws Exception;
	//R3
	public List<PublicHouseFeeReturnRecordModel> getListByAllWithPage(int start, int rows) throws Exception;
	
	//取得列表对象个数
	public int getCountByAll() throws Exception;
	//取得对象页数
	public int getCountPageByAll(int rows) throws Exception;
}
