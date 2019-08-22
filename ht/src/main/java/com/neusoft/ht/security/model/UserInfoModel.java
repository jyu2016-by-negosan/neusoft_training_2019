package com.neusoft.ht.security.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;
/**模块：系统安全模块
 * 系统管理员辅助信息类
 * @author 吴澍淼
 *
 */
@Alias("UserInfo")
@Data
public class UserInfoModel implements Serializable{
	
	private String uuserid=null;
	private String sex=null;
	private int age=0;
	private byte[] photo=null;
	private String photofilename=null;
	private String photocontenttype=null;
	private String tel=null;
	private String mobile=null;
	
	private SystemUserModel systemuser=null;
}
