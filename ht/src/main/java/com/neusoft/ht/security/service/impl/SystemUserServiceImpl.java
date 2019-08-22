package com.neusoft.ht.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.security.mapper.ISystemUserMapper;
import com.neusoft.ht.security.model.SystemUserModel;
import com.neusoft.ht.security.service.ISystemUserService;


/**模块：系统安全模块
 * 操作员的Service实现类
 * @author 吴澍淼
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemUserServiceImpl implements ISystemUserService{
	
	@Autowired(required=false)
	private ISystemUserMapper systemUserMapper=null;
	
	@Override
	public void add(SystemUserModel systemUserModel) throws Exception {
		systemUserMapper.insert(systemUserModel);
		
	}

	@Override
	public void modify(SystemUserModel systemUserModel) throws Exception {
		systemUserMapper.update(systemUserModel);
		
	}

	@Override
	public void delete(SystemUserModel systemUserModel) throws Exception {
		systemUserMapper.delete(systemUserModel);
		
	}
	
	@Override
	public boolean validate(String uuserid, String upassword) throws Exception {
		SystemUserModel systemUserModel = systemUserMapper.selectByIdWithoutOther(uuserid);
		if ( systemUserModel != null && systemUserModel.getUpassword().equals(upassword) )
			return true;
		return false;
	}	

	@Override
	@Transactional(readOnly = true)
	public List<SystemUserModel> getListByAll() throws Exception {
		
		return systemUserMapper.selectListByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemUserModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return systemUserMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public SystemUserModel getById(String uuserid) throws Exception {
		
		return systemUserMapper.selectById(uuserid);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		
		return systemUserMapper.selectCountByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int getPageCountByAll(int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByAll();
		if (count%rows == 0) {
			pageCount=count/rows;
		} else {
			pageCount=count/rows+1;
		}
		return pageCount;
	}
	
}
