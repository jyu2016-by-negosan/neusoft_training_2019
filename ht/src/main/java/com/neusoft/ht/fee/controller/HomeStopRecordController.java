package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.HomeStopRecordModel;
import com.neusoft.ht.fee.service.IHomeStopRecordService;
import com.neusoft.ht.message.ResultMessage;
/**
 * 模块：供热居民停供管理
 * @author 罗妙忠
 *
 */
@RestController
@RequestMapping("/fee/homeStopRecord")
public class HomeStopRecordController {
	@Autowired
	private IHomeStopRecordService homeStopRecordService;

	@RequestMapping("/add")
	public ResultMessage<HomeStopRecordModel> addHomeStopRecord(
			HomeStopRecordModel homeStopRecordModel) {
		if (homeStopRecordModel != null) {
			try {
				homeStopRecordService.add(homeStopRecordModel);
			} catch (Exception e) {
				return new ResultMessage<HomeStopRecordModel>("ERROR", "添加居民报停记录失败！");
			}
		} else {
			return new ResultMessage<HomeStopRecordModel>("ERROR", "居民报停记录不能为空！");
		}
		return new ResultMessage<HomeStopRecordModel>("OK", "添加居民报停记录成功！");
	}

	@RequestMapping("/modify")
	public ResultMessage<HomeStopRecordModel> modifyHomeStopRecord(
			HomeStopRecordModel homeStopRecordModel) {
		if (homeStopRecordModel != null) {
			try {
				homeStopRecordService.modify(homeStopRecordModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<HomeStopRecordModel>("ERROR", "修改居民报停记录失败！");
			}
		} else {
			return new ResultMessage<HomeStopRecordModel>("ERROR", "居民报停记录不能为空！");
		}
		return new ResultMessage<HomeStopRecordModel>("OK", "修改居民报停记录成功！");
	}

	@RequestMapping("/delete")
	public ResultMessage<HomeStopRecordModel> deleteHomeStopRecord(
			HomeStopRecordModel homeStopRecordModel) {
		if (homeStopRecordModel != null) {
			try {
				homeStopRecordService.delete(homeStopRecordModel);
			} catch (Exception e) {
				return new ResultMessage<HomeStopRecordModel>("ERROR", "删除居民报停记录失败！");
			}
		} else {
			return new ResultMessage<HomeStopRecordModel>("ERROR", "居民报停记录不能为空！");
		}
		return new ResultMessage<HomeStopRecordModel>("OK", "删除居民报停记录成功！");
	} 

	@RequestMapping("/getById")
	public ResultMessage<HomeStopRecordModel> getHomeFeeReturnRecordById(
			@RequestParam(defaultValue = "-1", required = false) int recordno) {
		HomeStopRecordModel homeStopRecordModel = null;
		if (recordno == -1) {
			return new ResultMessage<HomeStopRecordModel>("ERROR", "查找Id不能为空");
		}
		try {
			homeStopRecordModel=homeStopRecordService.getById(recordno);
		} catch (Exception e) {
			
			return new ResultMessage<HomeStopRecordModel>("ERROR", "查找居民报停记录失败！");
		}

		return new ResultMessage<HomeStopRecordModel>(homeStopRecordModel,"OK", "查找居民报停记录成功！");
	}

	@RequestMapping("/getAllByList")
	public ResultMessage<HomeStopRecordModel> getAllHomeFeeReturnRecordByList() {
		List<HomeStopRecordModel> list = null;
		try {
			list = homeStopRecordService.getAllByList();
		} catch (Exception e) {
			return new ResultMessage<HomeStopRecordModel>("ERROR", "查找居民报停记录失败！");
		}

		return new ResultMessage<HomeStopRecordModel>(list, "OK", "查找居民报停记录成功！");
	}

	@RequestMapping("/getAllByListWithPages")
	public ResultMessage<HomeStopRecordModel> getAllHomeFeeReturnRecordByListWithPages(
			@RequestParam(defaultValue = "-1", required = false) int rows,
			@RequestParam(defaultValue = "-1", required = false) int page) {
		List<HomeStopRecordModel> list = null;
		if(rows==-1 || page==-1) {
			return new ResultMessage<HomeStopRecordModel>("ERROR", "分页参数不能为空！");
		}
		try {
			list = homeStopRecordService.getAllByListWithPages(rows, page);
		} catch (Exception e) {
			return new ResultMessage<HomeStopRecordModel>("ERROR", "查找居民报停记录失败！");
		}

		return new ResultMessage<HomeStopRecordModel>(list, "OK", "查找居民报停记录成功！");
	}
}
