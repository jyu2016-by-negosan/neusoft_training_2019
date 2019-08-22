package com.neusoft.ht.complain.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import lombok.Data;



/**
 * 
 * 客户服务模块--投诉类型类
 * 
 * @author 万俊星
 *
 */
@Alias("ComplainType")
@Component
@Data
public class ComplainTypeModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int typeno;
	private String typename;
}
