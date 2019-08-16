package com.neusoft.ht.message;

import java.io.Serializable;
import java.util.List;



import lombok.Data;
/**
 * 模块：公共基础工具类，
 * 公共的控制层结果返回类，推荐控制层方法增加，修改，删除，取得分页的信息都可以返回此对象
 * @Author: 黄宇德
 */
@Data
public class ResultMessage<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private T model=null; //业务对象
	private List<T> list=null; //业务对象的列表
	private String status=null;
	private String message=null;
	private int rows=0; //每屏显示的行数
	private int page=0; //第几页
	private int count=0; //信息T的个数
	private int pageCount=0; //页数
	
	public ResultMessage() {
		
	}
	
	public ResultMessage(String status,String message) {
		this.status=status;
		this.message=message;
	}
	

	public ResultMessage(List<T>list,String status,String message) {
		this.list = list;
		this.status=status;
		this.message=message;
	}
	
	public ResultMessage(T model,String status,String message) {
		this.model = model;
		this.status=status;
		this.message=message;
	}
	
	public ResultMessage(int count,String status,String message) {
		this.count = count;
		this.status=status;
		this.message=message;
	}
	
	public ResultMessage(List<T>list,int rows,int page,int count,int pageCount,String status,String message) {
		this.list = list;
		this.rows = rows;
		this.page = page;
		this.count = count;
		this.pageCount = pageCount;
		this.status = status;
		this.message = message;
	}

	public ResultMessage(int count,int pageCount,List<T> list,String status,String message) {
		this.pageCount = pageCount;
		this.list = list;
		this.count = count;
		this.status=status;
		this.message=message;
	}
	
	

}
