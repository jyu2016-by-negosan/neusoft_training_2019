package com.neusoft.ht.complain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.complain.model.HomeComplainModel;
import com.neusoft.ht.complain.service.IHomeComplainService;
import com.neusoft.ht.message.ResultMessage;

@RestController
@RequestMapping("/homecomplain")
public class HomeComplainController {

	@Autowired
	private IHomeComplainService homecomplainService;

	@RequestMapping("/list")
	public List<HomeComplainModel> getComplainTypeList() throws Exception {
		return homecomplainService.getListByAll();
	}

	@RequestMapping("/add")
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

	@RequestMapping("/delete")
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

	@RequestMapping("/modify")
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

	@RequestMapping("/getBytypeNo")
	public HomeComplainModel getComplainType() throws Exception {
		return homecomplainService.getHomeComplainBycomplainNo(1);
	}

	@RequestMapping("/getCountByAll")
	public int getAllCount() throws Exception {
		return homecomplainService.getCountByAll();
	}

	@RequestMapping("/listwithpage")
	public List<HomeComplainModel> getListWithPage(@RequestParam(required = false, defaultValue = "2") int rows,
			@RequestParam(required = false, defaultValue = "1") int pages) throws Exception {
		return homecomplainService.selectListByAllWithPage(rows, pages);
	}

}
