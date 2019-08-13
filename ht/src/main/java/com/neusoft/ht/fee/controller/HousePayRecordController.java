package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.HomeFeePayRecordModel;
import com.neusoft.ht.fee.service.IHomePayRecordService;
import com.neusoft.ht.message.ResultMessage;

/**
 * 模块：居民供热缴费记录--controller
 * 
 * @author 罗妙忠
 *
 */
@RestController
@RequestMapping("/fee/housepayrecord")


public class HousePayRecordController {

	@Autowired
	private IHomePayRecordService housePayRecordService;

	// 增加住宅缴费记录
	@RequestMapping("/addPayRecord")
	public ResultMessage<HomeFeePayRecordModel> addPayRecord( HomeFeePayRecordModel payRecordModel) {
		if (payRecordModel != null) {
			try {
				housePayRecordService.add(payRecordModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<HomeFeePayRecordModel>("ERROR", "添加住宅缴费记录失败！");
			}
		} else {
			return new ResultMessage< HomeFeePayRecordModel>("ERROR", "住宅缴费记录不能为空！");
		}
		return new ResultMessage< HomeFeePayRecordModel>("OK", "住宅缴费记录添加成功");

	}

	// 删除住宅缴费记录
	@RequestMapping("/deletePayRecord")
	public ResultMessage<HomeFeePayRecordModel> deletePayRecord( HomeFeePayRecordModel payRecordModel) {
		if (payRecordModel != null) {
			try {
				housePayRecordService.delete(payRecordModel);
			} catch (Exception e) {
				return new ResultMessage<HomeFeePayRecordModel>("ERROR", "删除住宅缴费记录失败！");
			}
		} else {
			return new ResultMessage<HomeFeePayRecordModel>("ERROR", "住宅缴费记录不能为空！");
		}
		return new ResultMessage<HomeFeePayRecordModel>("OK", "住宅缴费记录删除成功");
	}

	// 更新住宅缴费记录
	@RequestMapping("/modifyPayRecord")
	public ResultMessage<HomeFeePayRecordModel> modifyPayRecord(HomeFeePayRecordModel payRecordModel) {
		if (payRecordModel != null) {
			try {
				housePayRecordService.modify(payRecordModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<HomeFeePayRecordModel>("ERROR", "更新住宅缴费记录失败！");
			}
		} else {
			return new ResultMessage<HomeFeePayRecordModel>("ERROR", "住宅缴费记录不能为空！");
		}
		return new ResultMessage<HomeFeePayRecordModel>("OK", "住宅缴费记录更新成功");
	}

	// 根据住宅Id查找缴费记录
	@RequestMapping("/getPayRecordById")
	public ResultMessage<HomeFeePayRecordModel> GetPayRecord(int recordno) {
		List<HomeFeePayRecordModel> list = null;
		try {
			list = housePayRecordService.getById(recordno);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage<HomeFeePayRecordModel>("ERROR", "查找住宅缴费记录失败！");
		}
		
		return new ResultMessage<HomeFeePayRecordModel>(list,"OK", "住宅缴费记录查找成功");
	}

	// 查找所有住宅缴费记录
	@RequestMapping("/getAllListPayRecordBy")
	public ResultMessage<HomeFeePayRecordModel> GetAllListPayRecord() {
		List<HomeFeePayRecordModel> list = null;
		try {
			list = housePayRecordService.getByAllList();
		} catch (Exception e) {
			return new ResultMessage<HomeFeePayRecordModel>("ERROR", "查找住宅缴费记录失败！");
		}
		return new ResultMessage<HomeFeePayRecordModel>(list,"OK", "住宅缴费记录查找成功");
	}
	
	

}
