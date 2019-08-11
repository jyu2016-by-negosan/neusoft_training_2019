package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HomeModel;

public interface IHomeMapper {
	//C
	public void insert(HomeModel homeModel) throws Exception;
	//U
	public void update(HomeModel homeModel) throws Exception;
	//R
	public List<HomeModel> selectListByAll() throws Exception;
	//R2
	public HomeModel selectByNo(int homeno) throws Exception;
	//R3
	public List<HomeModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//D
	public void delete(HomeModel homeModel) throws Exception;
	//取得对象的个数
	public int selectCountByAll() throws Exception;
}
