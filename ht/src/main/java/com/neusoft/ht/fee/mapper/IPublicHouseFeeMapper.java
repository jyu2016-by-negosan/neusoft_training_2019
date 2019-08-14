package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.PublicHouseFeeModel;

/**
 * 模块：供热缴费管理
 * 公建供热记录管理的Mapper接口
 * 
 * @author 张晓龙
 * */

public interface IPublicHouseFeeMapper {

	//C
	public void insert(PublicHouseFeeModel publicHouseFeeModel) throws Exception;
	//U
	public void update(PublicHouseFeeModel publicHouseFeeModel) throws Exception;
	//D
	public void delete(PublicHouseFeeModel publicHouseFeeModel) throws Exception;
	//R1
	public List<PublicHouseFeeModel> selectListByAll() throws Exception;
	//R2
	public PublicHouseFeeModel selectByNo(int feeno) throws Exception;
	//R3
	public List<PublicHouseFeeModel> selectListByAllWithPage(@Param("start") int start, @Param("rows") int rows) throws Exception;
	
	//取得对象个数（可选）
	public int selectCountByAll() throws Exception;
}
