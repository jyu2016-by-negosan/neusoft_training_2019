package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IPaymentTypeMapper;
import com.neusoft.ht.fee.model.PaymentTypeModel;
import com.neusoft.ht.fee.service.IPaymentTypeService;
/**模块：供热缴费管理
 * 付款类型管理的Service实现类
 * @author 黄宇德
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentTypeServiceImpl implements IPaymentTypeService {
	
	@Autowired
	private IPaymentTypeMapper paymentTypeMapper = null;
	
	@Override
	public void add(PaymentTypeModel paymentTypeModel) throws Exception {
		paymentTypeMapper.insert(paymentTypeModel);

	}

	@Override
	public void modify(PaymentTypeModel paymentTypeModel) throws Exception {
		paymentTypeMapper.update(paymentTypeModel);
	}

	@Override
	public void delete(PaymentTypeModel paymentTypeModel) throws Exception {
		paymentTypeMapper.delete(paymentTypeModel);

	}

	@Override
	@Transactional(readOnly = true)
	public PaymentTypeModel getByNo(int typeno) throws Exception {
		
		return paymentTypeMapper.selectByNo(typeno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaymentTypeModel> getListByAll() throws Exception {
		
		return paymentTypeMapper.selectListByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaymentTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return paymentTypeMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		
		return paymentTypeMapper.selectCountByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int getPagaCountByAll(int rows) throws Exception{
		
		int pageCount=0;
		int count=this.getCountByAll();
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}

}
