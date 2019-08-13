package com.neusoft.ht.fee.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**供热收费管理模块的公建供热退费记录类
 * @author 黄宇德
 *
 */
@Alias("PublicHouseFeeReturnRecord")
@Data
public class PublicHouseFeeReturnRecordModel implements Serializable {
	
	private int recordno = 0;
	private PublicHouseFeeModel publicHouseFeeModel = null;	
	private PaymentTypeModel paymentTypeModel = null;
	private float amount = 0;
	private Date returndate = null;
	private String person = null;
	private String checkcode = null;
	private String invoicecode = null;
	private String recorddesc = null;
	private String recordestatus = null;
}
