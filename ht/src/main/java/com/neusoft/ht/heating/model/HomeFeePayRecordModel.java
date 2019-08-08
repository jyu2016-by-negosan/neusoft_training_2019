package com.neusoft.ht.heating.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**供热收费管理模块的住宅供热缴费记录类
 * @author 黄宇德 
 *
 */
@Alias("HomeFeePayRecord")
@Data
public class HomeFeePayRecordModel {

	private int recordno = 0;
	private HomeFeeModel homeFeeModel = null;
	private PaymentTypeModel paymentTypeModel = null;
	private float payamount = 0;
	private Date paydate = null;
	private String payperson = null;
	private String checkcode = null;
	private String invoicecode = null;
	private String paydesc = null;
	private String recordestatus = null;
}
