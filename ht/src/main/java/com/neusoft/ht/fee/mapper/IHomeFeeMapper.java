package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HomeFeeModel;

/**
 * 模块：供热缴费管理
 * 住宅供热记录管理的Mapper接口
 * @author 黄宇德
 *
 */
public interface IHomeFeeMapper {
	//C
	public void insert(HomeFeeModel homeFeeModel) throws Exception;
	//U
	public void update(HomeFeeModel homeFeeModel) throws Exception;
	//R
	public List<HomeFeeModel> selectListByAll() throws Exception;
	//D
	public void delete(HomeFeeModel homeFeeModel) throws Exception;
	//取得住宅供热信息记录的个数
	public int selectCountByAll() throws Exception;
	//取得住宅供热信息记录的个数,带关联
	public int selectCountByAllWithHomeAndHeatingPriceWithPage(
			@Param("hoodno") int hoodno,@Param("heatingyear") String heatingyear,@Param("feestatus") String feestatus) throws Exception;
	//取得特定居民供热记录，取关联的居民表和年度供热价格表
	public HomeFeeModel selectByNoWithHomeAndHeatingPrice(int feeno) throws Exception;
	//根据综合检索条件取得居民供热记录列表，取得关联的居民表，取关联的年度供热价格表，分页模式
	public List<HomeFeeModel> selectListByConditionWithHomeAndHeatingPriceWithPage(
			@Param("hoodno") int hoodno,@Param("heatingyear") String heatingyear,@Param("feestatus") String feestautus,
			@Param("start") int start,@Param("rows") int rows) throws Exception;

}
