package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HomeStopRecordModel;

/**
 * 供热报停管理模块
 * 
 * @author 罗妙忠
 *
 */
@Mapper
public interface IHomeStopRecordMapper {

	// 增加居民报停表
	public void insert(HomeStopRecordModel homeStopRecordModel);

	// 删除居民报停表
	public void delete(HomeStopRecordModel homeStopRecordModel);

	// 更新居民报停表
	public void update(HomeStopRecordModel homeStopRecordModel);

	// 根据Id查找居民报停记录
	public HomeStopRecordModel selectById(int recordno);

	// 查找所有居民退费记录
	public List<HomeStopRecordModel> selectAllByList();

	// 分页查找居民退费记录
	public List<HomeStopRecordModel> selectAllByListWithPages(@Param("start") int start, @Param("rows") int rows);
}
