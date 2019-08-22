package com.neusoft.ht.security.model;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**模块：系统安全模块
 * 操作员类
 * @author 吴澍淼
 *
 */
@Alias("SystemUser")
@Data
public class SystemUserModel implements Serializable{

	private String uuserid=null;
	private String upassword=null;
	private String uname=null;
	
	//操作员关联的系统功能列表
	private List<SystemFunctionModel> systemfunctions=null;
	
	@Override
	public String toString() {
		return "SystemUser [uuserid=" + uuserid + ", upassword=" + upassword + ", uname=" +uname + "]";
	}
}
