package com.neusoft.ht.fee.service;

import java.util.List;

import com.neusoft.ht.fee.model.HeatingPriceModel;

public interface IHeatingPriceService {
	//增加
	public void add(HeatingPriceModel heatingPriceModel) throws Exception;
	//修改
	public void modify(HeatingPriceModel heatingPriceModel) throws Exception;
	//删除
	public void delete(HeatingPriceModel heatingPriceModel) throws Exception;
	//取得单个对象
	public HeatingPriceModel getByYear(String heatingyear) throws Exception;
	//取得所有对象
	public List<HeatingPriceModel> getListByAllWithPriceAndDay() throws Exception;

	//取得所有对象列表,分页模式
	public List<HeatingPriceModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得对象的个数
	public int getCountByAll() throws Exception;
	//取得对象页数
	public int getPagaCountByAll(int rows) throws Exception;
}
