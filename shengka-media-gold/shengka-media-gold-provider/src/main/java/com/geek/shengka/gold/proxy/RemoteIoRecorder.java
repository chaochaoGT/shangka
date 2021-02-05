package com.geek.shengka.gold.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * 第三方系统交互历史记录
 * @author jiangxinqiang
 */
public abstract class RemoteIoRecorder {
	private static final Logger logger = LoggerFactory.getLogger(RemoteIoRecorder.class);
	
	private Set<Integer> finishCodes = new HashSet<Integer>();
	{
		finishCodes.add(0);//正常结束 
		finishCodes.add(-200010);  // -200010, "重复交易:当前单号订单已完成
		finishCodes.add(-200009);  // 当前账户的状态无效
		finishCodes.add(-200031);  // 当前账户的状态无效
		
		finishCodes.add(100005);  // 
		
		finishCodes.add(-200004);  //-200004 已达到每日提现最大次数  已达到每日提现最大次数
		finishCodes.add(-200001); //-200001根据userId,bizCode无法查询出对应的账户
		finishCodes.add(-200003); //-200003 当前账户的状态无效
		finishCodes.add(-200005); //-200005 当前账户可用额度不足
		finishCodes.add(-200008); //-200008, "交易金额不能小于等于零")
		finishCodes.add(-200012); //-200012, "收款账号为空"
		finishCodes.add(-200017); //(-200017, "账务中心交易类型查询异常
		finishCodes.add(-200022); //-200022, "查询不出对应的账户
		
		finishCodes.add(-300004); //-300004,"提现审核状态非法"
		
		finishCodes.add(-300001); //-300001,"提现审核失败，请重新操作"
		
		finishCodes.add(-300007); //-300007,\"请勿重复操作\"
		
		finishCodes.add(-300008); //-300008,"记录已经回滚，请勿重复操作
		finishCodes.add(-300009); //-300009,"兑换金额不能小于0.01
		finishCodes.add(-300010); //-300010,"兑换金额小于0"
		
		finishCodes.add(-200024); //-200024,\"用户身份证校验不通过\"
		
		finishCodes.add(-200025); //-200025,\"用户手机号码校验不通过\"
		
		finishCodes.add(-200026); //-200026, \"根据条件查不到所对应的账户\
		
		finishCodes.add(-200023); //-200023, \"用户未绑定身份证\"
		
	 
	}
	
	
	protected boolean hasFinished(int code) {
		return finishCodes.contains(code);
	}

}
