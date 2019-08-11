package com.neusoft.ht.fee.service;

import java.util.List;

import com.neusoft.ht.fee.model.HouseTypeModel;
/**模块：供热缴费管理
 * 房型管理的Service接口
 * @author 黄宇德
 *
 */
public interface IHouseTypeService {

	//增加
	public void add(HouseTypeModel houseTypeModel) throws Exception;
	//修改
	public void modify(HouseTypeModel hosHouseTypeModel) throws Exception;
	//删除
	public void delete(HouseTypeModel houseTypeModel) throws Exception;
	//取得单个对象
	public HouseTypeModel getByNo(int typeno) throws Exception;
	//取得所有对象
	public List<HouseTypeModel> getListByAll() throws Exception;

	//取得所有对象列表,分页模式
	public List<HouseTypeModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得对象的个数
	public int getCountByAll() throws Exception;
	//取得对象页数
	public int getPagaCountByAll(int rows) throws Exception;


}
