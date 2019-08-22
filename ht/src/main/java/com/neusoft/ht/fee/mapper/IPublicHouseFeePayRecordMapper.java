package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.PublicHouseFeePayRecordModel;

/**
 * 模块：供热缴费管理
 * 公建缴费记录管理的Mapper接口
 * 
 * @author 张晓龙
 * */

public interface IPublicHouseFeePayRecordMapper {

	//C
	public void create(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception;
	//U
	public void update(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception;
	//D
	public void delete(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception;
	//R1
	public List<PublicHouseFeePayRecordModel> selectListByAll() throws Exception;
	//R2
	public PublicHouseFeePayRecordModel selectByNo(int recordno) throws Exception;
	//R3
	public List<PublicHouseFeePayRecordModel> selectListByAllWithPage(@Param("start") int start, @Param("rows") int rows) throws Exception;
	
	//取得对象个数（可选）
	public int selectCountByAll() throws Exception;
}
