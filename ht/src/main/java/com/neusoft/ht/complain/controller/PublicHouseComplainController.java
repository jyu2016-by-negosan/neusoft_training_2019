package com.neusoft.ht.complain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.complain.model.PublicHouseComplainModel;
import com.neusoft.ht.complain.service.IPublicHouseComplainService;
import com.neusoft.ht.message.ResultMessage;

@RestController
@RequestMapping("complain/housecomplain")
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

	@RequestMapping("/get")
	public ResultMessage<PublicHouseComplainModel> getHouseComplainType(@RequestParam(required = true) int complainno) throws Exception {
		return new ResultMessage<PublicHouseComplainModel>(housecomplainService.getByNoWithHouseAndComplainType(complainno),"OK","取得特定居民供热记录成功");
	}


	@GetMapping("/list/all/page/condition")
	public ResultMessage<PublicHouseComplainModel> getListWithPage(@RequestParam(required = false, defaultValue = "2") int rows,
			@RequestParam(required = false, defaultValue = "1") int pages) throws Exception {
		
		int pageCount = housecomplainService.getPageCountByAll(rows);
		int count = housecomplainService.getCountByAll();
		List<PublicHouseComplainModel> list = housecomplainService.getListByAllWithPage(rows, pages);
		return new ResultMessage<PublicHouseComplainModel>(list,rows,pages,count,pageCount,"OK","查询成功");
	}

}
