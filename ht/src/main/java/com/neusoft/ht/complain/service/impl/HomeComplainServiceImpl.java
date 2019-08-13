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
	public List<HomeComplainModel> selectListByAllWithPage(int rows, int pages) throws Exception {
		return homeComplainMapper.selectListByAllWithPage((pages-1)*rows, pages);
	}

}
