package com.neusoft.ht.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.security.model.SystemFunctionModel;

/**模块：系统安全模块
 * 系统功能的Mapper接口
 * @author 吴澍淼
 *
 */
@Mapper
public interface ISystemFunctionMapper {
	//增加
	public void insert(SystemFunctionModel systemFunctionModel) throws Exception;
	//修改
	public void update(SystemFunctionModel systemFunctionModel) throws Exception;
	//删除
	public void delete(SystemFunctionModel systemFunctionModel) throws Exception;
	
	//查询-获取列表
	public List<SystemFunctionModel> selectListByAll() throws Exception;
	//查询-取得列表，有分页
	public List<SystemFunctionModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//查询-返回单个对象
	public SystemFunctionModel selectByNo(int funno) throws Exception;
	//取得对象的个数
	public int selectCountByAll() throws Exception;
	//检查功能编号是否已经存在
	public int selectCountByNo(int funno) throws Exception;
	
}
