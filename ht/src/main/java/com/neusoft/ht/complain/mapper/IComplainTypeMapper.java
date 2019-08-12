package com.neusoft.ht.complain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.ht.complain.model.ComplainTypeModel;

@Mapper
public interface IComplainTypeMapper {
	// 增
	public void create(ComplainTypeModel complainType) throws Exception;

	// 改
	public void update(ComplainTypeModel complainType) throws Exception;

	// 删
	public void delete(ComplainTypeModel complainType) throws Exception;

	// 查1
	public List<ComplainTypeModel> selectComplianTypeModelByAll() throws Exception;

	// 查2
	public ComplainTypeModel selectComplianTypeModelByTypeNo(long typeNo) throws Exception;
	
	
}
