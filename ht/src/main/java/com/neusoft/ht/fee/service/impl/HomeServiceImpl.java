package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IHomeMapper;
import com.neusoft.ht.fee.model.HomeModel;
import com.neusoft.ht.fee.service.IHomeService;
/**模块：供热缴费管理
 * 居民管理的Service实现类
 * @author 黄宇德
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HomeServiceImpl implements IHomeService {

	@Autowired
	private IHomeMapper homeMapper = null;
	@Override
	public void add(HomeModel homeModel) throws Exception {
		homeMapper.insert(homeModel);

	}

	@Override
	public void modify(HomeModel homeModel) throws Exception {
		homeMapper.update(homeModel);

	}

	@Override
	public void delete(HomeModel homeModel) throws Exception {
		homeMapper.delete(homeModel);

	}

	@Override
	public HomeModel getByNo(int homeno) throws Exception {
		
		return homeMapper.selectByNo(homeno);
	}

	@Override
	public List<HomeModel> getListByAll() throws Exception {
		
		return homeMapper.selectListByAll();
	}

	@Override
	public List<HomeModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return homeMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	public int getCountByAll() throws Exception {
		
		return homeMapper.selectCountByAll();
	}

	@Override
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
	//根据综合检索条件,取得居民列表取得关联的小区和户型,分页模式
	@Override
	public List<HomeModel> getListByConditionWithHoodNoAndHouseTypeNoWithPage(int hoodno, int housetypeno, int rows,
			int page) throws Exception {
		
		return homeMapper.selectListByConditionWithHoodNoAndHouseTypeNoWithPage(hoodno, housetypeno, rows*(page-1),rows);
	}

	//取关联小区和户型的特定居民
	@Override
	public HomeModel getByNoWithHoodNoAndHouseTypeNo(int homeno) throws Exception {
		
		return homeMapper.selectByNoWithHoodNoAndHouseTypeNo(homeno);
	}

	//修改供热状态
	@Override
	public void ChangeHeatingStatus(int homeno ,String heatingstatus) throws Exception {
		homeMapper.ChangeHeatingStatus(homeno,heatingstatus);	
	}
	



}
