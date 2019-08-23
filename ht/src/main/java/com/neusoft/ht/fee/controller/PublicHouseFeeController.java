package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.PublicHouseFeeModel;
import com.neusoft.ht.fee.service.IPublicHouseFeeService;
import com.neusoft.ht.message.ResultMessage;

/**
 * 模块：供热缴费管理
 * 公建供热记录管理的Controller类
 * 
 * @author 张晓龙
 * */
@RestController
@RequestMapping("/fee/publichousefee")
public class PublicHouseFeeController {

	@Autowired
	private IPublicHouseFeeService publicHouseFeeService = null;
	
	//增加
	@PostMapping("/add")
	public ResultMessage<PublicHouseFeeModel> add(PublicHouseFeeModel publicHouseFeeModel) throws Exception {
		if (publicHouseFeeModel != null) {
			try {
				publicHouseFeeService.add(publicHouseFeeModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<PublicHouseFeeModel>("ERROR", "添加失败！记录已存在！");
			}
		}
		else {
			return new ResultMessage<PublicHouseFeeModel>("ERROR", "添加失败！记录不能为空！");
		}
		return new ResultMessage<PublicHouseFeeModel>("OK", "添加公建供热记录成功！");
	}
	
	//修改
	@PostMapping("/modify")
	public ResultMessage<PublicHouseFeeModel> modify(PublicHouseFeeModel publicHouseFeeModel) throws Exception {
		if (publicHouseFeeModel != null) {
			try {
				publicHouseFeeService.modify(publicHouseFeeModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<PublicHouseFeeModel>("ERROR", "修改失败！记录已存在！");
			}
		}
		else {
			return new ResultMessage<PublicHouseFeeModel>("ERROR", "修改失败！记录不能为空！");
		}
		return new ResultMessage<PublicHouseFeeModel>("OK", "修改公建供热记录成功！");
	}
	
	//删除
	@PostMapping("/delete")
	public ResultMessage<PublicHouseFeeModel> delete(PublicHouseFeeModel publicHouseFeeModel) throws Exception {
		publicHouseFeeService.delete(publicHouseFeeModel);
		
		return new ResultMessage<PublicHouseFeeModel>("OK", "删除公建供热记录成功！");
	}
	
	//取得列表的所有对象，无分页
	@GetMapping("/getListByAll")
	public ResultMessage<PublicHouseFeeModel> getListByAll() throws Exception {
		List<PublicHouseFeeModel> list = null;
		try {
			list = publicHouseFeeService.getListByAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage<PublicHouseFeeModel>("ERROR", "查询失败！");
		}
		return new ResultMessage<PublicHouseFeeModel>(list, "OK", "查询成功！");
	}
	
	//按照缴费编号查找对象
	@GetMapping("/getByNo")
	public ResultMessage<PublicHouseFeeModel> getByNo(int feeno) throws Exception {
		PublicHouseFeeModel publicHouseFeeModel = null;
		try {
			publicHouseFeeModel = publicHouseFeeService.getByNo(feeno);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage<PublicHouseFeeModel>(publicHouseFeeModel, "ERROR", "查找失败！");
		}
		return new ResultMessage<PublicHouseFeeModel>(publicHouseFeeModel, "OK", "查找成功！");
	}
	
	//取得列表的所有对象，有分页
	@GetMapping("/getListByAllWithPage")
	public ResultMessage<PublicHouseFeeModel> getListByAllWithPage(@RequestParam(required = false, defaultValue = "4") int rows, @RequestParam(required = false, defaultValue = "1") int page) throws Exception {
		ResultMessage<PublicHouseFeeModel> resultMessage = new ResultMessage<PublicHouseFeeModel>("OK", "取得公建缴费信息分页模式成功");
		resultMessage.setCount(publicHouseFeeService.getCountByAll());
		resultMessage.setPageCount(publicHouseFeeService.getCountPageByAll(rows));
		resultMessage.setList(publicHouseFeeService.getListByAllWithPage(rows, page));
		resultMessage.setPage(page);
		resultMessage.setRows(rows);
		
		return resultMessage;
	}
	
	//取得对象总数
	@GetMapping("/getCountByAll")
	public ResultMessage<PublicHouseFeeModel> getCountByAll() throws Exception {
		int count = 0;
		try {
			count = publicHouseFeeService.getCountByAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage<PublicHouseFeeModel>(count, "ERROR", "查找失败！");
		}
		return new ResultMessage<PublicHouseFeeModel>(count, "OK", "查找成功！");
	}
}
