package com.neusoft.ht.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.ht.fee.model.PaymentTypeModel;
import com.neusoft.ht.fee.service.IPaymentTypeService;
import com.neusoft.ht.message.ResultMessage;
/**模块：供热缴费管理
 * 付款类型管理的控制类
 * @author 黄宇德
 *
 */
@RestController
@RequestMapping(value = "/fee/paymenttype")
public class PaymentTypeController {

	@Autowired
	private IPaymentTypeService paymentTypeService = null;
		
	//增加
	@PostMapping("/add")
	public ResultMessage<PaymentTypeModel> add(PaymentTypeModel paymentTypeModel) throws Exception {
		paymentTypeService.add(paymentTypeModel);
		return new ResultMessage<PaymentTypeModel>("OK","增加付款类型成功");
	}

	//修改
	@PostMapping("/modify")
	public ResultMessage<PaymentTypeModel> modify(PaymentTypeModel paymentTypeModel) throws Exception {
		paymentTypeService.modify(paymentTypeModel);
		return new ResultMessage<PaymentTypeModel>("OK","修改付款类型成功");
	}

	//删除
	@PostMapping("/delete")
	public ResultMessage<PaymentTypeModel> delete(PaymentTypeModel paymentTypeModel) throws Exception {
		paymentTypeService.delete(paymentTypeModel);
		return new ResultMessage<PaymentTypeModel>("OK","删除付款类型成功");
	}

	//取得指定的付款类型
	@GetMapping("/get")
	public ResultMessage<PaymentTypeModel> getByNo(int typeno) throws Exception{
		
		ResultMessage<PaymentTypeModel> result = new ResultMessage<PaymentTypeModel>("OK","修改付款方式成功");
		result.setModel(paymentTypeService.getByNo(typeno));
		return result;
	}
		
	//取得所有小区列表，有分页
	@GetMapping(value="/list/all/page")
	public ResultMessage<PaymentTypeModel> getListByAllWitPage(@RequestParam(required = false,defaultValue ="4") int rows,@RequestParam(required = false,defaultValue = "1") int page) throws Exception{
			
		ResultMessage<PaymentTypeModel> result=new ResultMessage<PaymentTypeModel>("OK","取得付款类型列表分页模式成功");
		result.setCount(paymentTypeService.getCountByAll());
		result.setPageCount(paymentTypeService.getPagaCountByAll(rows));
		result.setList(paymentTypeService.getListByAllWithPage(rows, page));
		result.setPage(page);
		result.setRows(rows);
			
		return result;
	}
		
	//取得所有付款类型列表，无分页
	@GetMapping(value="/list/all")
	public List<PaymentTypeModel> getListByAll() throws Exception{
		return paymentTypeService.getListByAll();
	}

}
