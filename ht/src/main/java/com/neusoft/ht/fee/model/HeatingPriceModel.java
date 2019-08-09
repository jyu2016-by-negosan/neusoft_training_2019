package com.neusoft.ht.fee.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * 	供热收费管理模块的年度费率类
 * @author 黄宇德
 *
 */
@Alias("HeatingPrice")
@Data
public class HeatingPriceModel implements Serializable{
	  
    private String heatingyear = null;
	private float homeprice = 0;
	private float publichouseprice = 0;
	private String heatingmemo = null;
	private int heatingdays = 0;
	private byte[] pricefile = null;
	private String pricefilename = null;
	private String pricefilecontenttype = null; 
    
}
