package com.neusoft.ht.fee.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**供热收费管理模块的公建供热退费记录类
 * @author 黄宇德
 *
 */
@Alias("PublicHouseStopRecord")
@Data
public class PublicHouseStopRecordModel implements Serializable {

	/**
	 * 
	 */
	
	private int recordno = 0;
    private PublicHouseFeeModel publicHouseFeeModel = null;
    private float stoparea = 0;
    private Date stopdate =null;
    private String stopreason = null;
    private String stopperson = null;
    private String stopdesc = null;
    private String stopstuatus = null;
	
}
