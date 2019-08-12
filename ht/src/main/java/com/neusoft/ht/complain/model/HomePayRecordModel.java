package com.neusoft.ht.complain.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;


/**模块：供热缴费管理
 * 住宅供热缴费记录--Model
 * @author 罗妙忠
 *
 */
@Alias("homePayRecord")
@Data
public class HomePayRecordModel {
	private int recordNo;
	private int feeNo;
	private int paymentTypeNo;
	private double payAmount;
	private Date payDate;
	private String payPerson;
	private String checkCode;
	private String invoiceCode;
	private String payDesc;
	private String recordStatus;
}
