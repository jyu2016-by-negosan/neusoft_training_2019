package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neusoft.ht.fee.mapper.IHomeFeePayRecordMapper;
import com.neusoft.ht.fee.model.HomeFeePayRecordModel;
import com.neusoft.ht.fee.service.IHomeFeePayRecordService;
/**模块：供热缴费管理
 * 住宅供热缴费记录--ServiceImpl
 * @author 罗妙忠
 *
 */
@Service
public class HomeFeePayRecordServiceImpl implements IHomeFeePayRecordService{
	
	@Autowired
	private IHomeFeePayRecordMapper houseFeePayRecordMapper;
	
	//增加住宅缴费记录
	public void add(HomeFeePayRecordModel payRecordModel) {
		houseFeePayRecordMapper.insert(payRecordModel);
	}
			
	//删除住宅缴费记录
	public void delete(HomeFeePayRecordModel payRecordModel) {
		 houseFeePayRecordMapper.delete(payRecordModel);
	}
			
	//更改住宅缴费记录
	public void modify(HomeFeePayRecordModel payRecordModel) {
		 houseFeePayRecordMapper.update(payRecordModel);
	}
			
	//根据居民缴费序号查找缴费记录
	public HomeFeePayRecordModel getById(int recordNo) {
		return houseFeePayRecordMapper.selectById(recordNo);
		
	}
			
	
	//查找所有住宅缴费记录（分页）
	public List<HomeFeePayRecordModel> getAllByListWithPage(int page,int rows){
		return houseFeePayRecordMapper.selectAllByListWithPage((page-1)*rows, rows);
		
	}

	@Override
	public List<HomeFeePayRecordModel> getAllByList() {
		return  houseFeePayRecordMapper.selectAllByList();
		
	}

	@Override
	public int getAllCount() {
		return houseFeePayRecordMapper.selectAllCount();
	}

	
}
