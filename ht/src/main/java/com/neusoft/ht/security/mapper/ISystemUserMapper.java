package com.neusoft.ht.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.security.model.SystemUserModel;

/**模块：系统安全模块
 * 操作员的Mapper接口
 * @author 吴澍淼
 *
 */
@Mapper
public interface ISystemUserMapper {
	//增加
	public void insert(SystemUserModel systemUserModel) throws Exception;
	//修改
	public void update(SystemUserModel systemUserModel) throws Exception;
	//删除
	public void delete(SystemUserModel systemUserModel) throws Exception;
	
	//查询-获取列表
	public List<SystemUserModel> selectListByAll() throws Exception;
	//查询-取得列表，有分页
	public List<SystemUserModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//查询-返回单个对象
	public SystemUserModel selectById(String uuserid) throws Exception;
	public SystemUserModel selectByIdWithoutOther(String uuserid) throws Exception;
	//取得对象的个数
	public int selectCountByAll() throws Exception;
	
}
