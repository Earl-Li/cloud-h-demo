package com.atlisheng.cloud.client;

import com.atlisheng.cloud.resp.CommonResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-provider-storage")
public interface StorageClient {

    /**
     * 扣减库存
     */
    @PostMapping(value = "/storage/decrease")
    CommonResp decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}