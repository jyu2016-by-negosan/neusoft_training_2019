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
	public void add(HomeStopRecordModel homeStopRecordModel) throws Exception;

	// 删除居民报停表
	public void delete(HomeStopRecordModel homeStopRecordModel)throws Exception;

	// 更新居民报停表
	public void modify(HomeStopRecordModel homeStopRecordModel)throws Exception;

	// 根据Id查找居民报停记录
	public HomeStopRecordModel getById(int recordno)throws Exception;
	
	//查找关联HomeFee
	public HomeStopRecordModel getByIdWithHomeFee(int recordno);
	
	//查找停供记录总条数
	public int getAllCount() throws Exception;

	// 查找所有居民退费记录
	public List<HomeStopRecordModel> getAllByList()throws Exception;

	// 分页查找居民退费记录
	public List<HomeStopRecordModel> getAllByListWithPages(int rows, int page)throws Exception;
}
