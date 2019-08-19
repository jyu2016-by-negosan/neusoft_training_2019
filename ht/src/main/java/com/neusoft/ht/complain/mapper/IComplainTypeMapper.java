package com.neusoft.ht.complain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.complain.model.ComplainTypeModel;

/**
 * 
 * 模块：客户服务模块 投诉类型的mapper接口
 * 
 * @author 万俊星
 * 
 */

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
	public ComplainTypeModel selectComplianTypeModelByTypeNo(int typeno) throws Exception;

	public int selectCountByAll() throws Exception;

	public List<ComplainTypeModel> selectListByAllWithPage(@Param("start")int start,@Param("end")int end) throws Exception;

}
