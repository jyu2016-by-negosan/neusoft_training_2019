package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.ht.complain.model.HomePayRecordModel;

/**模块：供热缴费管理
 * 住宅供热缴费记录--Mapper接口
 * @author 罗妙忠
 *
 */
@Mapper
public interface IHouseFeeRecordMapper {
	//增加住宅缴费记录
	public void insert(HomePayRecordModel payRecordModel);
	
	//删除住宅缴费记录
	public void delete(HomePayRecordModel payRecordModel);
	
	//更改住宅缴费记录
	public void update(HomePayRecordModel payRecordModel);
	
	//根据居民缴费序号查找缴费记录
	public List<HomePayRecordModel> selectById(int recordNo);
	
	//查找所有住宅缴费记录
	public List<HomePayRecordModel> selectByAllList();
	
	
}
