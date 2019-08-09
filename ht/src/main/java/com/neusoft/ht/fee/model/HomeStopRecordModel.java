package com.neusoft.ht.fee.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**供热收费管理模块的住宅供热报停类
 * @author 黄宇德
 *
 */
@Alias("HomeStopRecord")
@Data
public class HomeStopRecordModel  {
 
    private int recordno = 0;
    private HomeFeeModel homeFeeModel = null;
    private float stoparea = 0;
    private Date stopdate =null;
    private String stopreason = null;
    private String stopperson = null;
    private String stopdesc = null;
    private String stopstuatus = null;
 
}
