package com.neusoft.ht.fee.mapper;

import java.util.List;

import com.neusoft.ht.fee.model.HomeFeeModel;

public interface IHomeFeeModelMapper {
	//C
	public void insert(HomeFeeModel homeFeeModel) throws Exception;
	//U
	public void update(HomeFeeModel homeFeeModel) throws Exception;
	//R
	public List<HomeFeeModel> selectListByAll() throws Exception;
	//D
	public void delete(HomeFeeModel homeFeeModel) throws Exception;
	
	//显示带小区名的居民供热记录（三表连接）
	public List<HomeFeeModel> selectListByAllWithHouseType() throws Exception;
	//按年份查找供热缴费记录
	public List<HomeFeeModel> selectListByAllWithHeatingYear() throws Exception;
	//生成欠费住宅列表
	public List<HomeFeeModel> selectListByAllWithDebtFee() throws Exception;
}
