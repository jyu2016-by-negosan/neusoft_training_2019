package com.neusoft.ht.complain.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * 
 * 客户服务模块--投诉类型类
 * 
 * @author 万俊星
 *
 */
@Alias("ComplainType")
@Data
public class ComplainTypeModel {
	private Long typeno;
	private String typename;

}
