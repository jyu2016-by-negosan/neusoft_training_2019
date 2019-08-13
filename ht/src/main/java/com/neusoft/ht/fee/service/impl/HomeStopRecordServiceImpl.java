package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ht.fee.mapper.IHomeStopRecordMapper;
import com.neusoft.ht.fee.model.HomeStopRecordModel;
import com.neusoft.ht.fee.service.IHomeStopRecordService;

/**
 * 供热报停管理模块
 * 
 * @author 罗妙忠
 *
 */
@Service
public class HomeStopRecordServiceImpl implements IHomeStopRecordService{

	@Autowired
	private IHomeStopRecordMapper homeStopRecordMapper;
	
	@Override
	public void add(HomeStopRecordModel homeStopRecordModel) {
		homeStopRecordMapper.insert(homeStopRecordModel);
		
	}

	@Override
	public void delete(HomeStopRecordModel homeStopRecordModel) {
		homeStopRecordMapper.delete(homeStopRecordModel);
		
	}

	@Override
	public void modify(HomeStopRecordModel homeStopRecordModel) {
		homeStopRecordMapper.update(homeStopRecordModel);
	}

	@Override
	public HomeStopRecordModel getById(int recordno) {
		return homeStopRecordMapper.selectById(recordno);
	}

	@Override
	public List<HomeStopRecordModel> getAllByList() {
		return homeStopRecordMapper.selectAllByList();
	}

	@Override
	public List<HomeStopRecordModel> getAllByListWithPages(int rows, int page) {
		return homeStopRecordMapper.selectAllByListWithPages((page-1)*rows, rows);
	}

}
