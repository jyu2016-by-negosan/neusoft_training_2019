package com.neusoft.ht.complain.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.neusoft.ht.fee.model.PublicHouseModel;

import lombok.Data;

/**
 * 
 * 客户服务模块--公建投诉类
 * 
 * @author 万俊星
 *
 */
@Alias("HouseComplain")
@Data
public class PublicHouseComplainModel {

	private int complainNo;
	private PublicHouseModel house;
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
	private String housecomment;
	private Date assurancedate;
	private BigDecimal assurancefee;
	private Date assurancepayfate;
	private String complainstatus;

}
