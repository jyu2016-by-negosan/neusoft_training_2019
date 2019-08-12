package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.PublicHouseStopRecordModel;

/**
 * 模块：供热管理模块
 * 公建报停记录管理的Mapper接口
 * 
 * @author 张晓龙
 * */

public interface IPublicHouseStopRecordMapper {

	//C
	public void create(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception;
	//U
	public void update(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception;
	//D
	public void delete(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception;
	//R1
	public List<PublicHouseStopRecordModel> selectListByAll() throws Exception;
	//R2
	public PublicHouseStopRecordModel selectByNo(int recordno) throws Exception;
	//R3
	public List<PublicHouseStopRecordModel> selectListByAllWithPage(@Param("start") int start, @Param("rows") int rows) throws Exception;Object clone() throws CloneNotSupportedException;
	
	//取得对象个数（可选）
	public int selectCountByAll() throws Exception;
}
