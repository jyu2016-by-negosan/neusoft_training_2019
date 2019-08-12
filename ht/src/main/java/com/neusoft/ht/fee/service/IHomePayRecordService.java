package com.neusoft.ht.fee.service;

import java.util.List;
import com.neusoft.ht.complain.model.HomePayRecordModel;

/**模块：供热缴费管理
 * 住宅供热缴费记录--Service
 * @author 罗妙忠
 *
 */
public interface IHomePayRecordService {
		//增加住宅缴费记录
		public void add(HomePayRecordModel payRecordModel);
		
		//删除住宅缴费记录
		public void delete(HomePayRecordModel payRecordModel);
		
		//更改住宅缴费记录
		public void modify(HomePayRecordModel payRecordModel);
		
		//根据居民缴费序号查找缴费记录
		public List<HomePayRecordModel> getById(int recordNo);
		
		//查找所有住宅缴费记录
		public List<HomePayRecordModel> getByAllList();
}
