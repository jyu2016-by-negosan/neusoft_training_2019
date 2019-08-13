package com.neusoft.ht.fee.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**供热收费管理模块的住宅供热缴费记录类
 * @author 黄宇德 
 *
 */
@Alias("HomeFeePayRecord")
@Data

public class HomeFeePayRecordModel implements Serializable {

	private int recordno = 0;
	private HomeFeeModel homeFeeModel = null;
	private PaymentTypeModel paymentTypeModel = null;
	private float payamount = 0;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date paydate = null;
	private String payperson = null;
	private String checkcode = null;
	private String invoicecode = null;
	private String paydesc = null;
	private String recordstatus = null;
}
