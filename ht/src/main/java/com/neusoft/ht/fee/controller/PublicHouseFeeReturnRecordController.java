package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.PublicHouseFeeReturnRecordModel;
import com.neusoft.ht.fee.service.IPublicHouseFeeReturnRecordService;
import com.neusoft.ht.message.ResultMessage;

@RestController
@RequestMapping("/fee/publichousefeereturnrecord")
public class PublicHouseFeeReturnRecordController {

	@Autowired
	private IPublicHouseFeeReturnRecordService publicHouseFeeReturnRecordService = null;
	
	@PostMapping("/add")
	public ResultMessage<PublicHouseFeeReturnRecordModel> add(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception {
		if (publicHouseFeeReturnRecordModel != null) {
			publicHouseFeeReturnRecordService.add(publicHouseFeeReturnRecordModel);
		}
		else {
			return new ResultMessage<PublicHouseFeeReturnRecordModel>("ERROR", "添加失败！");
		}
		return new ResultMessage<PublicHouseFeeReturnRecordModel>("OK", "添加成功！");
	}
	
	@PostMapping("/modify")
	public ResultMessage<PublicHouseFeeReturnRecordModel> modify(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception {
		if (publicHouseFeeReturnRecordModel != null) {
			publicHouseFeeReturnRecordService.modify(publicHouseFeeReturnRecordModel);
		}
		else {
			return new ResultMessage<PublicHouseFeeReturnRecordModel>("ERROR", "添加失败！");
		}
		return new ResultMessage<PublicHouseFeeReturnRecordModel>("OK", "添加成功");
	}
	
	@PostMapping("/delete")
	public ResultMessage<PublicHouseFeeReturnRecordModel> delete(PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel) throws Exception {
		publicHouseFeeReturnRecordService.delete(publicHouseFeeReturnRecordModel);
		return new ResultMessage<PublicHouseFeeReturnRecordModel>("OK", "删除成功！");
	}
	
	@GetMapping("/getListByAll")
	public ResultMessage<PublicHouseFeeReturnRecordModel> getListByAll() throws Exception {
		List<PublicHouseFeeReturnRecordModel> list = null;
		
		list = publicHouseFeeReturnRecordService.getListByAll();
		
		return new ResultMessage<PublicHouseFeeReturnRecordModel>(list, "OK", "查询成功！");
	}
	
	@GetMapping("/getCountByAll")
	public ResultMessage<PublicHouseFeeReturnRecordModel> getCountByAll() throws Exception {
		int count = 0;
		
		count = publicHouseFeeReturnRecordService.getCountByAll();
		
		return new ResultMessage<PublicHouseFeeReturnRecordModel>(count, "OK", "查找成功！");
	}
	
	@GetMapping("/getByNo")
	public ResultMessage<PublicHouseFeeReturnRecordModel> getByNo(int recordno) throws Exception {
		PublicHouseFeeReturnRecordModel publicHouseFeeReturnRecordModel = null;
		
		publicHouseFeeReturnRecordModel = publicHouseFeeReturnRecordService.getByNo(recordno);
		
		return new ResultMessage<PublicHouseFeeReturnRecordModel>(publicHouseFeeReturnRecordModel, "OK", "查找成功！");
	}
	
	@GetMapping("/getListByAllWithPage")
	public ResultMessage<PublicHouseFeeReturnRecordModel> getLIstByAllWithPage(int rows, int page) throws Exception {
		int  count = 0;
		int pageCount = 0;
		List<PublicHouseFeeReturnRecordModel> list = null;
		
		count = publicHouseFeeReturnRecordService.getCountByAll();

		pageCount = count % rows == 0 ? (count / rows) : (count / rows) + 1;

		list = publicHouseFeeReturnRecordService.getListByAllWithPage(rows, page);
		return new ResultMessage<PublicHouseFeeReturnRecordModel>(count, pageCount, list, "OK", "查找成功！");
	}
}
