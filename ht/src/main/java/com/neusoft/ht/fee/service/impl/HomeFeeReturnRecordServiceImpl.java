package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IHomeFeeMapper;
import com.neusoft.ht.fee.mapper.IHomeFeeReturnRecordMapper;
import com.neusoft.ht.fee.model.HomeFeeModel;
import com.neusoft.ht.fee.model.HomeFeeReturnRecordModel;
import com.neusoft.ht.fee.service.IHomeFeeReturnRecordService;

/**
 * 供热居民退费管理模块
 * 
 * @author 罗妙忠
 *
 */
@Service
@Transactional
public class HomeFeeReturnRecordServiceImpl implements IHomeFeeReturnRecordService{

	@Autowired
	private IHomeFeeReturnRecordMapper homeFeeReturnRecordMapper;
	
	@Autowired
	private IHomeFeeMapper homeFeeMapper;
	
	@Override
	public void add(HomeFeeReturnRecordModel homeFeeReturnRecordModel) throws Exception{
		 homeFeeReturnRecordMapper.insert(homeFeeReturnRecordModel);
		 HomeFeeModel homeFeeModel =homeFeeReturnRecordModel.getHomeFeeModel();
		 float actualfee = homeFeeModel.getActualfee();
		 actualfee = actualfee-homeFeeReturnRecordModel.getAmount();
		 homeFeeModel.setActualfee(actualfee);
		 homeFeeMapper.update(homeFeeModel);
	}

	@Override
	public void delete(HomeFeeReturnRecordModel homeFeeReturnRecordModel) throws Exception{
		
		 HomeFeeReturnRecordModel  selectByIdmodel = homeFeeReturnRecordMapper.selectByIdWithHomeFee(homeFeeReturnRecordModel.getRecordno());
		 float amount =selectByIdmodel.getAmount();
		 HomeFeeModel homeFeeModel = selectByIdmodel.getHomeFeeModel(); 
		 float actualfee = homeFeeModel.getActualfee();
		 actualfee = actualfee+amount;
		 homeFeeModel.setActualfee(actualfee);	 
		 homeFeeMapper.update(homeFeeModel);
		 homeFeeReturnRecordMapper.delete(homeFeeReturnRecordModel);
		
	}

	@Override
	public void modify(HomeFeeReturnRecordModel homeFeeReturnRecordModel) {
		 homeFeeReturnRecordMapper.update(homeFeeReturnRecordModel);
		
	}

	@Override
	public HomeFeeReturnRecordModel getById(int recordno) {
		return homeFeeReturnRecordMapper.selectById(recordno);
		
	}

	@Override
	public List<HomeFeeReturnRecordModel> getAllByList() {
		
		return homeFeeReturnRecordMapper.selectAllByList();
	}

	@Override
	public List<HomeFeeReturnRecordModel> getAllByListWithPages(int rows, int page) {
		
		return homeFeeReturnRecordMapper.selectAllByListWithPages((page-1)*rows, rows);
	}

	@Override
	public HomeFeeReturnRecordModel getByIdWithHomeFee(int recordno) throws Exception {
		return homeFeeReturnRecordMapper.selectByIdWithHomeFee(recordno);
	}

	@Override
	public int getAllCount() throws Exception {
		return homeFeeReturnRecordMapper.selectAllCount();
	}

}
