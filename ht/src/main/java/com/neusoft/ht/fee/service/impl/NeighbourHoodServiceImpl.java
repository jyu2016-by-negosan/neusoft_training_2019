package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.INeighbourHoodMapper;
import com.neusoft.ht.fee.model.NeighbourHoodModel;
import com.neusoft.ht.fee.service.INeighbourHoodService;
/**模块：供热缴费管理
 * 小区管理的Service实现类
 * @author 黄宇德
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NeighbourHoodServiceImpl implements INeighbourHoodService {

	@Autowired
	private INeighbourHoodMapper neighbourHoodMapper= null;
	
	@Override
	public void add(NeighbourHoodModel neighbourHoodModel) throws Exception {
		neighbourHoodMapper.insert(neighbourHoodModel);
	}

	@Override
	public void modify(NeighbourHoodModel neighbourHoodModel) throws Exception {
		neighbourHoodMapper.update(neighbourHoodModel);
		
	}

	@Override
	public void delete(NeighbourHoodModel neighbourHoodModel) throws Exception {
		neighbourHoodMapper.delete(neighbourHoodModel);
		
	}

	@Override
	@Transactional(readOnly = true)
	public NeighbourHoodModel getByNo(int hoodno) throws Exception {
		
		return neighbourHoodMapper.selectByNo(hoodno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<NeighbourHoodModel> getListByAll() throws Exception {
		
		return neighbourHoodMapper.selectListByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<NeighbourHoodModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return neighbourHoodMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
	
		return neighbourHoodMapper.selectCountByAll();
	}
	
	//取得小区页数
	@Override
	@Transactional(readOnly = true)
	public int getPagaCountByAll(int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByAll();
		if(count%rows==0) {
			pageCount=count/rows;
		}
		else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}


}
