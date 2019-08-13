package com.neusoft.ht.complain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ht.complain.mapper.IPublicHouseComplainMapper;
import com.neusoft.ht.complain.model.PublicHouseComplainModel;
import com.neusoft.ht.complain.service.IPublicHouseComplainService;

@Service("HouseComplainService")
public class PublicHouseComplainServiceImpl implements IPublicHouseComplainService {

	@Autowired
	private IPublicHouseComplainMapper housemapper;

	@Override
	public void add(PublicHouseComplainModel houseComplain) throws Exception {
		housemapper.create(houseComplain);
	}

	@Override
	public void modify(PublicHouseComplainModel houseComplain) throws Exception {
		housemapper.update(houseComplain);
	}

	@Override
	public void delete(PublicHouseComplainModel houseComplain) throws Exception {
		housemapper.delete(houseComplain);
	}

	@Override
	public List<PublicHouseComplainModel> getListByAll() throws Exception {
		return housemapper.selectHouseComplainModelByAll();
	}

	@Override
	public PublicHouseComplainModel getHouseComplainBycomplainNo(int complainNo) throws Exception {
		return housemapper.selectHouseComplainModelByComplainNo(complainNo);
	}

	@Override
	public int getCountByAll() throws Exception {
		return housemapper.selectCountByAll();
	}

	@Override
	public List<PublicHouseComplainModel> selectListByAllWithPage(int rows, int pages) throws Exception {
		return housemapper.selectListByAllWithPage((pages-1)*rows, pages);
	}

}
