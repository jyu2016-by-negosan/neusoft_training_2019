package com.neusoft.ht.fee.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HomeStopRecordModel;

/**
 * 供热报停管理模块
 * 
 * @author 罗妙忠
 *
 */
public interface IHomeStopRecordService {
	// 增加居民报停表
	public void add(HomeStopRecordModel homeStopRecordModel);

	// 删除居民报停表
	public void delete(HomeStopRecordModel homeStopRecordModel);

	// 更新居民报停表
	public void modify(HomeStopRecordModel homeStopRecordModel);

	// 根据Id查找居民报停记录
	public HomeStopRecordModel getById(int recordno);

	// 查找所有居民退费记录
	public List<HomeStopRecordModel> getAllByList();

	// 分页查找居民退费记录
	public List<HomeStopRecordModel> getAllByListWithPages(int rows, int page);
}
