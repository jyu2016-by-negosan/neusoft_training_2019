package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IPublicHouseStopRecordMapper;
import com.neusoft.ht.fee.model.PublicHouseStopRecordModel;
import com.neusoft.ht.fee.service.IPublicHouseStopRecordService;

@Service
public class PublicHouseStopRecordServiceImpl implements IPublicHouseStopRecordService {
	
	@Autowired
	private IPublicHouseStopRecordMapper publicHouseStopRecordMapper = null;

	@Override
	public void add(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception {
		publicHouseStopRecordMapper.create(publicHouseStopRecordModel);
	}

	@Override
	public void modify(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception {
		publicHouseStopRecordMapper.update(publicHouseStopRecordModel);
	}

	@Override
	public void delete(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception {
		publicHouseStopRecordMapper.delete(publicHouseStopRecordModel);
	}

	@Override
	public List<PublicHouseStopRecordModel> getListByAll() throws Exception {
		List<PublicHouseStopRecordModel> list = publicHouseStopRecordMapper.selectListByAll();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PublicHouseStopRecordModel getByNo(int recordno) throws Exception {
		return publicHouseStopRecordMapper.selectByNo(recordno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PublicHouseStopRecordModel> getListByAllWithPage(int rows, int page) throws Exception {
		List<PublicHouseStopRecordModel> list = publicHouseStopRecordMapper.selectListByAllWithPage((page - 1) * rows, rows);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		return publicHouseStopRecordMapper.selectCountByAll();
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
