package com.neusoft.ht.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.ht.security.model.UserInfoModel;

/**模块：系统安全模块
 * 系统管理员辅助信息的Mapper接口
 * @author 吴澍淼
 *
 */
@Mapper
public interface IUserInfoMapper {
	//增加
	public void insert(UserInfoModel userInfoModel) throws Exception;
	//修改
	public void update(UserInfoModel userInfoModel) throws Exception;
	//删除
	public void delete(UserInfoModel userInfoModel) throws Exception;
	
	//查询-获取列表
	public List<UserInfoModel> selectListByAll() throws Exception;
	//查询-取得列表，有分页
	public List<UserInfoModel> selectListByAllWithPage(@Param("start") int start,@Param("rows") int rows) throws Exception;
	//查询-返回单个对象
	public UserInfoModel selectById(String uuserid) throws Exception;
	//取得对象的个数
	public int selectCountByAll() throws Exception;
	//检查系统管理员登陆账号是否已经存在
	public int selectCountById(String uuserid) throws Exception;
	
}
