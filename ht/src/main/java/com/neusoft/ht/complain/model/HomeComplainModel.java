package com.neusoft.ht.complain.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.neusoft.ht.fee.model.HomeModel;

import lombok.Data;

/**
 * 
 * 客户服务模块--居民投诉类
 * 
 * @author 万俊星
 *
 */
@Alias("HomeComplain")
@Data
public class HomeComplainModel {

	private int complainno;
	private HomeModel home;
	private ComplainTypeModel compliantype;
	private String complaintitle;
	private String complaincontent;
	private String requestcontent;
	private Date complaindate;
	private String contactperson;
	private String tel;
	private String mobile;
	private String mail;
	private String qq;
	private Date servicestartdate;
	private Date serviceenddate;
	private String servicecontext;
	private String serviceperson;
	private Date feedbackdate;
	private String homecomment;
	private Date assurancedate;
	private BigDecimal assurancefee;
	private Date assurancepaydate;
	private String complainstatus;
}
