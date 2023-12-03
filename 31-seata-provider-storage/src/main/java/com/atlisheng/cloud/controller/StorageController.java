package com.atlisheng.cloud.controller;

import com.atlisheng.cloud.resp.CommonResp;
import com.atlisheng.cloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public CommonResp decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResp(200,"扣减库存成功！");
    }
}