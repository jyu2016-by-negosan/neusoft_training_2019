package com.neusoft.ht.fee.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**供热收费管理模块的住宅供热退费记录类
 * @author 黄宇德
 *
 */
@Alias("HomeFeeReturnRecord")
@Data
public class HomeFeeReturnRecordModel implements Serializable {
	
	private int recordno = 0;
	private HomeFeeModel homeFeeModel = null;	
	private PaymentTypeModel paymentTypeModel = null;
	private float amount = 0;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returndate = null;
	private String person = null;
	private String checkcode = null;
	private String invoicecode = null;
	private String recorddesc = null;
	private String recordstatus = null;
	
}
