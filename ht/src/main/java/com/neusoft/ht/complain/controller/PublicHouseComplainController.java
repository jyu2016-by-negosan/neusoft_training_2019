package com.neusoft.ht.complain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.complain.model.PublicHouseComplainModel;
import com.neusoft.ht.complain.service.IPublicHouseComplainService;
import com.neusoft.ht.message.ResultMessage;

@RestController
@RequestMapping("/housecomplain")
public class PublicHouseComplainController {

	@Autowired
	private IPublicHouseComplainService housecomplainService;

	@RequestMapping("/list")
	public List<PublicHouseComplainModel> getComplainTypeList() throws Exception {
		return housecomplainService.getListByAll();
	}

	@RequestMapping("/add")
	public ResultMessage<PublicHouseComplainModel> addComplainType(PublicHouseComplainModel houseComplain) {
		if (houseComplain == null) {
			return new ResultMessage<PublicHouseComplainModel>("ERROR", "增加的投诉类型对象为空");
		} else {

			try {
				housecomplainService.add(houseComplain);
			} catch (Exception e) {

				return new ResultMessage<PublicHouseComplainModel>("ERROR", "增加投诉类型失败");
			}
			return new ResultMessage<PublicHouseComplainModel>("OK", "增加投诉类型成功");
		}
	}

	@RequestMapping("/delete")
	public ResultMessage<PublicHouseComplainModel> deleteComplainType(PublicHouseComplainModel houseComplain)
			throws Exception {
		if (houseComplain != null) {

			try {
				housecomplainService.delete(houseComplain);
			} catch (Exception e) {
				return new ResultMessage<PublicHouseComplainModel>("ERROR", "删除投诉类型失败");
			}
			return new ResultMessage<PublicHouseComplainModel>("OK", "删除投诉类型成功");
		} else {
			return new ResultMessage<PublicHouseComplainModel>("ERROR", "删除的投诉类型对象为空");
		}
	}

	@RequestMapping("/modify")
	public ResultMessage<PublicHouseComplainModel> modifyComplainType(PublicHouseComplainModel houseComplain)
			throws Exception {
		if (houseComplain != null) {

			try {
				housecomplainService.modify(houseComplain);
			} catch (Exception e) {
				return new ResultMessage<PublicHouseComplainModel>("ERROR", "修改投诉类型失败");
			}
			return new ResultMessage<PublicHouseComplainModel>("OK", "修改投诉类型成功");
		} else {
			return new ResultMessage<PublicHouseComplainModel>("ERROR", "修改的投诉类型对象为空");
		}
	}

	@RequestMapping("/getBytypeNo")
	public PublicHouseComplainModel getComplainType() throws Exception {
		return housecomplainService.getHouseComplainBycomplainNo(1);
	}

	@RequestMapping("/getCountByAll")
	public int getAllCount() throws Exception {
		return housecomplainService.getCountByAll();
	}

	@RequestMapping("/listwithpage")
	public List<PublicHouseComplainModel> getListWithPage(@RequestParam(required = false, defaultValue = "2") int rows,
			@RequestParam(required = false, defaultValue = "1") int pages) throws Exception {
		return housecomplainService.selectListByAllWithPage(rows, pages);
	}

}
