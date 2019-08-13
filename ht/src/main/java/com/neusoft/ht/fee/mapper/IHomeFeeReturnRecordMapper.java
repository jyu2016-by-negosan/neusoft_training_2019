package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HomeFeeReturnRecordModel;

/**供热居民退费管理模块
 * @author 罗妙忠
 *
 */
@Mapper
public interface IHomeFeeReturnRecordMapper {
	
	//增加居民退费记录
	public void insert(HomeFeeReturnRecordModel homeFeeReturnRecordModel);
	
	//删除居民退费记录
	public void delete(HomeFeeReturnRecordModel homeFeeReturnRecordModel);
	
	//更新居民退费记录
	public void update(HomeFeeReturnRecordModel homeFeeReturnRecordModel);
	
	//根据Id查找居民退费记录
	public HomeFeeReturnRecordModel selectById(int recordno);
	
	//查找所有居民退费记录
	public List<HomeFeeReturnRecordModel> selectAllByList();
	
	//分页查找居民退费记录
	public List<HomeFeeReturnRecordModel> selectAllByListWithPages(@Param("start")int start,@Param("rows")int rows);
	
}
