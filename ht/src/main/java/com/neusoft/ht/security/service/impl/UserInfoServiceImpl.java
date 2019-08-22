package com.neusoft.ht.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.security.mapper.IUserInfoMapper;
import com.neusoft.ht.security.model.UserInfoModel;
import com.neusoft.ht.security.service.IUserInfoService;
/**模块：系统安全模块
 * 系统管理员辅助信息的Service实现类
 * @author 吴澍淼
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl implements IUserInfoService{
	
	@Autowired(required=false)
	private IUserInfoMapper userInfoMapper=null;

	@Override
	public void add(UserInfoModel userInfoModel) throws Exception {
		userInfoMapper.insert(userInfoModel);
		
	}

	@Override
	public void modify(UserInfoModel userInfoModel) throws Exception {
		userInfoMapper.update(userInfoModel);
		
	}

	@Override
	public void delete(UserInfoModel userInfoModel) throws Exception {
		userInfoMapper.delete(userInfoModel);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserInfoModel> getListByAll() throws Exception {
		
		return userInfoMapper.selectListByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserInfoModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return userInfoMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public UserInfoModel getById(String uuserid) throws Exception {
		
		return userInfoMapper.selectById(uuserid);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		
		return userInfoMapper.selectCountByAll();
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
