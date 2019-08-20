package com.neusoft.ht.complain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.complain.model.HomeComplainModel;
import com.neusoft.ht.complain.service.IHomeComplainService;
import com.neusoft.ht.message.ResultMessage;

@RestController
@RequestMapping("complain/homecomplain")
public class HomeComplainController {

	@Autowired
	private IHomeComplainService homecomplainService;

	@GetMapping("/list/all")
	public List<HomeComplainModel> getComplainTypeList() throws Exception {
		return homecomplainService.getListByAll();
	}

	@PostMapping("/add")
	public ResultMessage<HomeComplainModel> addComplainType(HomeComplainModel homeComplain) {
		if (homeComplain != null) {

			try {
				homecomplainService.add(homeComplain);
			} catch (Exception e) {
				return new ResultMessage<HomeComplainModel>("ERROR", "增加投诉类型失败");
			}
			return new ResultMessage<HomeComplainModel>("OK", "增加投诉类型成功");
		} else {
			return new ResultMessage<HomeComplainModel>("ERROR", "增加的投诉类型对象为空");
		}
	}

	@PostMapping("/delete")
	public ResultMessage<HomeComplainModel> deleteComplainType(HomeComplainModel homeComplain) throws Exception {
		if (homeComplain != null) {

			try {
				homecomplainService.delete(homeComplain);
			} catch (Exception e) {
				return new ResultMessage<HomeComplainModel>("ERROR", "删除投诉类型失败");
			}
			return new ResultMessage<HomeComplainModel>("OK", "删除投诉类型成功");
		} else {
			return new ResultMessage<HomeComplainModel>("ERROR", "删除的投诉类型对象为空");
		}
	}

	@PostMapping("/modify")
	public ResultMessage<HomeComplainModel> modifyComplainType(HomeComplainModel homeComplain) throws Exception {
		if (homeComplain != null) {

			try {
				homecomplainService.modify(homeComplain);
			} catch (Exception e) {
				return new ResultMessage<HomeComplainModel>("ERROR", "修改投诉类型失败");
			}
			return new ResultMessage<HomeComplainModel>("OK", "修改投诉类型成功");
		} else {
			return new ResultMessage<HomeComplainModel>("ERROR", "修改的投诉类型对象为空");
		}
	}

	@GetMapping("/get")
	public HomeComplainModel getHomeComplainType(@RequestParam(required = true) int complainno) throws Exception {
		return homecomplainService.getByNoWithHomeAndComplainType(complainno);
	}


	@GetMapping("/list/all/page")
	public ResultMessage<HomeComplainModel> getListWithPage(@RequestParam(required = false, defaultValue = "2") int rows,
			@RequestParam(required = false, defaultValue = "1") int pages) throws Exception {
		
		int pageCount = homecomplainService.getPageCountByAll(rows);
		int count = homecomplainService.getCountByAll();
		List<HomeComplainModel> list = homecomplainService.getListByAllWithPage(rows, pages);
		return new ResultMessage<HomeComplainModel>(list,rows,pages,count,pageCount,"OK","查询成功");
	}

}
