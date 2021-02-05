package com.geek.shengka.common.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程池
 * @author jiangxinqiang
 */
public class ThreadPoolManager {

	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolManager.class);
	
	private static ThreadPoolExecutor threadPoolLowTaskExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1 , Runtime.getRuntime().availableProcessors() + 6, 100, 
			TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(8000), 
			new RejectedExecutionHandler() {
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					//必须判断isShutdown，否则停止后还会继续向队列中添加任务，直至钩子等待超时
					if (!executor.isShutdown()) {
						//r.run(); //在主线程执行方案，与阻塞添加任务相比各有好处
						try {
							executor.getQueue().put(r);
						} catch (InterruptedException e) {
							logger.error("ThreadPoolExecutor error ",e);
						}
					}
				}
			});
	//注册钩子
	static {
		registerStoreUsedUtilExecutorHook();
	}
	/**
	 *注册钩子，程序启动时执行，等待线程池所有线程执行完毕再退出程序 
	 *若有新线程池，同样在此钩子内处理（不要放到另一个钩子中，可能有并发问题）
	 */
	public static void registerStoreUsedUtilExecutorHook(){
		Runtime.getRuntime().addShutdownHook( new Thread() {
			@Override
			public void run() {
				try {
					threadPoolLowTaskExecutor.shutdown();//设置线程池不再接收新任务
					threadPoolLowTaskExecutor.awaitTermination(1 * 30, TimeUnit.SECONDS);//等待所有线程执行结束，或30s超时
					logger.info("stop ThreadPoolManager end!");
				} catch (InterruptedException e) {
					logger.error("register Hook error ",e);
				}
			}
		});
		logger.info("ThreadPoolManager ExecutorHook register success!");
	}
	
	
	
	public static void addTask(Runnable task){
		threadPoolLowTaskExecutor.execute(task);
	}
}
