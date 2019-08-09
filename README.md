# 东软2019培训项目团队-供热缴费管理系统
##  团队成员

团队|姓名|任务分配(暂定，快认领)
--|:--:|:-
<<<<<<< HEAD
组长|黄宇德|供热收费管理模块(基础信息，年份供热价格管理，供热居民管理，住宅供热记录管理，住宅缴费记录管理，住宅供热退费管理，住宅停供记录管理)
=======
组长|黄宇德|供热收费管理模块（基础信息（小区的管理，房型的管理，付款类型管理），年份供热价格管理，供热居民管理，住宅供热记录管理，住宅缴费记录管理，住宅供热退费管理，住宅停供记录管理）
>>>>>>> branch 'master' of git@github.com:jyu2016-by-negosan/neusoft_training_2019.git
组员|罗妙忠|
组员|吴澍淼|
组员|张晓龙|供热收费管理模块（公建年度供热费管理，修改公建供热状态，修改公司年度实际供热天数，修改公建供热状态，公建缴费记录管理，公建退费记录管理）
组员|万俊星|投诉模块、系统操作员管理模块、系统功能配置模块
****************************

### 项目参数

+ 域名：com.neusoft
+ 项目：ht
+ 项目模块(初步)
1. 登录模块：login
2. 文件模块:file
3. 通知模块:notice
4. 员工信息管理模块:security
5. 供热收费管理模块:fee
6. 投诉模块:complain
7. 系统操作员管理模块:security
8. 系统功能配置模块:security

******************************
### 模块关系整理（初步）

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
<<<<<<< HEAD
住宅供热退费记录表     |RecordNo	|HT_HomeFeeReturnRecord		|居民表(HomeNo)1:1,付款方式表(TypeNo)1:1
=======
住宅供热退费记录表     |RecordNo	|HT_HomeFeeReturnRecord		|居民表(HomeNo)1:1,付款方式表(TypeNo)1:n
>>>>>>> branch 'master' of git@github.com:jyu2016-by-negosan/neusoft_training_2019.git
住宅供热报停表	        |RecordNo		|HT_HomeStopRecord		|居民供热记录表(FeeNo)1:1
公建供热记录表	        |FeeNo		|HT_PublicHouseFee			|公建表(HouseNo)1:1, 供热年度费率表(HeatingYear)1:1
公建供热缴费记录表      |RecordNo		|HT_PublicHouseFeePayRecord		|公建供热记录表(FeeNo)1:1,付款方式表(TypeNo)1:1
公建供热退费记录	     |RecordNo	|HT_PublicHouseFeeReturnRecord	|公建表(HouseNo)1:1,付款方式表(TypeNo)1:1
公建供热报停表	        |RecordNo	|HT_PublicHouseStopRecord	|公建供热记录表(FeeNo)1:1


#### 2. 客户服务模块 对应 6

表数量：3	                           |主键		|表名				|关联的表
:-|:--|:--|:--
|投诉类型表	           |TYPENO		|HT_complainType
|住宅投诉记录表	           |ComplainNo		|HT_Homecomplain			|居民表(HomeNo)1:1，投诉类型表(TypeNO)
|公建投诉记录表	           |ComplainNo		|HT_PublicHousecomplain		|公建表(HouseNo)1:1,  投诉类型表(TypeNO)


#### 3. 系统配置模块 对应 1? 4?  7?  8

表数量：4	                           |主键		|表名				|关联的表
:-|:--|:--|:--
|操作员表	           |UUSERID		|HT_SystemUser
|系统功能表	           |FUNNO	 	|HT_Function
|系统功能权限表（操作员与功能的关联表）	        |FUNNO,UUserID	|HT_FunctionUse
|系统管理员辅助信息表       |UUSERID		|HT_UserInfo			|操作员表(UUSERID) 1:1    

