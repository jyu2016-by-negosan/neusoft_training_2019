package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.PaymentTypeModel;
/**模块：供热缴费管理
 * 付款类型管理的Mapper接口
 * @author 黄宇德
 *
 */
public interface IPaymentTypeMapper {
	//C
	public void insert(PaymentTypeModel paymentTypeModel) throws Exception;
	//U
	public void update(PaymentTypeModel paymentTypeModel) throws Exception;
	//R
	public List<PaymentTypeModel> selectListByAll() throws Exception;
	//R2
	public PaymentTypeModel selectByNo(int typeno) throws Exception;
	//R3
	public List<PaymentTypeModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//D
	public void delete(PaymentTypeModel paymentTypeModel) throws Exception;
	//取得对象的个数
	public int selectCountByAll() throws Exception;

}
