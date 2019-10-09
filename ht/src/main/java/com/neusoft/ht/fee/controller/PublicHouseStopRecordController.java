package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.PublicHouseStopRecordModel;
import com.neusoft.ht.fee.service.IPublicHouseStopRecordService;
import com.neusoft.ht.message.ResultMessage;

@RestController
@RequestMapping("/fee/publichousestoprecord")
public class PublicHouseStopRecordController {

	@Autowired
	private IPublicHouseStopRecordService publicHouseStopRecordService = null;
	
	@PostMapping("/add")
	public ResultMessage<PublicHouseStopRecordModel> add(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception {
		if (publicHouseStopRecordModel != null) {
			publicHouseStopRecordService.add(publicHouseStopRecordModel);
		}
		else {
			return new ResultMessage<PublicHouseStopRecordModel>("ERROR", "添加失败！");
		}
		return new ResultMessage<PublicHouseStopRecordModel>("OK", "添加成功！");
	}
	
	@PostMapping("/modify")
	public ResultMessage<PublicHouseStopRecordModel> modify(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception {
		if (publicHouseStopRecordModel != null) {
			publicHouseStopRecordService.modify(publicHouseStopRecordModel);
		}
		else {
			return new ResultMessage<PublicHouseStopRecordModel>("ERROR", "修改失败！");
		}
		return new ResultMessage<PublicHouseStopRecordModel>("OK", "修改成功！");
	}
	
	@PostMapping("/delete")
	public ResultMessage<PublicHouseStopRecordModel> delete(PublicHouseStopRecordModel publicHouseStopRecordModel) throws Exception {
		publicHouseStopRecordService.delete(publicHouseStopRecordModel);
		
		return new ResultMessage<PublicHouseStopRecordModel>("OK", "删除成功！");
	}
	
	@GetMapping("/getLisytByAll")
	public ResultMessage<PublicHouseStopRecordModel> getListByAll() throws Exception {
		List<PublicHouseStopRecordModel> list = null;
		list = publicHouseStopRecordService.getListByAll();
		
		return new ResultMessage<PublicHouseStopRecordModel>(list, "OK", "查找成功！");
	}
	
	@GetMapping("/getByNo")
	public ResultMessage<PublicHouseStopRecordModel> getByNo(int recordno) throws Exception {
		PublicHouseStopRecordModel publicHouseStopRecordModel = null;
		publicHouseStopRecordModel = publicHouseStopRecordService.getByNo(recordno);
		
		return new ResultMessage<PublicHouseStopRecordModel>(publicHouseStopRecordModel, "OK", "查找成功！");
	}
	
	@GetMapping("/getCountByAll")
	public ResultMessage<PublicHouseStopRecordModel> getCountByAll() throws Exception {
		int count = 0;
		count = publicHouseStopRecordService.getCountByAll();
		
		return new ResultMessage<PublicHouseStopRecordModel>(count, "OK", "查找成功！");
	}
}
