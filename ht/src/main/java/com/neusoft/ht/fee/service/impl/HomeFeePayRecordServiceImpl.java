package com.neusoft.ht.fee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.ht.fee.mapper.IHomeFeeMapper;
import com.neusoft.ht.fee.mapper.IHomeFeePayRecordMapper;
import com.neusoft.ht.fee.model.HomeFeeModel;
import com.neusoft.ht.fee.model.HomeFeePayRecordModel;
import com.neusoft.ht.fee.service.IHomeFeePayRecordService;
/**模块：供热缴费管理
 * 住宅供热缴费记录--ServiceImpl
 * @author 罗妙忠
 *
 */
@Service
@Transactional
public class HomeFeePayRecordServiceImpl implements IHomeFeePayRecordService{
	
	@Autowired
	private IHomeFeePayRecordMapper houseFeePayRecordMapper;
	
	@Autowired
	private IHomeFeeMapper homeFeeMapper;
	
	//增加住宅缴费记录
	public void add(HomeFeePayRecordModel payRecordModel)throws Exception {
		houseFeePayRecordMapper.insert(payRecordModel);

		float payAmount = payRecordModel.getPayamount();
		HomeFeeModel homeFeeModel = homeFeeMapper.selectByNoWithHomeAndHeatingPrice(payRecordModel.getHomeFeeModel().getFeeno());
		homeFeeModel.setActualfee(homeFeeModel.getActualfee()+payAmount);	
		if(homeFeeModel.getDebtfee()-payAmount>0) {
			homeFeeModel.setDebtfee(homeFeeModel.getDebtfee()-payAmount);
		}else {
			homeFeeModel.setDebtfee(0);
			homeFeeModel.setFeestatus("Y");
			System.out.println("feestatus:"+homeFeeModel.getFeestatus());

		}
		homeFeeMapper.update(homeFeeModel);
	}
			
	//删除住宅缴费记录
	public void delete(HomeFeePayRecordModel payRecordModel) throws Exception{
		 houseFeePayRecordMapper.delete(payRecordModel);
	}
			
	//更改缴费记录
	public void modify(HomeFeePayRecordModel payRecordModel)throws Exception {
		HomeFeePayRecordModel homeFeePayRecordModel = houseFeePayRecordMapper.selectById(payRecordModel.getRecordno());
		float payamount = homeFeePayRecordModel.getPayamount();
		float actualpayamount =payRecordModel.getPayamount();
		if(actualpayamount!=payamount){
			if(payamount>actualpayamount) {
				
				float error = payamount-actualpayamount;
				int feeno =  houseFeePayRecordMapper.selectByIdWithFeeno(payRecordModel);
				HomeFeeModel homeFeeModel = homeFeeMapper.selectByNoWithHomeAndHeatingPrice(feeno);
				homeFeeModel.setActualfee(homeFeeModel.getActualfee()-error);		
				homeFeeModel.setDebtfee(homeFeeModel.getDebtfee()+error);
				homeFeeMapper.update(homeFeeModel);
			}else {
				
				float error = actualpayamount-payamount;
				int feeno =  houseFeePayRecordMapper.selectByIdWithFeeno(payRecordModel);
				HomeFeeModel homeFeeModel = homeFeeMapper.selectByNoWithHomeAndHeatingPrice(feeno);
				homeFeeModel.setActualfee(homeFeeModel.getActualfee()+error);	
				if(homeFeeModel.getDebtfee()-error>0) {
					homeFeeModel.setDebtfee(homeFeeModel.getDebtfee()-error);
				}
				else {
					homeFeeModel.setDebtfee(0);
					homeFeeModel.setFeestatus("Y");
					System.out.println(homeFeeModel.getFeestatus());
				}
				homeFeeMapper.update(homeFeeModel);
			}
		}
		
		houseFeePayRecordMapper.update(payRecordModel);	
	}
			
	//根据居民缴费序号查找缴费记录
	public HomeFeePayRecordModel getById(int recordNo)throws Exception {
		return houseFeePayRecordMapper.selectById(recordNo);
		
	}
			
	
	//查找所有住宅缴费记录（分页）
	public List<HomeFeePayRecordModel> getAllByListWithPage(int page,int rows)throws Exception{
		return houseFeePayRecordMapper.selectAllByListWithPage((page-1)*rows, rows);
		
	}

	@Override
	public List<HomeFeePayRecordModel> getAllByList()throws Exception {
		return  houseFeePayRecordMapper.selectAllByList();
		
	}

	@Override
	public int getAllCount()throws Exception {
		return houseFeePayRecordMapper.selectAllCount();
	}

	@Override
	public HomeFeePayRecordModel getByIdWithHomeFee(int recordno)throws Exception {
		return houseFeePayRecordMapper.selectByIdWithHomeFee(recordno);
	}

	
}
