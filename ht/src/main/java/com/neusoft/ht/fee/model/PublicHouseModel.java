package com.neusoft.ht.fee.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * 
 *   供热收费管理模块的公建类
 * @author 黄宇德
 *
 */
@Alias("PublicHouse")
@Data
public class PublicHouseModel implements Serializable {
  
	  private int houseno = 0;
	  private NeighbourHoodModel neighbourHoodModel = null;
	  private String heatingecode = null;
	  private String housename = null;
	  private String building = null;
	  private float housearea = 0;
	  private float housecode = 0;
	  private String address = null;  
	  private String postcode = null;
	  private String contactname = null;
	  private String tel = null;
	  private String mobile = null;
	  private String mail = null;
	  private String qq = null;
	  private String heatingstatus = null;
	  private String departmentcode = null;
	  private String floorcode = null;
	  private float heatingarea = 0;

}
