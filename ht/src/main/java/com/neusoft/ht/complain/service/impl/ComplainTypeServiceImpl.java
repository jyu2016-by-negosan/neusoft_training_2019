package com.neusoft.ht.complain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.complain.mapper.IComplainTypeMapper;
import com.neusoft.ht.complain.model.ComplainTypeModel;
import com.neusoft.ht.complain.service.IComplainTypeService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ComplainTypeServiceImpl implements IComplainTypeService {

	@Autowired
	private IComplainTypeMapper complainTypeMapper;

	@Override
	public void add(ComplainTypeModel complianType) throws Exception {
		complainTypeMapper.create(complianType);
	}

	@Override
	public void modify(ComplainTypeModel complainType) throws Exception {
		complainTypeMapper.update(complainType);
	}

	@Override
	public void delete(ComplainTypeModel complainType) throws Exception {
		complainTypeMapper.delete(complainType);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ComplainTypeModel> getListByAll() throws Exception {
		return complainTypeMapper.selectComplianTypeModelByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ComplainTypeModel getComplainTypeBytypeNo(int typeNo) throws Exception {
		return complainTypeMapper.selectComplianTypeModelByTypeNo(typeNo);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		return complainTypeMapper.selectCountByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ComplainTypeModel> getListByAllWithPage(int rows, int pages) throws Exception {
		return complainTypeMapper.selectListByAllWithPage((pages - 1) * rows, rows);
	}

	@Override
	@Transactional(readOnly = true)
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