package com.neusoft.ht.complain.service;

import java.util.List;

import com.neusoft.ht.complain.model.HomeComplainModel;

public interface IHomeComplainService {

	//增加投诉类型
	public void add(HomeComplainModel homeComplain) throws Exception;

	//修改投诉类型
	public void modify(HomeComplainModel homeComplain) throws Exception;

	//删除投诉类型
	public void delete(HomeComplainModel homeComplain) throws Exception;

	//查询所有对象
	public List<HomeComplainModel> getListByAll() throws Exception;

	//根据投诉类型编号查询对象
	public HomeComplainModel getHomeComplainBycomplainNo(int complainNo) throws Exception;

	//查询对象个数
	public int getCountByAll() throws Exception;

	//查询所有对象（分页）
	public List<HomeComplainModel> selectListByAllWithPage(int rows, int pages) throws Exception;

}