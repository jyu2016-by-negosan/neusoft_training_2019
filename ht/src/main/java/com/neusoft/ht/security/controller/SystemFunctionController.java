package com.neusoft.ht.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.message.ResultMessage;
import com.neusoft.ht.security.model.SystemFunctionModel;
import com.neusoft.ht.security.service.ISystemFunctionService;

/**模块：系统安全模块
 * 系统功能的控制类
 * @author 吴澍淼
 *
 */
@RestController
@RequestMapping(value="/security/systemfuncyion")
public class SystemFunctionController {
	
	@Autowired
	private ISystemFunctionService systemFunctionService=null;
	
	//增加系统功能
	@PostMapping("/add")
	public ResultMessage<SystemFunctionModel> add(SystemFunctionModel systemFunctionModel) throws Exception{
		try {
			systemFunctionService.add(systemFunctionModel);
		} catch (Exception e) {
			return new ResultMessage<SystemFunctionModel>("ERROR", systemFunctionModel.getFunno()+"系统功能已存在！");
		}
			return new ResultMessage<SystemFunctionModel>("Success!","增加系统功能成功");
	}
	
	//修改系统功能
	@PostMapping("/modify")
	public ResultMessage<SystemFunctionModel> modify(SystemFunctionModel systemFunctionModel) throws Exception{
		systemFunctionService.modify(systemFunctionModel);
		return new ResultMessage<SystemFunctionModel>("Success!","修改系统功能信息成功！");
	}
	
	//删除系统功能
	@PostMapping("/delete")
	public ResultMessage<SystemFunctionModel> delete(SystemFunctionModel systemFunctionModel) throws Exception{
		systemFunctionService.delete(systemFunctionModel);
		return new ResultMessage<SystemFunctionModel>("Success!","删除系统功能信息成功！");
	}
	
	//取得系统功能所有列表，无分页
	@GetMapping(value = "/list/all")
	public List<SystemFunctionModel> getListByAll() throws Exception{
		return systemFunctionService.getListByAll();
	}
	
	//取得系统功能所有列表，有分页
	@GetMapping(value = "/list/all/page")
	public ResultMessage<SystemFunctionModel> getListByAllWithPage(@RequestParam(required = false, defaultValue = "4") int rows, @RequestParam(required = false, defaultValue = "1") int page) throws Exception{
		ResultMessage<SystemFunctionModel> result = new ResultMessage<SystemFunctionModel>("Success!","取得系统功能列表分页模式成功！");
		result.setCount(systemFunctionService.getCountByAll());
		result.setPageCount(systemFunctionService.getPageCountByAll(rows));
		result.setList(systemFunctionService.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
		
		return result;
	}
	
	//取得指定功能编号的功能信息
	@GetMapping("/getSystemFunctionByNo")
	public ResultMessage<SystemFunctionModel> getByNo(int funno) throws Exception{
		ResultMessage<SystemFunctionModel> result = new ResultMessage<SystemFunctionModel>("Success!","取得指定功能编号的功能信息成功！");
		result.setModel(systemFunctionService.getByNo(funno));
		return result;
	}

}
