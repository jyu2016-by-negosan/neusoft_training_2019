package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.NeighbourHoodModel;

/**模块：供热缴费管理
 * 小区管理的Mapper接口
 * @author 黄宇德
 *
 */
public interface INeighbourHoodMapper {
	//C
	public void insert(NeighbourHoodModel neighbourHoodModel) throws Exception;
	//U
	public void update(NeighbourHoodModel neighbourHoodModel) throws Exception;
	//R
	public List<NeighbourHoodModel> selectListByAll() throws Exception;
	//R2
	public NeighbourHoodModel selectByNo(int hoodno) throws Exception;
	//R3
	public List<NeighbourHoodModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//R4
	public int selectCountByAll() throws Exception;
	//D
	public void delete(NeighbourHoodModel neighbourHoodModel) throws Exception;

}
	