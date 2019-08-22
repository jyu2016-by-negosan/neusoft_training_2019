package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HomeModel;
/**模块：供热缴费管理
 * 供热居民管理的Mapper接口
 * @author 黄宇德
 *
 */
public interface IHomeMapper {
	//C
	public void insert(HomeModel homeModel) throws Exception;
	//U
	public void update(HomeModel homeModel) throws Exception;
	//R
	public List<HomeModel> selectListByAll() throws Exception;
	//R2
	public HomeModel selectByNo(int homeno) throws Exception;
	//取关联小区和户型的特定居民
	public HomeModel selectByNoWithHoodNoAndHouseTypeNo(int homeno) throws Exception;
	//R3
	public List<HomeModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//D
	public void delete(HomeModel homeModel) throws Exception;
	//取得对象的个数
	public int selectCountByAll() throws Exception;
	//根据综合检索条件取得居民列表，取得关联的小区和户型，分页模式
	public List<HomeModel> selectListByConditionWithHoodNoAndHouseTypeNoWithPage(@Param("hoodno") int hoodno,@Param("housetypeno") int housetypeno,@Param("start") int start,@Param("rows") int rows) throws Exception;
	//修改供热状态
	public void ChangeHeatingStatus(int homeno,String heatingstatus) throws Exception;
}
