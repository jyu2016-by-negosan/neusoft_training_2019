package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.PublicHouseModel;
import com.neusoft.ht.fee.service.IPublicHouseService;
import com.neusoft.ht.message.ResultMessage;
/**
 * 模块：供热公建表管理
 * @author 罗妙忠
 *
 */
@RestController
@RequestMapping("/fee/publicHouse")
public class PublicHouseController {
	
	@Autowired
	private IPublicHouseService publicHouseService;
	
	@RequestMapping("/addPublicHouse")
	public ResultMessage<PublicHouseModel> addPublicHouse(PublicHouseModel publicHouseModel){
		if(publicHouseModel!=null) {
			try {
				publicHouseService.add(publicHouseModel);
			}catch(Exception e) {
				e.printStackTrace();
				return new ResultMessage<PublicHouseModel>("ERROR","添加公建表失败！");
			}
		}else {
			return new ResultMessage<PublicHouseModel>("ERROR","公建表不能为空！");
		}
		return new ResultMessage<PublicHouseModel>("OK","添加公建表成功！");
	}
	
	@RequestMapping("/modifyPublicHouse")
	public ResultMessage<PublicHouseModel> modifyPublicHouse(PublicHouseModel publicHouseModel){
		if(publicHouseModel!=null) {
			try {
				publicHouseService.modify(publicHouseModel);
			}catch(Exception e) {
				e.printStackTrace();
				return new ResultMessage<PublicHouseModel>("ERROR","修改公建表失败！");
			}
		}else {
			return new ResultMessage<PublicHouseModel>("ERROR","公建表不能为空！");
		}
		return new ResultMessage<PublicHouseModel>("OK","修改公建表成功！");
	}
	
	@RequestMapping("/deletePublicHouse")
	public ResultMessage<PublicHouseModel> deletePublicHouse(PublicHouseModel publicHouseModel){
		if(publicHouseModel!=null) {
			try {
				publicHouseService.delete(publicHouseModel);
			}catch(Exception e) {
				e.printStackTrace();
				return new ResultMessage<PublicHouseModel>("ERROR","删除公建表失败！");
			}
		}else {
			return new ResultMessage<PublicHouseModel>("ERROR","公建表不存在！");
		}
		return new ResultMessage<PublicHouseModel>("OK","删除公建表成功！");
	}
	
	@RequestMapping("/getPublicHouseByNo")
	public ResultMessage<PublicHouseModel> getPublicHouseByNo(int houseno){
		PublicHouseModel publicHouseModel=null;
			try {
			publicHouseModel = publicHouseService.getByNo(houseno);
			}catch(Exception e) {
				return new ResultMessage<PublicHouseModel>("ERROR","查找公建表失败！");
			}
		
		return new ResultMessage<PublicHouseModel>(publicHouseModel,"OK","查找公建表成功！");
	}
	
	@RequestMapping("/getAllPublicHouse")
	public ResultMessage<PublicHouseModel> getAllPublicHouse(){
			List<PublicHouseModel> list=null;
			try {
				list = publicHouseService.getListByAll();
			}catch(Exception e) {
				return new ResultMessage<PublicHouseModel>("ERROR","查找公建表失败！");
			}
		
		return new ResultMessage<PublicHouseModel>(list,"OK","查找公建表成功！");
	}
	
	@RequestMapping("/getAllPublicHouseWithPages")
	public ResultMessage<PublicHouseModel> getAllPublicHouseWithPages(int rows,int page){
		List<PublicHouseModel> list=null;
			try {
				list=publicHouseService.getListByAllWithPage(rows, page);
			}catch(Exception e) {
				return new ResultMessage<PublicHouseModel>("ERROR","查找公建表分页失败！");
			}
		
		return new ResultMessage<PublicHouseModel>(list,"OK","查找公建表分页成功！");
	}
	
	@RequestMapping("/getPublicHouseCount")
	public ResultMessage<PublicHouseModel> getPublicHouseCount(){
			int count = 0;
			try {
				count = publicHouseService.getCountByAll();
			}catch(Exception e) {
				e.printStackTrace();
				return new ResultMessage<PublicHouseModel>("ERROR","查找公建表失败！");
			}
		
		return new ResultMessage<PublicHouseModel>(count,"OK","查找公建表成功！");
	}
	
	
	
	
	
}
