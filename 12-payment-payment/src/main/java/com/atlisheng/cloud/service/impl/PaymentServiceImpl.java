package com.atlisheng.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atlisheng.cloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    /**
     * @param id
     * @return {@link String }
     * @描述 正常访问肯定ok的方法
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/29
     * @since 1.0.0
     */
    @Override
    public String paymentInfoOk(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+"paymentInfoOk,id:"+id;
    }

    /**
     * @param id
     * @return {@link String }
     * @描述 访问一定会报错并导致服务降级的方法
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/29
     * @since 1.0.0
     */
    @Override
    @HystrixCommand(fallbackMethod="paymentInfoTimeoutHandler",commandProperties = {
            //execution.isolation.thread.timeoutInMilliseconds是设置目标方法的调用超时时间,单位是毫秒
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfoTimeout(Integer id) {
        //int sleepTime=4;
        int sleepTime=1;
        //int age=10/0;
        try{
            TimeUnit.SECONDS.sleep(sleepTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池:"+Thread.currentThread().getName()+",paymentInfoTimeout,id:"+id+",超时"+sleepTime+"秒钟";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            //这些属性可以去HystrixCommandProperties类里面看,没配置准备了默认值，配置了就注入用户配置的
            //是否开启断路器
            @HystrixProperty(name="circuitBreaker.enabled",value="true"),
            //时间窗口期内的请求次数阈值，默认是20个，超过该阈值断路器工作，threshold是阈值的意思
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),
            //熔断时间窗口期，单位毫秒，这里表示10s钟，意思是熔断多少秒后开始尝试恢复，默认为5s，在这个窗口期内尝试单个请求是否正常，如果请求失败，则断路器维持断开状态，请求成功再讲断路器置于close状态，
            // 即正常放行请求，这里暂时理解成窗口期和熔断多长时间后尝试恢复的时间长度是一样的，但是是两个不同的时间段，
            //查看博文了解到，hystrix用于统计的时间窗口默认是10s，对应一个十个桶的滑动窗口，每个桶负责记录1秒内的成功量、失败量、超时量和拒绝量；拒绝量指信息号/线程池资源检查中被拒绝的请求数量，计算
            // 10s内即一个滑动窗口的数据需要将十个桶的数据相加，多个桶是放在AtomicReferenceArray<Bucket>中维护的，为了让窗口滑动起来，数组大小默认是11，确定的1s内只有一个桶的数据被更新，其他桶的数据没有变化，
            // 这个时间和sleepWindowInMilliseconds不同，sleepWindowInMilliseconds确实是熔断后多长时间再尝试恢复
            //博文地址：https://www.cnblogs.com/frankcui/p/14461258.html
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),
            //失败率达到多少后跳闸，默认是50%，这里是失败率达到百分之六十，即一段时间内请求调用服务降级的百分比超过60%，就直接熔断,这里的统计要看
            // 源码才行，如果这个失败率取默认的前10s，但是前十秒都没有请求，突然来一个失败的服务降级如果只是简单的考虑十秒内失败率，那么失败率100%，
            // 就会直接导致熔断，但是事实上这种情况没有发生
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("id不能为负数!!!");
        }
        //糊涂包下的IdUtil工具类，相当于UUID.RandomUUID().toString().replace('_',"");,获取随机UUID并去掉下划线
        //Hutool是一个Java工具包类库，对文件，流，加密解密，转码、正则、线程、XML等JDK方法进行封装，组成各种Util工具类
        //官网：hutool.cn,涉及内容包括日期工具、HTTP客户端、类型转换、配置文件工具、日志工具、JDBC工具类
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"正常调用,流水号:"+serialNumber+",id:"+id;
    }
    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id){
        return "id不能为负数,请稍后再试,id:"+id;
    }

    /**
     * @param id
     * @return {@link String }
     * @描述 这个方法的返回结果就会作为原方法的返回结果
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/30
     * @since 1.0.0
     */
    public String paymentInfoTimeoutHandler(Integer id){
        return "线程池:"+Thread.currentThread().getName()+",支付服务调用方法超时兜底，参数id会由原方法自动传递进来:"+id;
    }
}
