package com.neusoft.ht.fee.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * 供热收费管理模块的居民供热记录类
 * @author黄宇德 
 *
 */
@Alias("HomeFee")
@Data
public class HomeFeeModel implements Serializable {
	
	private int feeno = 0;
	private HomeModel home = null;
	private HeatingPriceModel heatingprice = null;
	private float agreefee = 0;
	private float actualfee = 0;
	private float debtfee = 0;
	private String feedesc = null;
	private String feestatus = null;

    
    
}
