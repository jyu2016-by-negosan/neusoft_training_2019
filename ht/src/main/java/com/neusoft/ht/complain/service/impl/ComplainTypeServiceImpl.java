package com.neusoft.ht.complain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ht.complain.mapper.IComplainTypeMapper;
import com.neusoft.ht.complain.model.ComplainTypeModel;
import com.neusoft.ht.complain.service.IComplainTypeService;

@Service("ComplainTypeService")
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
	public List<ComplainTypeModel> getListByAll() throws Exception {
		return complainTypeMapper.selectComplianTypeModelByAll();
	}

	@Override
	public ComplainTypeModel getComplainTypeBytypeNo(int typeNo) throws Exception {
		return complainTypeMapper.selectComplianTypeModelByTypeNo(typeNo);
	}

	@Override
	public int getCountByAll() throws Exception {
		return complainTypeMapper.selectCountByAll();
	}

	@Override
	public List<ComplainTypeModel> selectListByAllWithPage(int rows, int pages) throws Exception {
		return complainTypeMapper.selectListByAllWithPage((pages-1)*rows, rows);
	}

}