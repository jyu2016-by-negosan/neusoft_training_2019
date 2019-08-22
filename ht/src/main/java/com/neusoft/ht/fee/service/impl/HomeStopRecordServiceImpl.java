package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IHomeFeeMapper;
import com.neusoft.ht.fee.mapper.IHomeStopRecordMapper;
import com.neusoft.ht.fee.model.HomeFeeModel;
import com.neusoft.ht.fee.model.HomeStopRecordModel;
import com.neusoft.ht.fee.service.IHomeStopRecordService;

/**
 * 供热报停管理模块
 * 
 * @author 罗妙忠
 *
 */
@Service
@Transactional
public class HomeStopRecordServiceImpl implements IHomeStopRecordService{

	@Autowired
	private IHomeStopRecordMapper homeStopRecordMapper;
	
	@Autowired
	private IHomeFeeMapper homeFeeMapper;
	
	@Override
	public void add(HomeStopRecordModel homeStopRecordModel) throws Exception{
		float stoparea = homeStopRecordModel.getHomeFeeModel().getHome().getHeatingarea();
		homeStopRecordModel.setStoparea(stoparea);
		homeStopRecordMapper.insert(homeStopRecordModel);
		homeFeeMapper.update(homeStopRecordModel.getHomeFeeModel());
	}

	@Override
	public void delete(HomeStopRecordModel homeStopRecordModel) throws Exception{
		homeStopRecordMapper.delete(homeStopRecordModel);
		
	}

	@Override
	public void modify(HomeStopRecordModel homeStopRecordModel)throws Exception {
		homeStopRecordMapper.update(homeStopRecordModel);
		
	}

	@Override
	public HomeStopRecordModel getById(int recordno)throws Exception {
		return homeStopRecordMapper.selectById(recordno);
	}

	@Override
	public List<HomeStopRecordModel> getAllByList() throws Exception{
		return homeStopRecordMapper.selectAllByList();
	}

	@Override
	public List<HomeStopRecordModel> getAllByListWithPages(int rows, int page) throws Exception{
		return homeStopRecordMapper.selectAllByListWithPages((page-1)*rows, rows);
	}

	@Override
	public HomeStopRecordModel getByIdWithHomeFee(int recordno) {
		return homeStopRecordMapper.selectByIdWithHomeFee(recordno);
	}

	@Override
	public int getAllCount() throws Exception {
		return homeStopRecordMapper.selectAllCount();
	}

}
