package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.NeighbourHoodModel;
import com.neusoft.ht.fee.service.INeighbourHoodService;
import com.neusoft.ht.message.ResultMessage;


@RestController
@RequestMapping(value="/neighbourhood")
public class NeighbourHoodController {

	@Autowired
	private INeighbourHoodService neighbourHoodService = null;
	
	//增加小区
	@PostMapping("/add")
	public ResultMessage<NeighbourHoodModel> add(NeighbourHoodModel neighbourHoodModel) throws Exception {
		neighbourHoodService.add(neighbourHoodModel);
		return new ResultMessage<NeighbourHoodModel>("OK","增加小区成功");
	}
	//修改小区信息
	@PostMapping("/modify")
	public ResultMessage<NeighbourHoodModel> modify(NeighbourHoodModel neighbourHoodModel) throws Exception {
		neighbourHoodService.modify(neighbourHoodModel);
		return new ResultMessage<NeighbourHoodModel>("OK","修改小区信息成功");
	}
	//删除小区
	@PostMapping("/delete")
	public ResultMessage<NeighbourHoodModel> delete(NeighbourHoodModel neighbourHoodModel) throws Exception {
		neighbourHoodService.delete(neighbourHoodModel);
		return new ResultMessage<NeighbourHoodModel>("OK","删除小区成功");
	}
	//取得指定的小区
	@GetMapping("/get")
	public NeighbourHoodModel getByNo(int hoodno) throws Exception{
		return neighbourHoodService.getByNo(hoodno);
	}
	
	//取得所有小区列表，有分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<NeighbourHoodModel> getListByAllWitPage(@RequestParam(required = false,defaultValue ="4") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
		
		ResultMessage<NeighbourHoodModel> result=new ResultMessage<NeighbourHoodModel>("OK","取得小区列表分页模式成功");
		result.setCount(neighbourHoodService.getCountByAll());
		result.setPageCount(neighbourHoodService.getPagaCountByAll(rows));
		result.setList(neighbourHoodService.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	
	//取得所有小区列表，无分页
	@GetMapping(value="/list/all")
	public List<NeighbourHoodModel> getListByAll() throws Exception{
		return neighbourHoodService.getListByAll();
	}
}
