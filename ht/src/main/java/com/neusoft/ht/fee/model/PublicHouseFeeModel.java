package com.neusoft.ht.fee.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * 	供热收费管理模块的公建供热记录类
 * @author 黄宇德
 */
@Alias("PublicHouse")
@Data
public class PublicHouseFeeModel {

	private int feeno = 0;
	private int houseno = 0;
	private String heatingyear = null;
	private float heatarea = 0;
	private float actualfee = 0;
	private float debtfee = 0;
	private String feedesc = null;
	private String feestatus = null;
	private int heatingdays = 0;
}
