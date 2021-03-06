package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.PublicHouseFeePayRecordModel;
import com.neusoft.ht.fee.service.IPublicHouseFeePayRecordService;
import com.neusoft.ht.message.ResultMessage;

@RestController
@RequestMapping("/fee/publichousefeepayrecord")
public class PublicHouseFeePayRecordController {

	@Autowired
	private IPublicHouseFeePayRecordService publicHouseFeePayRecordService = null;
	
	@PostMapping("/add")
	public ResultMessage<PublicHouseFeePayRecordModel> add(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception {
		if (publicHouseFeePayRecordModel != null) {
			publicHouseFeePayRecordService.add(publicHouseFeePayRecordModel);
		}
		else {
			return new ResultMessage<PublicHouseFeePayRecordModel>("ERROR", "添加失败！记录不能为空！");
		}
		return new ResultMessage<PublicHouseFeePayRecordModel>("OK", "添加成功！");
	}
	
	@PostMapping("/modify")
	public ResultMessage<PublicHouseFeePayRecordModel> modify(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception {
		if (publicHouseFeePayRecordModel != null) {
			publicHouseFeePayRecordService.modify(publicHouseFeePayRecordModel);
		}
		else {
			return new ResultMessage<PublicHouseFeePayRecordModel>("ERROR", "修改失败！记录不能为空！");
		}
		return new ResultMessage<PublicHouseFeePayRecordModel>("OK", "修改成功！");
	}
	
	@PostMapping("/delete")
	public ResultMessage<PublicHouseFeePayRecordModel> delete(PublicHouseFeePayRecordModel publicHouseFeePayRecordModel) throws Exception {
		publicHouseFeePayRecordService.delete(publicHouseFeePayRecordModel);
		
		return new ResultMessage<PublicHouseFeePayRecordModel>("OK", "删除成功！");
	}
	
	@GetMapping("/getListByAll")
	public ResultMessage<PublicHouseFeePayRecordModel> getListByAll() throws Exception {
		List<PublicHouseFeePayRecordModel> list = null;
		
		list = publicHouseFeePayRecordService.getListByAll();
		
		return new ResultMessage<PublicHouseFeePayRecordModel>(list, "OK", "查找成功！");
	}
	
	@GetMapping("/getByNo")
	public ResultMessage<PublicHouseFeePayRecordModel> getByNo(int recordno) throws Exception {
		PublicHouseFeePayRecordModel publicHouseFeePayRecordModel = null;
		
		publicHouseFeePayRecordModel = publicHouseFeePayRecordService.getByNo(recordno);
		
		return new ResultMessage<PublicHouseFeePayRecordModel>(publicHouseFeePayRecordModel, "OK", "查找成功！");
	}
	
	@GetMapping("/getListByAllWithPage")
	public ResultMessage<PublicHouseFeePayRecordModel> getListByAllWithPage(@RequestParam(required = false, defaultValue = "4") int rows, @RequestParam(required = false, defaultValue = "1") int page) throws Exception {
		ResultMessage<PublicHouseFeePayRecordModel> resultMessage = new ResultMessage<PublicHouseFeePayRecordModel>("OK", "取得公建缴费信息分页模式成功");
		resultMessage.setCount(publicHouseFeePayRecordService.getCountByAll());
		resultMessage.setPageCount(publicHouseFeePayRecordService.getCountPageByAll(rows));
		resultMessage.setList(publicHouseFeePayRecordService.getListByAllWithPage(rows, page));
		resultMessage.setPage(page);
		resultMessage.setRows(rows);
		
		return resultMessage;
	}
	
	@GetMapping("/getCountByAll")
	public ResultMessage<PublicHouseFeePayRecordModel> getCountByAll() throws Exception {
		int count = 0;
		count = publicHouseFeePayRecordService.getCountByAll();
		
		return new ResultMessage<PublicHouseFeePayRecordModel>(count, "OK", "查找成功！");
	}
}
