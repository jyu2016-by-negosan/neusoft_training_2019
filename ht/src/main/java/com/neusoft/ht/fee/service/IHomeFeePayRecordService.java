package com.neusoft.ht.fee.service;

import java.util.List;
import com.neusoft.ht.fee.model.HomeFeePayRecordModel;

/**模块：供热缴费管理
 * 住宅供热缴费记录--Service
 * @author 罗妙忠
 *
 */
public interface IHomeFeePayRecordService {
		//增加住宅缴费记录
		public void add(HomeFeePayRecordModel payRecordModel);
		
		//删除住宅缴费记录
		public void delete(HomeFeePayRecordModel payRecordModel);
		
		//更改住宅缴费记录
		public void modify(HomeFeePayRecordModel payRecordModel);
		
		//根据居民缴费序号查找缴费记录
		public HomeFeePayRecordModel getById(int recordno);
		
		//查找所有住宅缴费记录
		public List<HomeFeePayRecordModel> getAllByList();
		
		//查找所有住宅缴费记录(分页)
		public List<HomeFeePayRecordModel> getAllByListWithPage(int page,int rows);
		
		//记录总条数
		public int getAllCount();
}
