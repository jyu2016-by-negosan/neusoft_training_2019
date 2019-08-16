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
	public List<HomeFeeModel> getListByAll() throws Exception {
		
		return homeFeeMapper.selectListByAll();
	}

	@Override
	public void delete(HomeFeeModel homeFeeModel) throws Exception {
		homeFeeMapper.delete(homeFeeModel);

	}

	@Override
	public HomeFeeModel getByNoWithHomeAndHeatingPrice(int feeno) throws Exception {
		
		return homeFeeMapper.selectByNoWithHomeAndHeatingPrice(feeno);
	}

	@Override
	public List<HomeFeeModel> getListByConditionWithHomeAndHeatingPriceWithPage(int homeno, int heatingyear,
			String feestautus, int rows, int page) throws Exception {
		
		return homeFeeMapper.selectListByConditionWithHomeAndHeatingPriceWithPage(homeno, heatingyear, feestautus, rows*(page-1), rows);
	}

}
