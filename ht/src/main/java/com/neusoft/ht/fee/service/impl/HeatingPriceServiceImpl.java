package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IHeatingPriceMapper;
import com.neusoft.ht.fee.model.HeatingPriceModel;
import com.neusoft.ht.fee.service.IHeatingPriceService;
/**模块：供热缴费管理
 * 年份供热价格管理的Service实现类
 * @author 黄宇德
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HeatingPriceServiceImpl implements IHeatingPriceService {
	
	@Autowired
	private IHeatingPriceMapper heatingPriceMapper = null;
	@Override
	public void add(HeatingPriceModel heatingPriceModel) throws Exception {
			heatingPriceMapper.insert(heatingPriceModel);
		
	}

	@Override
	public void modify(HeatingPriceModel heatingPriceModel) throws Exception {
		heatingPriceMapper.update(heatingPriceModel);

	}

	@Override
	public void delete(HeatingPriceModel heatingPriceModel) throws Exception {
		heatingPriceMapper.delete(heatingPriceModel);

	}

	@Override
	@Transactional(readOnly = true)
	public HeatingPriceModel getByYear(String heatingyear) throws Exception {
		
		return heatingPriceMapper.selectByYear(heatingyear);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HeatingPriceModel> getListByAllWithPriceAndDay() throws Exception {
		
		return heatingPriceMapper.selectListByAllWithPriceAndDay();
	}

	@Override
	@Transactional(readOnly = true)
	public List<HeatingPriceModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return heatingPriceMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		
		return heatingPriceMapper.selectCountByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int getPagaCountByAll(int rows) throws Exception {
		
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

	//检查年份是否已存在
	@Override
	public int getCountByYear(String heatingyear) throws Exception {
		
		return heatingPriceMapper.selectCountByYear(heatingyear);
	}
	
	//修改实际供热天数
	@Override
	public void ChangeHeatingdays(String heatingyear,int heatingdays) throws Exception {
		heatingPriceMapper.ChangeHeatingdays(heatingyear,heatingdays);	
	}

}
