package com.neusoft.ht.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.security.mapper.ISystemFunctionMapper;
import com.neusoft.ht.security.model.SystemFunctionModel;
import com.neusoft.ht.security.service.ISystemFunctionService;
/**模块：系统安全模块
  * 系统功能的Service实现类
 * @author 吴澍淼
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemFunctionServiceImpl implements ISystemFunctionService{
	
	@Autowired(required=false)
	private ISystemFunctionMapper systemFunctionMapper=null;

	@Override
	public void add(SystemFunctionModel systemFunctionModel) throws Exception {
		systemFunctionMapper.insert(systemFunctionModel);
		
	}

	@Override
	public void modify(SystemFunctionModel systemFunctionModel) throws Exception {
		systemFunctionMapper.update(systemFunctionModel);
		
	}

	@Override
	public void delete(SystemFunctionModel systemFunctionModel) throws Exception {
		systemFunctionMapper.delete(systemFunctionModel);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemFunctionModel> getListByAll() throws Exception {
		
		return systemFunctionMapper.selectListByAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<SystemFunctionModel> getListByAllWithPage(int rows, int page) throws Exception {
		
		return systemFunctionMapper.selectListByAllWithPage(rows*(page-1), rows);
	}

	@Override
	@Transactional(readOnly = true)
	public SystemFunctionModel getByNo(int funno) throws Exception {
		
		return systemFunctionMapper.selectByNo(funno);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByAll() throws Exception {
		
		return systemFunctionMapper.selectCountByAll();
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
	
	@Override
	public int getCountByNo(int funno) throws Exception{
		
		return systemFunctionMapper.selectCountByNo(funno);
	}

}
