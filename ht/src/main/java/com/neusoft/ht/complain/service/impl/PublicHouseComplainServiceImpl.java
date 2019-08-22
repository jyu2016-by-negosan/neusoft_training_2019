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
	public List<PublicHouseComplainModel> getListByAllWithPage(int rows, int pages) throws Exception {
		return housemapper.selectListByAllWithPage((pages - 1) * rows, rows);
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		int pageCount = 0;
		int count = this.getCountByAll();
		if (count % rows == 0) {
			pageCount = count / rows;
		} else {
			pageCount = count / rows + 1;
		}
		return pageCount;
	}

	@Override
	public PublicHouseComplainModel getByNoWithHouseAndComplainType(int complainno) throws Exception {
		return housemapper.selectByNoWithHouseAndComplainType(complainno);
	}

	@Override
	public List<PublicHouseComplainModel> getListByConditionWithHouseAndComplainTypeWithPage(String complainstatus,
			int hoodno, String housename, String typename, int rows, int pages) throws Exception {

		return housemapper.selectListByConditionWithHouseAndComplainTypeWithPage(complainstatus, hoodno, housename,
				typename, rows, pages);
	}

	@Override
	public int getCountByAllWithHouseAndComplainTypeWithPage(String complainstatus, int hoodno, String housename,
			String typename) throws Exception {
		return housemapper.selectCountByAllWithHouseAndComplainTypeWithPage(complainstatus, hoodno, housename,
				typename);
	}

	@Override
	public int getPageCountByAll(int rows, String complainstatus, int hoodno, String housename, String typename)
			throws Exception {
		int pageCount = 0;
		int count = this.getCountByAllWithHouseAndComplainTypeWithPage(complainstatus, hoodno, housename, typename);
		if (count % rows == 0) {
			pageCount = count / rows;
		} else {
			pageCount = count / rows + 1;
		}
		return pageCount;
	}
}
