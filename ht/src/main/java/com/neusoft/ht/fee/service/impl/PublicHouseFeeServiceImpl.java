package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ht.fee.mapper.IPublicHouseFeeMapper;
import com.neusoft.ht.fee.model.PublicHouseFeeModel;
import com.neusoft.ht.fee.service.IPublicHouseFeeService;

@Service
public class PublicHouseFeeServiceImpl implements IPublicHouseFeeService {
	
	@Autowired
	private IPublicHouseFeeMapper publichousefeemapper;

	@Override
	public void add(PublicHouseFeeModel publicHouseFeeModel) throws Exception {
		publichousefeemapper.insert(publicHouseFeeModel);
	}

	@Override
	public void modify(PublicHouseFeeModel publicHouseFeeModel) throws Exception {
		publichousefeemapper.update(publicHouseFeeModel);
	}

	@Override
	public void delete(PublicHouseFeeModel publicHouseFeeModel) throws Exception {
		publichousefeemapper.delete(publicHouseFeeModel);
	}

	@Override
	public List<PublicHouseFeeModel> getListByAll() throws Exception {
		List<PublicHouseFeeModel> list = publichousefeemapper.selectListByAll();
		return list;
	}

	@Override
	public PublicHouseFeeModel getByNo(int houseno) throws Exception {
		return publichousefeemapper.selectByNo(houseno);
	}

	@Override
	public List<PublicHouseFeeModel> getListByAllWithPage(int rows, int page) throws Exception {
		List<PublicHouseFeeModel> list = publichousefeemapper.selectListByAllWithPage((page - 1) * rows, rows);
		return list;
	}

	@Override
	public int getCountByAll() throws Exception {
		return publichousefeemapper.selectCountByAll();
	}

}
