package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.HouseTypeModel;
import com.neusoft.ht.fee.service.IHouseTypeService;
import com.neusoft.ht.message.ResultMessage;
/**模块：供热缴费管理
 * 房屋类型管理的控制类
 * @author 黄宇德
 *
 */
@RestController
@RequestMapping(value="/fee/housetype")
public class HouseTypeController {

	@Autowired
	private IHouseTypeService houseTypeService = null;
		
	//增加
	@PostMapping("/add")
	public ResultMessage<HouseTypeModel> add(HouseTypeModel houseTypeModel ) throws Exception {
		houseTypeService.add(houseTypeModel);
		return new ResultMessage<HouseTypeModel>("OK","增加房屋类型成功");
	}

	//修改
	@PostMapping("/modify")
	public ResultMessage<HouseTypeModel> modify(HouseTypeModel houseTypeModel ) throws Exception {
		houseTypeService.modify(houseTypeModel);
		return new ResultMessage<HouseTypeModel>("OK","修改房屋类型成功");
	}

	//删除
	@PostMapping("/delete")
	public ResultMessage<HouseTypeModel> delete(HouseTypeModel houseTypeModel) throws Exception {
		houseTypeService.delete(houseTypeModel);
		return new ResultMessage<HouseTypeModel>("OK","删除房屋类型成功");
	}

	//取得指定的房屋类型
	@GetMapping("/get")
	public ResultMessage<HouseTypeModel> getByNo(int typeno) throws Exception{
		
		ResultMessage<HouseTypeModel> result = new ResultMessage<HouseTypeModel>("OK","修改户型成功");
		result.setModel(houseTypeService.getByNo(typeno));
		return result;
	}
		
	//取得所有小区列表，有分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<HouseTypeModel> getListByAllWitPage(@RequestParam(required = false,defaultValue ="4") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
			
		ResultMessage<HouseTypeModel> result=new ResultMessage<HouseTypeModel >("OK","取得房屋类型列表分页模式成功");
		result.setCount(houseTypeService.getCountByAll());
		result.setPageCount(houseTypeService.getPagaCountByAll(rows));
		result.setList(houseTypeService.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
			
		return result;
	}
		
	//取得所有房屋类型列表，无分页
	@GetMapping(value="/list/all")
	public List<HouseTypeModel> getListByAll() throws Exception{
		return houseTypeService.getListByAll();
	}

}
