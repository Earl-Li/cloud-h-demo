package com.atlisheng.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 流控
 * @创建日期 2023/11/18
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sentinel")
@Slf4j
public class FlowLimitController {

    @GetMapping("/test1")
    public String test1() {
        log.info(Thread.currentThread()+" | "+new Date()+"/test1");
        return "------test1";
    }

    @GetMapping("/test2")
    public String test2() {
        /*try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        return "------test2";
    }

    @GetMapping("/test3")
    public String test3() {
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("test3 测试RT");
        return "------test3";
    }

    @GetMapping("/test4")
    public String test4() {
        int i=10/0;
        log.info("test4 测试RT");
        return "------test4";
    }

    @GetMapping("/test5")
    public String test5()
    {
        log.info("test5 测试分钟异常数");
        int age = 10/0;
        return "------test5 测试分钟异常数";
    }

    /**
     * @param p1
     * @param p2
     * @return {@link String }
     * @描述 不满足该控制器的sentinel热点规则熔断以后调用自定义的服务降级方法dealHandler_testHotKey
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/11/21
     * @since 1.0.0
     */
    @GetMapping("/testHotKey")
    // value是随意的，一般取RestFul的URI斜杠后面的内容；这个value会在sentinel控制台作为控制器方法的下级簇点，
    // blockHandler指定对应的热点降级的方法名，sentinel中配置的资源名是@SentinelResource注解的
    //只要热部署重启就噶，需要完全关闭并等待sentinel控制台刷新重新配置才有效，否则热点降级规则有效，但是降级方法找不着
    @SentinelResource(value = "testHotKey",blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "------testHotKey";
    }
    public String dealHandler_testHotKey(String p1,String p2,BlockException exception) {
        return "-----dealHandler_testHotKey";
    }


    @GetMapping("/test6")
    @SentinelResource(value = "test6", blockHandler = "dealHandler_test6")
    public String test6(@RequestParam(value = "p1",required = false) String p1,
                        @RequestParam(value = "p2",required = false) String p2){
        //int a=10/0;
        log.info(p1);
        return "----test6";
    }

    public String dealHandler_test6(String p1, String p2, BlockException exception) {
        return "-----dealHandler_test6";
    }


}