package com.neusoft.ht.fee.service;

import java.util.List;

import com.neusoft.ht.fee.model.PaymentTypeModel;
/**模块：供热缴费管理
 * 付款类型管理的Service接口
 * @author 黄宇德
 *
 */
public interface IPaymentTypeService {
	//增加
	public void add(PaymentTypeModel paymentTypeModel ) throws Exception;
	//修改
	public void modify(PaymentTypeModel paymentTypeModel) throws Exception;
	//删除
	public void delete(PaymentTypeModel paymentTypeModel) throws Exception;
	//取得单个对象
	public PaymentTypeModel getByNo(int typeno) throws Exception;
	//取得所有对象
	public List<PaymentTypeModel> getListByAll() throws Exception;

	//取得所有对象列表,分页模式
	public List<PaymentTypeModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得对象的个数
	public int getCountByAll() throws Exception;
	//取得对象页数
	public int getPagaCountByAll(int rows) throws Exception;

}
