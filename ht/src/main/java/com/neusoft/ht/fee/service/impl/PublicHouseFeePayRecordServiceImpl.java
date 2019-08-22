package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IPublicHouseFeePayRecordMapper;
import com.neusoft.ht.fee.model.PublicHouseFeePayRecordModel;
import com.neusoft.ht.fee.service.IPublicHouseFeePayRecordService;

/**
 * 模块：供热缴费管理
 * 功能：公建供热缴费记录
 * 
 * @author 张晓龙
 * */
@Service
public class PublicHouseFeePayRecordServiceImpl implements IPublicHouseFeePayRecordService {
	
	@Autowired
	private IPublicHouseFeePayRecordMapper publicHouseFeePayRecordMapper = null;

	@Override
	public void add(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception {
		publicHouseFeePayRecordMapper.create(publicHouseFeePayRecordModel);
	}

	@Override
	public void modify(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception {
		publicHouseFeePayRecordMapper.update(publicHouseFeePayRecordModel);
	}

	@Override
	public void delete(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception {
		publicHouseFeePayRecordMapper.delete(publicHouseFeePayRecordModel);
	}

	@Override
	public List<PublicHouseFeePayRecordModel> getListByAll() throws Exception {
		List<PublicHouseFeePayRecordModel> list = publicHouseFeePayRecordMapper.selectListByAll();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PublicHouseFeePayRecordModel getByNo(int recordno) throws Exception {
		return publicHouseFeePayRecordMapper.selectByNo(recordno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PublicHouseFeePayRecordModel> getListByAllWithPage(int rows, int page) throws Exception {
		List<PublicHouseFeePayRecordModel> list = publicHouseFeePayRecordMapper.selectListByAllWithPage((page - 1) * rows, rows);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		return publicHouseFeePayRecordMapper.selectCountByAll();
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
