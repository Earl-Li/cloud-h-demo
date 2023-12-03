package com.atlisheng.lbrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Earl
 * @version 1.0.0
 * @描述  自定义规则
 * @创建日期 2023/10/26
 * @since 1.0.0
 */
@Configuration
public class CustomizationRule {
    /**
     * @return {@link IRule }
     * @描述 将负载均衡配置为轮询
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/26
     * @since 1.0.0
     */
    @Bean
    public IRule loadBalanceRule(){
        return new RandomRule();
    }
}
