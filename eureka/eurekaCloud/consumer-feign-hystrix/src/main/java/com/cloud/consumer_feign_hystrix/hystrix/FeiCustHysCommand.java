
package com.cloud.consumer_feign_hystrix.hystrix;


import com.alibaba.fastjson.JSONObject;
import com.cloud.consumer_feign_hystrix.feignClient.FeiClient;
import com.netflix.hystrix.*;
import org.springframework.beans.factory.annotation.Autowired;

public class FeiCustHysCommand extends HystrixCommand<JSONObject> {

	@Autowired
	private FeiClient feiClient;

	public FeiCustHysCommand(FeiClient feiClient) {
		super(setter());
		this.feiClient = feiClient;
	}

	@Override
	protected JSONObject getFallback() {

		// 如果Hystrix发生熔断，当前服务不可用,直接执行Fallback方法
		JSONObject result = new JSONObject();
		result.put("code", 500);
		result.put("msg", "系统错误！(服务降级演示)");
		return result;
	}

	@Override
	protected JSONObject run() throws Exception {
		String consumer = feiClient.consumer();

		return JSONObject.parseObject(consumer);
	}

	private static Setter setter() {

		// 服务分组
		HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("consumer3Group");
		// 服务标识
		HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("eureka-consumer3");
		// 线程池名称
		HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("client-pool");

		// 线程池配置 线程池大小为10,线程存活时间15秒 队列等待的阈值为100,超过100执行拒绝策略
		HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter().withCoreSize(10)
				.withKeepAliveTimeMinutes(15).withQueueSizeRejectionThreshold(100);

		// 命令属性配置Hystrix 开启超时
		HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
				// 采用线程池方式实现服务隔离
				.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
				// 禁止超时时间
				.withExecutionTimeoutEnabled(false);
		return HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey).andThreadPoolKey(threadPoolKey)
				.andThreadPoolPropertiesDefaults(threadPoolProperties).andCommandPropertiesDefaults(commandProperties);

	}






}
