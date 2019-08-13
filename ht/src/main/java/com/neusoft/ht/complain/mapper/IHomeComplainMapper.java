package com.neusoft.ht.complain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.complain.model.HomeComplainModel;


/**
 * 
 * 模块：客户服务模块
 * 居民投诉类型的mapper接口
 * @author 万俊星
 * 
 * */
@Mapper
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

	
	public int selectCountByAll() throws Exception;

	
	public List<HomeComplainModel> selectListByAllWithPage(@Param("start")int start,@Param("end")int end) throws Exception;

}
