package com.neusoft.ht.login.model;

import lombok.Data;

/**
 * 模块：登陆处理
 * 
 * 系统操作员值类
 * 
 * @author 张晓龙
 * */

@Data
public class AdminUserModel {

	private String uUserId = null;
	private String uPassword = null;
	private String uName = null;
}
