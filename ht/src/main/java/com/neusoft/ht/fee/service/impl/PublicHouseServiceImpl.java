package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.ht.fee.mapper.IPublicHouseMapper;
import com.neusoft.ht.fee.model.PublicHouseModel;
import com.neusoft.ht.fee.service.IPublicHouseService;
/**模块：供热缴费管理
 * 供热公建表管理
 * @author 罗妙忠
 *
 */
@Service
public class PublicHouseServiceImpl implements IPublicHouseService {

	@Autowired
	private IPublicHouseMapper publicHouseMapper;
	
	@Override
	public void add(PublicHouseModel publicHouseModel) throws Exception {
		publicHouseMapper.insert(publicHouseModel);
	}

	@Override
	public void modify(PublicHouseModel publicHouseModel) throws Exception {
		publicHouseMapper.update(publicHouseModel);
	}

	@Override
	public List<PublicHouseModel> getListByAll() throws Exception {
		List<PublicHouseModel> list = publicHouseMapper.selectListByAll();
		return list;
	}

	@Override
	public PublicHouseModel getByNo(int houseno) throws Exception {
		 return publicHouseMapper.selectByNo(houseno);
		
	}

	@Override
	public List<PublicHouseModel> getListByAllWithPage(int rows, int page) throws Exception {
		List<PublicHouseModel> list = publicHouseMapper.selectListByAllWithPage((page-1)*rows, rows);
		return list;
	}

	@Override
	public void delete(PublicHouseModel publicHouseModel) throws Exception {
		publicHouseMapper.delete(publicHouseModel);
	}

	@Override
	public int getCountByAll() throws Exception {
		return publicHouseMapper.selectCountByAll();
	}

}
