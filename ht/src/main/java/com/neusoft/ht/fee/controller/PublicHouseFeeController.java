package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
	private IPublicHouseFeeService publicHouseFeeService;
	
	@RequestMapping("/add")
	public ResultMessage<PublicHouseFeeModel> add(PublicHouseFeeModel publicHouseFeeModel) {
		if (publicHouseFeeModel != null) {
			try {
				publicHouseFeeService.add(publicHouseFeeModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<PublicHouseFeeModel>("Error", "添加失败！记录已存在！");
			}
		}
		else {
			return new ResultMessage<PublicHouseFeeModel>("Error", "添加失败！记录不能为空！");
		}
		return new ResultMessage<PublicHouseFeeModel>("OK", "添加公建供热记录成功！");
	}
	
	@RequestMapping("/modify")
	public ResultMessage<PublicHouseFeeModel> modify(PublicHouseFeeModel publicHouseFeeModel) {
		if (publicHouseFeeModel != null) {
			try {
				publicHouseFeeService.modify(publicHouseFeeModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<PublicHouseFeeModel>("Error", "修改失败！记录已存在！");
			}
		}
		else {
			return new ResultMessage<PublicHouseFeeModel>("Error", "修改失败！记录不能为空！");
		}
		return new ResultMessage<PublicHouseFeeModel>("OK", "修改公建供热记录成功！");
	}
	
	@RequestMapping("/delete")
	public ResultMessage<PublicHouseFeeModel> delete(PublicHouseFeeModel publicHouseFeeModel) {
		if (publicHouseFeeModel != null) {
			try {
				publicHouseFeeService.delete(publicHouseFeeModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<PublicHouseFeeModel>("Error", "删除失败！记录不存在！");
			}
		}
		else {
			return new ResultMessage<PublicHouseFeeModel>("Error", "添加失败！记录不能为空！");
		}
		return new ResultMessage<PublicHouseFeeModel>("OK", "添加公建供热记录成功！");
	}
	
	@RequestMapping("/getListByAll")
	public ResultMessage<PublicHouseFeeModel> getListByAll() {
		List<PublicHouseFeeModel> list = null;
		try {
			list = publicHouseFeeService.getListByAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage<PublicHouseFeeModel>("Error", "查询失败！");
		}
		return new ResultMessage<PublicHouseFeeModel>(list, "OK", "查询成功！");
	}
	
	@RequestMapping("/getByNo")
	public ResultMessage<PublicHouseFeeModel> getByNo(int houseno) {
		PublicHouseFeeModel publicHouseFeeModel = null;
		try {
			publicHouseFeeModel = publicHouseFeeService.getByNo(houseno);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage<PublicHouseFeeModel>(publicHouseFeeModel, "Error", "查找失败！");
		}
		return new ResultMessage<PublicHouseFeeModel>(publicHouseFeeModel, "OK", "查找成功！");
	}
	
	@RequestMapping("/getListByAllWithPage")
	public ResultMessage<PublicHouseFeeModel> getListByAllWithPage(int start, int rows) {
		List<PublicHouseFeeModel> list = null;
		try {
			list = publicHouseFeeService.getListByAllWithPage(start, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage<PublicHouseFeeModel>(list, "Error", "分页查找失败！");
		}
		return new ResultMessage<PublicHouseFeeModel>(list, "OK", "分页查找成功！");
	}
	
	@RequestMapping("/getCountByAll")
	public ResultMessage<PublicHouseFeeModel> getCountByAll() {
		int count = 0;
		try {
			count = publicHouseFeeService.getCountByAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMessage<PublicHouseFeeModel>(count, "Error", "查找失败！");
		}
		return new ResultMessage<PublicHouseFeeModel>(count, "OK", "查找成功！");
	}
}
