package com.neusoft.ht.complain.mapper;

import java.util.List;

import com.neusoft.ht.complain.model.HouseComplainModel;

public interface IHouseComplainMapper {
	// 增
	public void create(HouseComplainModel houseComplain) throws Exception;

	// 改
	public void update(HouseComplainModel houseComplain) throws Exception;

	// 删
	public void delete(HouseComplainModel houseComplain) throws Exception;

	// 查1
	public List<HouseComplainModel> selectHouseComplainModelByAll() throws Exception;

	// 查2
	public HouseComplainModel selectHouseComplainModelByComplainNo(long complainNo) throws Exception;
}
