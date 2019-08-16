package com.neusoft.ht.fee.service;

import java.util.List;

import com.neusoft.ht.fee.model.HomeFeeModel;

/**
 * 模块：供热缴费管理
 * 住宅供热记录管理的Service接口
 * @author 黄宇德
 *
 */
public interface IHomeFeeService {
	//增加
	public void add(HomeFeeModel homeFeeModel) throws Exception;
	//修改
	public void modify(HomeFeeModel homeFeeModel) throws Exception;
	//查询所有对象
	public List<HomeFeeModel> getListByAll() throws Exception;
	//删除
	public void delete(HomeFeeModel homeFeeModel) throws Exception;
	//取得住宅供热信息记录的个数
	public int getCountByAll() throws Exception;
	//取得住宅供热信息记录的个数,带关联
	public int getCountByAllWithHomeAndHeatingPriceWithPage() throws Exception;
	//取得住宅供热信息记录页数
	public int getPageCountByAll(int rows) throws Exception;
	//取得特定居民供热记录，取关联的居民表和年度供热价格表
	public HomeFeeModel getByNoWithHomeAndHeatingPrice(int feeno) throws Exception;
	//根据综合检索条件取得居民供热记录列表，取得关联的居民表，取关联的年度供热价格表，分页模式
	public List<HomeFeeModel> getListByConditionWithHomeAndHeatingPriceWithPage(int homeno,String heatingyear, String feestautus,int rows, int page) throws Exception;
}
