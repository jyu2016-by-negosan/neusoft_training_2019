package com.neusoft.ht.complain.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;



/**
 * 
 * 客户服务模块--投诉类型类
 * 
 * @author 万俊星
 *
 */
@Alias("ComplainType")
@Component
public class ComplainTypeModel implements Serializable {
	public int getTypeno() {
		return typeno;
	}
	public void setTypeno(int typeno) {
		this.typeno = typeno;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	private static final long serialVersionUID = 1L;
	private int typeno;
	private String typename;
}
