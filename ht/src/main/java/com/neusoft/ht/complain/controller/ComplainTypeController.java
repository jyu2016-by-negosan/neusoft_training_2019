package com.neusoft.ht.complain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.complain.model.ComplainTypeModel;
import com.neusoft.ht.complain.service.IComplainTypeService;
import com.neusoft.ht.message.ResultMessage;

@RestController
@RequestMapping("/complaintype")
public class ComplainTypeController {

	@Autowired
	private IComplainTypeService complainTypeService;

	@RequestMapping("/list")
	public List<ComplainTypeModel> getComplainTypeList() throws Exception {
		return complainTypeService.getListByAll();
	}

	@RequestMapping("/add")
	public ResultMessage<ComplainTypeModel> addComplainType(ComplainTypeModel complainType) {
		if (complainType != null) {

			try {
				complainTypeService.add(complainType);
			} catch (Exception e) {
				return new ResultMessage<ComplainTypeModel>("ERROR", "增加住户投诉对象失败");
			}
			return new ResultMessage<ComplainTypeModel>("OK", "增加住户投诉对象成功");
		} else {
			return new ResultMessage<ComplainTypeModel>("ERROR", "增加住户投诉对象为空");
		}
	}

	@RequestMapping("/delete")
	public ResultMessage<ComplainTypeModel> deleteComplainType(ComplainTypeModel complainType) throws Exception {
		if (complainType != null) {

			try {
				complainTypeService.delete(complainType);
			} catch (Exception e) {
				return new ResultMessage<ComplainTypeModel>("ERROR", "删除住户投诉对象失败");
			}
			return new ResultMessage<ComplainTypeModel>("OK", "删除住户投诉对象成功");
		} else {
			return new ResultMessage<ComplainTypeModel>("ERROR", "删除的住户投诉对象为空");
		}
	}

	@RequestMapping("/modify")
	public ResultMessage<ComplainTypeModel> modifyComplainType(ComplainTypeModel complainType) throws Exception {
		if (complainType != null) {

			try {
				complainTypeService.modify(complainType);
			} catch (Exception e) {
				return new ResultMessage<ComplainTypeModel>("ERROR", "修改住户投诉对象失败");
			}
			return new ResultMessage<ComplainTypeModel>("OK", "修改住户投诉对象成功");
		} else {
			return new ResultMessage<ComplainTypeModel>("ERROR", "修改的住户投诉对象为空");
		}
	}

	@RequestMapping("/getBytypeNo")
	public ComplainTypeModel getComplainType() throws Exception {
		return complainTypeService.getComplainTypeBytypeNo(1);
	}

	@RequestMapping("/getCountByAll")
	public int getAllCount() throws Exception {
		return complainTypeService.getCountByAll();
	}
	
	@RequestMapping("/listwithpage")
	public List<ComplainTypeModel> getListWithPage(@RequestParam(required = false,defaultValue ="2")int rows,@RequestParam(required = false,defaultValue ="1")int pages) throws Exception{
		return complainTypeService.selectListByAllWithPage(rows, pages);
	} 
	
	

}
