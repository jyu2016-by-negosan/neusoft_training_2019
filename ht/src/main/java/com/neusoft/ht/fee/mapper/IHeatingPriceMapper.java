package com.neusoft.ht.fee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.fee.model.HeatingPriceModel;
/**模块：供热缴费管理
 * 年份供热价格管理的Mapper接口
 * @author 黄宇德
 *
 */
public interface IHeatingPriceMapper {
	//C
	public void insert(HeatingPriceModel heatingPriceModel) throws Exception;
	//U
	public void update(HeatingPriceModel heatingPriceModel) throws Exception;
	//R
	public List<HeatingPriceModel> selectListByAllWithPriceAndDay() throws Exception;
	//R2
	public HeatingPriceModel selectByYear(String heatingyear) throws Exception;
	//R3
	public List<HeatingPriceModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//D
	public void delete(HeatingPriceModel heatingPriceModel) throws Exception;
	//取得对象的个数
	public int selectCountByAll() throws Exception;
	//检查年份是否已经存在
	public int selectCountByYear(String heatingyear) throws Exception;
	//修改实际供热天数
	public void ChangeHeatingdays(String heatingyear,int heatingdays);
}
