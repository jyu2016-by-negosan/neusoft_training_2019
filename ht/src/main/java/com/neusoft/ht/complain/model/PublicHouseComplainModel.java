package com.neusoft.ht.complain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

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
public class PublicHouseComplainModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private int complainno;
	private PublicHouseModel house;
	private ComplainTypeModel compliantype;
	private String complaintitle;
	private String complaincontent;
	private String requestcontent;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date complaindate;
	private String contactperson;
	private String tel;
	private String mobile;
	private String mail;
	private String qq;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date servicestartdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date serviceenddate;
	private String servicecontext;
	private String serviceperson;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date feedbackdate;
	private String housecomment;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date assurancedate;
	private BigDecimal assurancefee;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date assurancepayfate;
	private String complainstatus;

}
