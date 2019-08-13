package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ht.fee.mapper.IHomeFeeReturnRecordMapper;
import com.neusoft.ht.fee.model.HomeFeeReturnRecordModel;
import com.neusoft.ht.fee.service.IHomeFeeReturnRecordService;

/**
 * 供热居民退费管理模块
 * 
 * @author 罗妙忠
 *
 */
@Service
public class HomeFeeReturnRecordServiceImpl implements IHomeFeeReturnRecordService{

	@Autowired
	private IHomeFeeReturnRecordMapper homeFeeReturnRecordMapper;
	
	@Override
	public void add(HomeFeeReturnRecordModel homeFeeReturnRecordModel) {
		 homeFeeReturnRecordMapper.insert(homeFeeReturnRecordModel);
		
	}

	@Override
	public void delete(HomeFeeReturnRecordModel homeFeeReturnRecordModel) {
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

}
