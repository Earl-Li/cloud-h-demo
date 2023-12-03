package com.atlisheng.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atlisheng.cloud.entities.Payment;
import com.atlisheng.cloud.handler.CustomerBlockHandler;
import com.atlisheng.cloud.resp.CommonResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
public class RateLimitController {
    /**
     * @return {@link CommonResp }
     * @描述 按照@SentinelResource注解的value属性配置流控规则，使用自定义降级方法
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/11/22
     * @since 1.0.0
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResp byResource() {
        return new CommonResp(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }

    public CommonResp handleException(BlockException exception) {
        return new CommonResp(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResp byUrl() {
        return new CommonResp(200, "按url限流测试OK", new Payment(2020L, "serial002"));
    }

    /**
     * @return {@link CommonResp }
     * @描述 自定义通用的限流处理逻辑
     * 自定义通用的限流处理逻辑，
     *        blockHandlerClass = CustomerBlockHandler.class
     *        blockHandler = handleException
     *       上述配置：找CustomerBlockHandler类里的handleException方法进行兜底处理
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/11/22
     * @since 1.0.0
     */
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handleException")
    public CommonResp customerBlockHandler() {
        return new CommonResp(200, "按客户自定义限流处理逻辑");
    }


}
