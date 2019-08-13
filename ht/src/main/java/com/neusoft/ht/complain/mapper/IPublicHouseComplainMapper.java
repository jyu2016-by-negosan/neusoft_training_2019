package com.neusoft.ht.complain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.complain.model.PublicHouseComplainModel;

/**
 * 
 * 模块：客户服务模块 公建投诉类型的mapper接口
 * 
 * @author 万俊星
 * 
 */
@Mapper
public interface IPublicHouseComplainMapper {
	// 增
	public void create(PublicHouseComplainModel houseComplain) throws Exception;

	// 改
	public void update(PublicHouseComplainModel houseComplain) throws Exception;

	// 删
	public void delete(PublicHouseComplainModel houseComplain) throws Exception;

	// 查1
	public List<PublicHouseComplainModel> selectHouseComplainModelByAll() throws Exception;

	// 查2
	public PublicHouseComplainModel selectHouseComplainModelByComplainNo(long complainNo) throws Exception;

	
	public int selectCountByAll() throws Exception;

	
	public List<PublicHouseComplainModel> selectListByAllWithPage(@Param("start")int start,@Param("end")int end) throws Exception;
}
