package com.neusoft.ht.fee.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**供热收费管理模块的住宅供热报停类
 * @author 黄宇德
 *
 */
@Alias("HomeStopRecord")
@Data
public class HomeStopRecordModel implements Serializable{
 
    private int recordno = 0;
    private HomeFeeModel homeFeeModel = null;
    private float stoparea = 0;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date stopdate =null;
    private String stopreason = null;
    private String stopperson = null;
    private String stopdesc = null;
    private String stopstatus = null;
 
}
