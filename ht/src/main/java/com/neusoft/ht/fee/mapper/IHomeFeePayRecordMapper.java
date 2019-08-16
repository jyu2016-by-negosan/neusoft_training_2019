package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HomeFeePayRecordModel;

/**模块：供热缴费管理 
 * 
 * @author 罗妙忠
 *
 */
@Mapper
public interface IHomeFeePayRecordMapper {
	//增加住宅缴费记录
	public void insert( HomeFeePayRecordModel payRecordModel);
	
	//删除住宅缴费记录
	public void delete( HomeFeePayRecordModel payRecordModel);
	
	//更改住宅缴费记录
	public void update( HomeFeePayRecordModel payRecordModel);
	
	//根据居民缴费序号查找缴费记录
	public HomeFeePayRecordModel selectById(int recordNo);
	
	//查找所有住宅缴费记录
	public List< HomeFeePayRecordModel> selectAllByList();
	
	//查找所有住宅缴费记录（分页）
	public List<HomeFeePayRecordModel> selectAllByListWithPage(@Param("start")int start,@Param("rows")int rows);
	
	//查找缴费记录总条数
	public int selectAllCount();
}
