package com.neusoft.ht.fee.service;

import java.util.List;

import com.neusoft.ht.fee.model.PublicHouseStopRecordModel;

/**
 * 模块：供热缴费管理
 * 功能：公建报停记录
 * */

public interface IPublicHouseStopRecordService {

	//C
	public void add(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception;
	//U
	public void modify(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception;
	//D
	public void delete(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception;
	//R1
	public List<PublicHouseStopRecordModel> getListByAll() throws Exception;
	//R2
	public PublicHouseStopRecordModel getByNo(int recordno) throws Exception;
	//R3
	public List<PublicHouseStopRecordModel> getListByAllWithPage(int rows, int page) throws Exception;
	
	//取得对象个数
	public int getCountByAll() throws Exception;
	//取得分页个数
	public int getCountPageByAll(int rows) throws Exception;
}
