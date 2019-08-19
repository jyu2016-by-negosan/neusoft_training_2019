package com.neusoft.ht.fee.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HomeModel;

/**模块：供热缴费管理
 * 居民管理的Service接口
 * @author 黄宇德
 *
 */
public interface IHomeService {
	//增加
	public void add(HomeModel homeModel) throws Exception;
	//修改
	public void modify(HomeModel homeModel) throws Exception;
	//删除
	public void delete(HomeModel homeModel) throws Exception;
	//取得单个居民
	public HomeModel  getByNo(int homeno) throws Exception;
	//取关联小区和户型的特定居民
	public HomeModel  getByNoWithHoodNoAndHouseTypeNo(int homeno) throws Exception;
	//取得所有居民
	public List<HomeModel> getListByAll() throws Exception;
	
	//取得所有居民列表,分页模式
	public List<HomeModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得居民的个数
	public int getCountByAll() throws Exception;
	//取得居民页数
	public int getPagaCountByAll(int rows) throws Exception;
	//根据综合检索条件,取得居民列表取得关联的小区和户型,分页模式
	public List<HomeModel> getListByConditionWithHoodNoAndHouseTypeNoWithPage(@Param("hoodno") int hoodno,@Param("housetypeno") int housetypeno,int rows, int page) throws Exception;
	//修改供热状态
	public void ChangeHeatingStatus(int homeno,String heatingstatus) throws Exception;
}
