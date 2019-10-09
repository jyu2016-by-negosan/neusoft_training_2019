package com.neusoft.ht.fee.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * 	供热收费管理模块的公建供热缴费记录类
 * @author 黄宇德
 *
 */
@Alias("PublicHouseFeePayRecord")
@Data
public class PublicHouseFeePayRecordModel implements Serializable {

	private int recordno = 0;
	private PublicHouseFeeModel publicHouseFeeModel = null;
	private PaymentTypeModel paymentTypeModel = null;
	private float payamount = 0;
	private String paydate = null;
	private String payperson = null;
	private String checkcode = null;
	private String invoicecode = null;
	private String paydesc = null;
	private String recordstatus = null;
}
