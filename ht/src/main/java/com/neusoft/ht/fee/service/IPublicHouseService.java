package com.neusoft.ht.fee.service;

import java.util.List;
/**模块：供热缴费管理
 * 供热公建管理    --service
 * @author 罗妙忠
 *
 */
import com.neusoft.ht.fee.model.PublicHouseModel;

/**模块：供热缴费管理
 * 供热公建表管理
 * @author 罗妙忠
 *
 */
public interface IPublicHouseService {
		//C
		public void add(PublicHouseModel publicHouseModel) throws Exception;
		//U
		public void modify(PublicHouseModel publicHouseModel) throws Exception;
		//R
		public List<PublicHouseModel> getListByAll() throws Exception;
		//R2
		public PublicHouseModel getByNo(int houseno) throws Exception;
		//R3
		public List<PublicHouseModel> getListByAllWithPage(int rows,int page) throws Exception;
		//D
		public void delete(PublicHouseModel publicHouseModel) throws Exception;
		//取得对象的个数(可选）
		public int getCountByAll() throws Exception;
}
