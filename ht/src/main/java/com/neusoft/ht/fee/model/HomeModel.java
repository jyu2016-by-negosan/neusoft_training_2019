package com.neusoft.ht.fee.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * 
 *   供热收费管理模块的居民类
 * @author 黄宇德
 *
 */
@Alias("Home")
@Data
public class HomeModel implements Serializable{
  
   private int homeno = 0;
   private String heatingcode = null;
   private HouseTypeModel housetype = null;
   private NeighbourHoodModel neighbourhood = null;
   private String homename = null;
   private String buildingcode = null;
   private String departmentcode = null;
   private String floorcode = null;
   private String housecode = null;
   private float homearea = 0;
   private String direction = null;
   private String tel = null;
   private String mobile = null;
   private String mail = null;
   private String qq = null;
   private String heatingstatus = null;
   private float heatingarea = 0;
}
