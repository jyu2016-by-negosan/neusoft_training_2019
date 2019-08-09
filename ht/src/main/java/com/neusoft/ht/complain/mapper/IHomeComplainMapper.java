package com.neusoft.ht.complain.mapper;

import java.util.List;

import com.neusoft.ht.complain.model.HomeComplainModel;

public interface IHomeComplainMapper {

	// 增
	public void create(HomeComplainModel homeComplain) throws Exception;

	// 改
	public void update(HomeComplainModel homeComplain) throws Exception;

	// 删
	public void delete(HomeComplainModel homeComplain) throws Exception;

	// 查1
	public List<HomeComplainModel> selectHomeComplainModelByAll() throws Exception;

	// 查2
	public HomeComplainModel selectHomeComplainModelByComplainNo(long complainNo) throws Exception;
}
