package com.neusoft.ht.fee.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HomeFeeReturnRecordModel;

/**
 * 供热居民退费管理模块
 * 
 * @author 罗妙忠
 *
 */
public interface IHomeFeeReturnRecordService {
	// 增加居民退费记录
	public void add(HomeFeeReturnRecordModel homeFeeReturnRecordModel) throws Exception;

	// 删除居民退费记录
	public void delete(HomeFeeReturnRecordModel homeFeeReturnRecordModel) throws Exception;

	// 更新居民退费记录
	public void modify(HomeFeeReturnRecordModel homeFeeReturnRecordModel)throws Exception;

	// 根据Id查找居民退费记录
	public HomeFeeReturnRecordModel getById(int recordno)throws Exception;

	// 查找所有居民退费记录
	public List<HomeFeeReturnRecordModel> getAllByList()throws Exception;

	// 分页查找居民退费记录
	public List<HomeFeeReturnRecordModel> getAllByListWithPages(int rows, int page)throws Exception;
}
