package com.atlisheng.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class LogGatewayFilter implements GlobalFilter, Ordered {

    /**
     * @param exchange 看做封装了请求、响应的参数
     * @param chain
     * @return {@link Mono }<{@link Void }> 这个就像springMVC中的ModelAndView
     * @描述 过滤器方法，判断请求是否带请求参数username，且参数值不能为null，否则认为是非法用户
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/11/03
     * @since 1.0.0
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("######LogGatewayFilter过滤器请求校验:"+new Date());
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if(username==null){
            log.error("=======非法用户名，用户名为null");
            //对应状态码是406，表示该请求不被服务器接受，前端页面显示网页无法正常运作，请与网站所有者联系
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //将响应的状态设置为完成，让请求返回给客户端，服务器不受理
            return exchange.getResponse().setComplete();
        }
        //类似于javaweb中的过滤链放行
        return chain.filter(exchange);
    }

    /**
     * @return int
     * @描述 这个参数决定过滤器在过滤器链中的位置，数字越小优先级越高，最大的是21亿，最小是-21亿，
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/11/03
     * @since 1.0.0
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
