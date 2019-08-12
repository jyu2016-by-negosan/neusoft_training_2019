package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IHouseTypeMapper;
import com.neusoft.ht.fee.model.HouseTypeModel;
import com.neusoft.ht.fee.service.IHouseTypeService;
/**模块：供热缴费管理
 * 房型管理的Service实现类
 * @author 黄宇德
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HouseTypeServiceImpl implements IHouseTypeService {

	@Autowired
	private IHouseTypeMapper houseTypeMapper = null;
	
	@Override
	public void add(HouseTypeModel houseTypeModel) throws Exception {
		houseTypeMapper.insert(houseTypeModel);
		
	}

	@Override
	public void modify(HouseTypeModel houseTypeModel) throws Exception {
		houseTypeMapper.update(houseTypeModel);
		
	}

	@Override
	public void delete(HouseTypeModel houseTypeModel) throws Exception {
		houseTypeMapper.delete(houseTypeModel);
		
	}

	@Override
	@Transactional(readOnly = true)
	public HouseTypeModel getByNo(int typeno) throws Exception {
		
		return houseTypeMapper.selectByNo(typeno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HouseTypeModel> getListByAll() throws Exception {
		
		return houseTypeMapper.selectListByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<HouseTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
	
		return houseTypeMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		
		return houseTypeMapper.selectCountByAll();
	}

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
