package com.neusoft.ht.security.model;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
/**模块：系统安全模块
  * 系统功能类
 * @author 吴澍淼
 *
 */
@Alias("SystemFunction")
@Data
public class SystemFunctionModel implements Serializable{

	private int funno=0;
	private String funname=null;
	private String funurl=null;
	private int levelno=0;
	
	//系统功能关联的操作员列表
	private List<SystemUserModel> systemusers=null;
}
