# 东软2019培训项目团队-供热缴费管理系统
##  团队成员

团队|姓名|任务分配(暂定，快认领)
--|:--:|:-
组长|黄宇德|供热收费管理模块（小区管理，房型管理，付款类型管理，年份供热价格管理，供热居民管理，住宅供热记录管理）
组员|罗妙忠|供热收费管理模块（住宅缴费记录管理，住宅供热退费管理，住宅停供记录管理）
组员|吴澍淼|系统安全模块（登录管理，系统操作员管理，系统功能配置）
组员|张晓龙|供热收费管理模块（供热公建管理，公建年度供热费管理，修改公建供热状态，公建缴费记录管理，公建退费记录管理）
组员|万俊星|客户服务模块（投诉管理，文件管理，通知管理）
****************************

### 项目参数

+ 域名：com.neusoft
+ 项目：ht
+ 项目模块
1. 供热收费管理模块（fee）
2. 客户服务模块（complain）
3. 系统安全模块（security）


******************************
### 模块关系整理

#### 1.采暖费模块 对应 5
表数量：14|主键|表名|关联的表
:-|:--|:--|:--
小区信息表           |HOODNO   |HT_Neighbourhood		
户型表               |TYPENO   |HT_HOUSETYPE
居民表               |HOMENO   |HT_HOME|户型表(TYPENO) 1:1,小区信息表(HoodNo) 1:1
公建表		            |HOUSENO  |HT_publichouse	|小区信息表(HoodNo)1:1
供热年度费率表	       |HeatingYear	|HT_HeadtingPrice|
付款方式表	           |TYPENO 	|HT_PaymentType|
居民供热记录表	        |FeeNo		|HT_homeFee			|居民表(HomeNo)1:1, 供热年度费率表(HeatingYear)1:1
住宅供热缴费记录表     |RecordNo	 |HT_HomeFeePayRecord		|居民供热记录表(FeeNo)1:1,付款方式表(TypeNo)1:1
住宅供热退费记录表     |RecordNo	|HT_HomeFeeReturnRecord		|居民表(HomeNo)1:1,付款方式表(TypeNo)1:1
住宅供热退费记录表     |RecordNo	|HT_HomeFeeReturnRecord		|居民表(HomeNo)1:1,付款方式表(TypeNo)1:1
住宅供热报停表	        |RecordNo		|HT_HomeStopRecord		|居民供热记录表(FeeNo)1:1
公建供热记录表	        |FeeNo		|HT_PublicHouseFee			|公建表(HouseNo)1:1, 供热年度费率表(HeatingYear)1:1
公建供热缴费记录表      |RecordNo		|HT_PublicHouseFeePayRecord		|公建供热记录表(FeeNo)1:1,付款方式表(TypeNo)1:1
公建供热退费记录	     |RecordNo	|HT_PublicHouseFeeReturnRecord	|公建表(HouseNo)1:1,付款方式表(TypeNo)1:1
公建供热报停表	        |RecordNo	|HT_PublicHouseStopRecord	|公建供热记录表(FeeNo)1:1


#### 2. 客户服务模块

表数量：3	                           |主键		|表名				|关联的表
:-|:--|:--|:--
|投诉类型表	           |TYPENO		|HT_complainType
|住宅投诉记录表	           |ComplainNo		|HT_Homecomplain			|居民表(HomeNo)1:1，投诉类型表(TypeNO)
|公建投诉记录表	           |ComplainNo		|HT_PublicHousecomplain		|公建表(HouseNo)1:1,  投诉类型表(TypeNO)


#### 3. 系统安全模块 

表数量：4	                           |主键		|表名				|关联的表
:-|:--|:--|:--
|操作员表	           |UUSERID		|HT_SystemUser
|系统功能表	           |FUNNO	 	|HT_Function
|系统功能权限表（操作员与功能的关联表）	        |FUNNO,UUserID	|HT_FunctionUse
|系统管理员辅助信息表       |UUSERID		|HT_UserInfo			|操作员表(UUSERID) 1:1    

