package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.HomeFeeModel;
import com.neusoft.ht.fee.model.HomeFeeReturnRecordModel;
import com.neusoft.ht.fee.service.IHomeFeeReturnRecordService;
import com.neusoft.ht.fee.service.IHomeFeeService;
import com.neusoft.ht.message.ResultMessage;
/**供热--居民退费管理
 * 罗妙忠 --controller
 */
@RestController
@RequestMapping("/fee/homefeereturnrecord")
public class HomeFeeReturnRecordController {
	@Autowired
	private IHomeFeeReturnRecordService homeFeeReturnRecordService;
	
	@Autowired
	private IHomeFeeService homeFeeService;

	@PostMapping("/add")
	public ResultMessage<HomeFeeReturnRecordModel> addHomeFeeReturnRecord(
			HomeFeeReturnRecordModel homeFeeReturnRecordModel,int feeno) {
		if (homeFeeReturnRecordModel != null) {
			try {
				HomeFeeModel homeFeeModel = homeFeeService.getByNoWithHomeAndHeatingPrice(feeno);
				if(homeFeeReturnRecordModel.getAmount()>(homeFeeModel.getActualfee()-homeFeeModel.getAgreefee())) {
					return new ResultMessage<HomeFeeReturnRecordModel>("ERROR","退款金额不得大于可退费金额");
				}
				homeFeeReturnRecordModel.setHomeFeeModel(homeFeeModel);
				homeFeeReturnRecordService.add(homeFeeReturnRecordModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "添加居民退费记录失败！");
			}
		} else {
			return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "居民退费记录不能为空！");
		}
		return new ResultMessage<HomeFeeReturnRecordModel>("OK", "添加居民退费记录成功！");
	}

	@RequestMapping("/modify")
	public ResultMessage<HomeFeeReturnRecordModel> modifyHomeFeeReturnRecord(
			HomeFeeReturnRecordModel homeFeeReturnRecordModel) {
		if (homeFeeReturnRecordModel != null) {
			try {
				homeFeeReturnRecordService.modify(homeFeeReturnRecordModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "修改居民退费记录失败！");
			}
		} else {
			return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "居民退费记录不能为空！");
		}
		return new ResultMessage<HomeFeeReturnRecordModel>("OK", "修改居民退费记录成功！");
	}

	@RequestMapping("/delete")
	public ResultMessage<HomeFeeReturnRecordModel> deleteHomeFeeReturnRecord(
			HomeFeeReturnRecordModel homeFeeReturnRecordModel) {
		if (homeFeeReturnRecordModel != null) {
			try {
				homeFeeReturnRecordService.delete(homeFeeReturnRecordModel);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "删除居民退费记录失败！");
			}
		} else {
			return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "居民退费记录不能为空！");
		}
		return new ResultMessage<HomeFeeReturnRecordModel>("OK", "删除居民退费记录成功！");
	}

	@RequestMapping("/getById")
	public ResultMessage<HomeFeeReturnRecordModel> getHomeFeeReturnRecordById(
			@RequestParam(defaultValue = "-1", required = false) int recordno) {
		
		HomeFeeReturnRecordModel homeFeeReturnRecordModel=null;
		if (recordno == -1) {
			return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "查找Id不能为空");
		}
	
		try {
			homeFeeReturnRecordModel=homeFeeReturnRecordService.getById(recordno);
		} catch (Exception e) {
			return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "查找居民退费记录失败！");
		}

		return new ResultMessage<HomeFeeReturnRecordModel>(homeFeeReturnRecordModel,"OK", "查找居民退费记录成功！");
	}
	
	@RequestMapping("/getByIdWithHomeFee")
	public ResultMessage<HomeFeeReturnRecordModel> getHomeFeeReturnRecordAndHomeFeeById(int recordno){
		HomeFeeReturnRecordModel homeFeeReturnRecordModel = null;
		try {
			homeFeeReturnRecordModel = homeFeeReturnRecordService.getByIdWithHomeFee(recordno);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "查找居民退费记录失败！");

		}
		//System.out.println(homeFeeReturnRecordModel.getHomeFeeModel().getActualfee());
		return new ResultMessage<HomeFeeReturnRecordModel>(homeFeeReturnRecordModel,"OK", "查找居民退费记录成功！");

	}
	
	@RequestMapping("/getReturnAmount")
	public float getReturnAmount(int feeno){
		try {
			HomeFeeModel homeFeeModel = homeFeeService.getByNoWithHomeAndHeatingPrice(feeno);
			
			if(homeFeeModel.getActualfee()>homeFeeModel.getAgreefee()) {
				return homeFeeModel.getActualfee()-homeFeeModel.getAgreefee();
			}
		} catch (Exception e) {
			return -9999;
		}
		return 0;
	}

	
	
	@RequestMapping("/getAllByList")
	public ResultMessage<HomeFeeReturnRecordModel> getAllHomeFeeReturnRecordByList() {
		List<HomeFeeReturnRecordModel> list = null;
		try {
			list = homeFeeReturnRecordService.getAllByList();
		} catch (Exception e) {
			return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "查找居民退费记录失败！");
		}

		return new ResultMessage<HomeFeeReturnRecordModel>(list, "OK", "查找居民退费记录成功！");
	}

	@RequestMapping("/getAllByListWithPages")
	public ResultMessage<HomeFeeReturnRecordModel> getAllHomeFeeReturnRecordByListWithPages(
			@RequestParam(defaultValue = "-1", required = false) int rows,
			@RequestParam(defaultValue = "-1", required = false) int page) {
		List<HomeFeeReturnRecordModel> list = null;
		if(rows==-1 || page==-1) {
			return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "分页参数不能为空！");
		}
		try {
			list = homeFeeReturnRecordService.getAllByListWithPages(rows, page);
		} catch (Exception e) {
			return new ResultMessage<HomeFeeReturnRecordModel>("ERROR", "查找居民退费记录失败！");
		}

		return new ResultMessage<HomeFeeReturnRecordModel>(list, "OK", "查找居民退费记录成功！");
	}

}
