package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HeatingPriceModel;

public interface IHeatingPriceMapper {
	//C
	public void insert(HeatingPriceModel heatingPriceModel) throws Exception;
	//U
	public void update(HeatingPriceModel heatingPriceModel) throws Exception;
	//R
	public List<HeatingPriceModel> selectListByAll() throws Exception;
	//R2
	public HeatingPriceModel selectByYear(String heatingyear) throws Exception;
	//R3
	public List<HeatingPriceModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//D
	public void delete(HeatingPriceModel heatingPriceModel) throws Exception;
	//取得对象的个数
	public int selectCountByAll() throws Exception;
}
