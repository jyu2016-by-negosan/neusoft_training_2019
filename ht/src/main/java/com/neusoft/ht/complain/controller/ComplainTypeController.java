package com.neusoft.ht.complain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.complain.model.ComplainTypeModel;
import com.neusoft.ht.complain.service.IComplainTypeService;
import com.neusoft.ht.message.ResultMessage;

@RestController
@RequestMapping("complain/complaintype")
public class ComplainTypeController {

	@Autowired
	private IComplainTypeService complainTypeService;

	@PostMapping("/add")
	public ResultMessage<ComplainTypeModel> addComplainType(ComplainTypeModel complainType) throws Exception {
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

	@PostMapping("/delete")
	public ResultMessage<ComplainTypeModel> deleteComplainType(ComplainTypeModel complainType) throws Exception {
		if (complainType != null) {

			try {
				complainTypeService.delete(complainType);
				System.out.println("jkl");
			} catch (Exception e) {
				return new ResultMessage<ComplainTypeModel>("ERROR", "删除住户投诉对象失败");
			}
			return new ResultMessage<ComplainTypeModel>("OK", "删除住户投诉对象成功");
		} else {
			return new ResultMessage<ComplainTypeModel>("ERROR", "删除的住户投诉对象为空");
		}
	}

	@PostMapping("/modify")
	public ResultMessage<ComplainTypeModel> modifyComplainType(ComplainTypeModel complainType) throws Exception {
		if (complainType != null) {

			try {
				complainTypeService.modify(complainType);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<ComplainTypeModel>("ERROR", "修改住户投诉对象失败");
			}
			return new ResultMessage<ComplainTypeModel>("OK", "修改住户投诉对象成功");
		} else {
			return new ResultMessage<ComplainTypeModel>("ERROR", "修改的住户投诉对象为空");
		}
	}

	@GetMapping("/list/all")
	public List<ComplainTypeModel> getComplainTypeList() throws Exception {
		return complainTypeService.getListByAll();
	}
	
	@GetMapping("/get")
	public ComplainTypeModel getComplainType(@RequestParam(required = true) int typeno) throws Exception {
		return complainTypeService.getComplainTypeBytypeNo(typeno);
	}

	@GetMapping("/list/all/page")
	public ResultMessage<ComplainTypeModel> getListWithPage(@RequestParam(required = false, defaultValue = "2") int rows,
			@RequestParam(required = false, defaultValue = "1") int pages) throws Exception {
		
		int pageCount = complainTypeService.getPageCountByAll(rows);
		int count = complainTypeService.getCountByAll();
		List<ComplainTypeModel> list = complainTypeService.getListByAllWithPage(rows, pages);
		return new ResultMessage<ComplainTypeModel>(list,rows,pages,count,pageCount,"OK","查询成功");
	}

}
