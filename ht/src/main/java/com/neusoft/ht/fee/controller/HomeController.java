package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.HomeModel;
import com.neusoft.ht.fee.service.IHomeService;
import com.neusoft.ht.message.ResultMessage;

/**
 * 模块：供热缴费管理
 * 居民管理的控制类
 * @author 黄宇德
 *
 */
@RestController
@RequestMapping(value="/fee/home")
public class HomeController {
	@Autowired
	private IHomeService homeService = null;
	
	//增加
	@PostMapping("/add")
	public ResultMessage<HomeModel> add(HomeModel homeModel ) throws Exception {		
			homeService.add(homeModel);
			return new ResultMessage<HomeModel>("OK","增加居民信息成功");	
	}

	//修改
	@PostMapping("/modify")
	public ResultMessage<HomeModel> modify(HomeModel homeModel ) throws Exception {
		homeService.modify(homeModel);
		return new ResultMessage<HomeModel>("OK","修改居民信息成功");
	
	}

	//删除
	@PostMapping("/delete")
	public ResultMessage<HomeModel> delete(HomeModel homeModel) throws Exception {
		homeService.delete(homeModel);
		return new ResultMessage<HomeModel>("OK","删除居民信息成功");
	}

	//取得指定序号的居民信息
	@GetMapping("/get")
	public ResultMessage<HomeModel> getByNo(int homeno) throws Exception{
		ResultMessage<HomeModel> result = new ResultMessage<HomeModel>("OK","取得居民信息成功");
		result.setModel(homeService.getByNo(homeno));
		return result;
	}
		
	//取关联小区和户型的特定居民
	@GetMapping("/get/detail")
	public ResultMessage<HomeModel> getByNoWithHoodNoAndHouseTypeNo(int homeno) throws Exception{
		ResultMessage<HomeModel> result = new ResultMessage<HomeModel>("OK","取得带关联的居民信息成功");
		result.setModel(homeService.getByNoWithHoodNoAndHouseTypeNo(homeno));
		return result;
	}
	
	//取得所有列表，有分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<HomeModel> getListByAllWitPage(@RequestParam(required = false,defaultValue ="4") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
			
		ResultMessage<HomeModel> result=new ResultMessage<HomeModel>("OK","取得居民信息列表分页模式成功");
		result.setCount(homeService.getCountByAll());
		result.setPageCount(homeService.getPagaCountByAll(rows));
		result.setList(homeService.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
			
		return result;
	}
			
	//取得所有列表
	@GetMapping(value="/list/all")
	public List<HomeModel> getListByAll() throws Exception{
		return homeService.getListByAll();
	}
	
	//按小区类型和房型进行检索,分页模式
	@GetMapping(value="/list/all/page/condition")
	public ResultMessage<HomeModel> getListByConditionWithHoodNoAndHouseTypeNoWithPage(@RequestParam(required = false,defaultValue ="0")int hoodno, @RequestParam(required = false,defaultValue ="0")int housetypeno, 
			@RequestParam(required = false,defaultValue ="4") int rows,@RequestParam(required = false,defaultValue = "1")int page) 
					throws Exception{
		
		ResultMessage<HomeModel> result=new ResultMessage<HomeModel>("OK","条件检索取得居民信息列表分页模式成功");
		result.setCount(homeService.getCountByAll());
		result.setPageCount(homeService.getPagaCountByAll(rows));
		result.setList(homeService.getListByConditionWithHoodNoAndHouseTypeNoWithPage(hoodno, housetypeno, rows, page));
		result.setPage(page);
		result.setRows(rows);
			
		return result;
		
	}
	
	//修改供热状态
	@PostMapping(value="/changeheatingstatus")
	public ResultMessage<HomeModel> ChangeHeatingStatus(int homeno,String heatingstatus) throws Exception {
		ResultMessage<HomeModel> result=new ResultMessage<HomeModel>("OK","修改供热状态成功");
		homeService.ChangeHeatingStatus(homeno,heatingstatus);
		return result;
	}

}
