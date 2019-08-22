package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public PublicHouseFeeModel getByNo(int feeno) throws Exception {
		return publichousefeemapper.selectByNo(feeno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PublicHouseFeeModel> getListByAllWithPage(int rows, int page) throws Exception {
		List<PublicHouseFeeModel> list = publichousefeemapper.selectListByAllWithPage((page - 1) * rows, rows);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		return publichousefeemapper.selectCountByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountPageByAll(int rows) throws Exception {
		int pageCount = 0;
		int count = this.getCountByAll();
		if (count % rows == 0) {
			pageCount = count / rows;
		}
		else {
			pageCount = count / rows + 1;
		}
		return pageCount;
	}
}
