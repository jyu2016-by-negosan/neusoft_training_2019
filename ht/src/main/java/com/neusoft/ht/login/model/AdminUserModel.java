package com.neusoft.ht.login.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * 模块：登陆处理
 * 系统操作员值类
 * 
 * @author 张晓龙
 * */

@Alias("AdminUser")
@Data
public class AdminUserModel implements Serializable {

	private String uUserId = null;
	private String uPassword = null;
	private String uName = null;
}
