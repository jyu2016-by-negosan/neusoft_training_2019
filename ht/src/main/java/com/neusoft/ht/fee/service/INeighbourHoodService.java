package com.neusoft.ht.fee.service;

import java.util.List;

import com.neusoft.ht.fee.model.NeighbourHoodModel;

/**模块：供热缴费管理
 * 小区管理的Service接口
 * @author 黄宇德
 *
 */
public interface INeighbourHoodService {
	//增加
	public void add(NeighbourHoodModel neighbourHoodModel) throws Exception;
	//修改
	public void modify(NeighbourHoodModel neighbourHoodModel) throws Exception;
	//删除
	public void delete(NeighbourHoodModel neighbourHoodModel) throws Exception;
	//取得单个小区
	public NeighbourHoodModel getByNo(int hoodno) throws Exception;
	//取得所有小区
	public List<NeighbourHoodModel> getListByAll() throws Exception;
	
	//取得所有小区列表,分页模式
	public List<NeighbourHoodModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得小区的个数
	public int getCountByAll() throws Exception;
	//取得小区页数
	public int getPagaCountByAll(int rows) throws Exception;

}
