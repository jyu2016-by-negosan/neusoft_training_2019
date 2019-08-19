package com.neusoft.ht.complain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ht.complain.mapper.IHomeComplainMapper;
import com.neusoft.ht.complain.model.HomeComplainModel;
import com.neusoft.ht.complain.service.IHomeComplainService;

@Service("HomeComplainService")
public class HomeComplainServiceImpl implements IHomeComplainService {

	@Autowired
	private IHomeComplainMapper homeComplainMapper;

	@Override
	public void add(HomeComplainModel homeComplain) throws Exception {
		homeComplainMapper.create(homeComplain);
	}

	@Override
	public void modify(HomeComplainModel homeComplain) throws Exception {
		homeComplainMapper.update(homeComplain);
	}

	@Override
	public void delete(HomeComplainModel homeComplain) throws Exception {
		homeComplainMapper.delete(homeComplain);
	}

	@Override
	public List<HomeComplainModel> getListByAll() throws Exception {
		return homeComplainMapper.selectHomeComplainModelByAll();
	}

	@Override
	public HomeComplainModel getHomeComplainBycomplainNo(int complainNo) throws Exception {
		return homeComplainMapper.selectHomeComplainModelByComplainNo(complainNo);
	}

	@Override
	public int getCountByAll() throws Exception {
		return homeComplainMapper.selectCountByAll();
	}

	@Override
	public List<HomeComplainModel> getListByAllWithPage(int rows, int pages) throws Exception {
		return homeComplainMapper.selectListByAllWithPage((pages - 1) * rows, rows);
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

}
