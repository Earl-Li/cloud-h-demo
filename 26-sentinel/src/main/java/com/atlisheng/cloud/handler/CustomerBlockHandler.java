package com.atlisheng.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atlisheng.cloud.resp.CommonResp;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 sentinel的全局限流服务自定义降级处理类，名字是自定义的
 * @创建日期 2023/11/22
 * @since 1.0.0
 */
public class CustomerBlockHandler {
    public static CommonResp handleException(BlockException exception) {
        return new CommonResp(2020, "自定义的限流处理信息......CustomerBlockHandler");
    }
}
