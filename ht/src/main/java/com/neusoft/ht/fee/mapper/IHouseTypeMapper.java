package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HouseTypeModel;

public interface IHouseTypeMapper {
	//C
	public void insert(HouseTypeModel houseTypeModel) throws Exception;
	//U
	public void update(HouseTypeModel houseTypeModel) throws Exception;
	//R
	public List<HouseTypeModel> selectListByAll() throws Exception;
	//R2
	public HouseTypeModel selectByNo(int typeno) throws Exception;
	//R3
	public List<HouseTypeModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//D
	public void delete(HouseTypeModel houseTypeModel) throws Exception;
	//取得对象的个数
	public int selectCountByAll() throws Exception;
}
