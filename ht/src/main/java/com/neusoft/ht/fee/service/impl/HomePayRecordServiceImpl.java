package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neusoft.ht.complain.model.HomePayRecordModel;
import com.neusoft.ht.fee.mapper.IHouseFeeRecordMapper;
import com.neusoft.ht.fee.service.IHomePayRecordService;
/**模块：供热缴费管理
 * 住宅供热缴费记录--ServiceImpl
 * @author 罗妙忠
 *
 */
@Service
public class HomePayRecordServiceImpl implements IHomePayRecordService{
	
	@Autowired
	private IHouseFeeRecordMapper houseFeeRecordMapper;
	
	//增加住宅缴费记录
	public void add(HomePayRecordModel payRecordModel) {
		houseFeeRecordMapper.insert(payRecordModel);
	}
			
	//删除住宅缴费记录
	public void delete(HomePayRecordModel payRecordModel) {
		 houseFeeRecordMapper.delete(payRecordModel);
	}
			
	//更改住宅缴费记录
	public void modify(HomePayRecordModel payRecordModel) {
		 houseFeeRecordMapper.update(payRecordModel);
	}
			
	//根据居民缴费序号查找缴费记录
	public List<HomePayRecordModel> getById(int recordNo) {
		 List<HomePayRecordModel> list = houseFeeRecordMapper.selectById(recordNo);
		 return list;
	}
			
	//查找所有住宅缴费记录
	public List<HomePayRecordModel> getByAllList(){
		List<HomePayRecordModel> list = houseFeeRecordMapper.selectByAllList();
		return list;
	}
}
