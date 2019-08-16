package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.HomeFeeModel;
import com.neusoft.ht.fee.service.IHomeFeeService;
import com.neusoft.ht.message.ResultMessage;

/**
 * 模块：供热缴费管理
 * 住宅供热记录管理的控制类
 * @author 黄宇德
 *
 */
@RestController
@RequestMapping(value="/fee/homefee")
public class HomeFeeController {

	@Autowired
	private IHomeFeeService homeFeeService =null;
	
	//增加
	@PostMapping("/add")
	public ResultMessage<HomeFeeModel> add(HomeFeeModel homeFeeModel ) throws Exception {		
			homeFeeService.add(homeFeeModel);
			return new ResultMessage<HomeFeeModel>("OK","增加住宅供热记录信息成功");	
	}

	//修改
	@PostMapping("/modify")
	public ResultMessage<HomeFeeModel> modify(HomeFeeModel homeFeeModel ) throws Exception {
		homeFeeService.modify(homeFeeModel);
		return new ResultMessage<HomeFeeModel>("OK","修改住宅供热记录信息成功");
	
	}

	//删除
	@PostMapping("/delete")
	public ResultMessage<HomeFeeModel> delete(HomeFeeModel homeFeeModel) throws Exception {
		homeFeeService.delete(homeFeeModel);
		return new ResultMessage<HomeFeeModel>("OK","删除住宅供热记录信息成功");
	}
	
	//取所有信息，无关联
	@GetMapping("/list/all")
	public List<HomeFeeModel> getListByAll() throws Exception {
		
		return homeFeeService.getListByAll();
	}
	
	
	//取得特定居民供热记录，取关联的居民表和年度供热价格表
	@GetMapping("/get")
	public ResultMessage<HomeFeeModel> getByNoWithHomeAndHeatingPrice(int feeno) throws Exception {
		ResultMessage<HomeFeeModel> result=new ResultMessage<HomeFeeModel>("OK","取得特定居民供热记录成功");
		result.setModel(homeFeeService.getByNoWithHomeAndHeatingPrice(feeno));
		return result;

	}
	
	//根据综合检索条件取得居民供热记录列表，取得关联的居民表，取关联的年度供热价格表，分页模式
	@GetMapping("/list/all/page/condition")
	public ResultMessage<HomeFeeModel> getListByConditionWithHomeAndHeatingPriceWithPage(@RequestParam(required = false,defaultValue ="0") int hoodno, @RequestParam(required = false,defaultValue ="") String heatingyear,
			@RequestParam(required = false,defaultValue ="") String feestatus, @RequestParam(required = false,defaultValue ="4") int rows, @RequestParam(required = false,defaultValue ="1") int page) throws Exception {
		
		ResultMessage<HomeFeeModel> result=new ResultMessage<HomeFeeModel>("OK","条件检索取得住宅供热记录信息列表分页模式成功");
		result.setCount(homeFeeService.getCountByAllWithHomeAndHeatingPriceWithPage(hoodno, heatingyear, feestatus));
		result.setPageCount(homeFeeService.getPageCountByAll(rows,hoodno, heatingyear, feestatus));
		result.setList(homeFeeService.getListByConditionWithHomeAndHeatingPriceWithPage(hoodno, heatingyear, feestatus, rows, page));
		result.setPage(page);
		result.setRows(rows);

			
		return result;
		
	}

	
}
