package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.PublicHouseFeeReturnRecordModel;

/**
 * 模块：供热缴费管理
 * 公建退费记录管理的接口
 * 
 * @author 张晓龙
 * */

public interface IPublicHouseFeeReturnRecordMapper {

	//C
	public void create(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception;
	//U
	public void update(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception;
	//D
	public void delete(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception;
	//R1
	public List<PublicHouseFeeReturnRecordModel> selectListByAll() throws Exception;
	//R2
	public PublicHouseFeeReturnRecordModel selectByNo(int recordno) throws Exception;
	//R3
	public List<PublicHouseFeeReturnRecordModel> selectListByAllWithPage(@Param("start") int start, @Param("rows") int rows) throws Exception;
	
	//取得对象个数（可选）
	public int selectCountByAll() throws Exception;
}
