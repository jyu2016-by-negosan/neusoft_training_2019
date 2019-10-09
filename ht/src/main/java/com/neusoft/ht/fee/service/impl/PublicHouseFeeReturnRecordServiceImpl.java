package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IPublicHouseFeeReturnRecordMapper;
import com.neusoft.ht.fee.model.PublicHouseFeeReturnRecordModel;
import com.neusoft.ht.fee.service.IPublicHouseFeeReturnRecordService;

/**
 * 模块：供热缴费管理
 * 功能：公建供热退费记录
 * 
 * @author 张晓龙
 * */
@Service
public class PublicHouseFeeReturnRecordServiceImpl implements IPublicHouseFeeReturnRecordService {

	@Autowired
	private IPublicHouseFeeReturnRecordMapper publichousefeereturnrecordmapper = null;
	
	@Override
	public void add(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception {
		publichousefeereturnrecordmapper.create(publicHouseFeeReturnRecordModel);
	}

	@Override
	public void modify(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception {
		publichousefeereturnrecordmapper.update(publicHouseFeeReturnRecordModel);
	}

	@Override
	public void delete(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception {
		publichousefeereturnrecordmapper.delete(publicHouseFeeReturnRecordModel);
	}

	@Override
	public List<PublicHouseFeeReturnRecordModel> getListByAll() throws Exception {
		List<PublicHouseFeeReturnRecordModel> list = publichousefeereturnrecordmapper.selectListByAll();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PublicHouseFeeReturnRecordModel getByNo(int recordno) throws Exception {
		return publichousefeereturnrecordmapper.selectByNo(recordno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PublicHouseFeeReturnRecordModel> getListByAllWithPage(int rows, int page) throws Exception {
		List<PublicHouseFeeReturnRecordModel> list = publichousefeereturnrecordmapper.selectListByAllWithPage((page - 1) * rows, rows);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		return publichousefeereturnrecordmapper.selectCountByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountPageByAll(int rows) throws Exception {
		int pageCount = 0;
		int count = this.getCountByAll();
		if(count % rows == 0) {
			pageCount = count / rows;
		}
		else {
			pageCount = count / rows + 1;
		}
		return pageCount;
	}
}
