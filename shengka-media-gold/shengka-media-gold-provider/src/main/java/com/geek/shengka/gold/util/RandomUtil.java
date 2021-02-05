package com.geek.shengka.gold.util;

import java.util.Random;

public class RandomUtil {
    public static final Random  RANDOM = new Random();
    public static int getRandom(int x, int y) {
		int num = -1;
		//说明：两个数在合法范围内，并不限制输入的数哪个更大一些	
		if(x<0||y<0) {
			return num;
		}else {
			int max = x>y?x:y;
			int min = x<y?x:y;
			int mid = max -min;//求差
			//产生随机数
			num = min+RANDOM.nextInt(mid+1);
		}
		return num;
	}

}
