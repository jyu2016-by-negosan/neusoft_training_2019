package com.neusoft.ht.fee.service.impl;
/**
 * 模块：供热缴费管理
 * 住宅供热记录管理的Service实现类
 * @author 黄宇德
 *
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IHomeFeeMapper;
import com.neusoft.ht.fee.model.HomeFeeModel;
import com.neusoft.ht.fee.service.IHomeFeeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class HomeFeeServiceImpl implements IHomeFeeService {

	@Autowired
	private IHomeFeeMapper homeFeeMapper = null;
	@Override
	public void add(HomeFeeModel homeFeeModel) throws Exception {
		homeFeeMapper.insert(homeFeeModel);

	}

	@Override
	public void modify(HomeFeeModel homeFeeModel) throws Exception {
		homeFeeMapper.update(homeFeeModel);

	}

	@Override
	@Transactional(readOnly = true)
	public List<HomeFeeModel> getListByAll() throws Exception {
		
		return homeFeeMapper.selectListByAll();
	}

	@Override
	public void delete(HomeFeeModel homeFeeModel) throws Exception {
		homeFeeMapper.delete(homeFeeModel);

	}
	
	//取得特定居民供热记录，取关联的居民表和年度供热价格表
	@Override
	@Transactional(readOnly = true)
	public HomeFeeModel getByNoWithHomeAndHeatingPrice(int feeno) throws Exception {
		
		return homeFeeMapper.selectByNoWithHomeAndHeatingPrice(feeno);
	}
	
	//根据综合检索条件取得居民供热记录列表，取得关联的居民表，取关联的年度供热价格表，分页模式
	@Override
	@Transactional(readOnly = true)
	public List<HomeFeeModel> getListByConditionWithHomeAndHeatingPriceWithPage(int homeno, String heatingyear,
			String feestautus, int rows, int page) throws Exception {
		
		return homeFeeMapper.selectListByConditionWithHomeAndHeatingPriceWithPage(homeno, heatingyear, feestautus, rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		
		return homeFeeMapper.selectCountByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int getPageCountByAll(int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByAllWithHomeAndHeatingPriceWithPage();
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}
	
	//取得住宅供热信息记录的个数,带关联	
	@Override
	public int getCountByAllWithHomeAndHeatingPriceWithPage() throws Exception {
		
		return homeFeeMapper.selectCountByAllWithHomeAndHeatingPriceWithPage();
	}

}
