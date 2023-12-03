package com.atlisheng.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    /**
     * 配置了一个id为route-name的路由规则，
     * 当访问地址 http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     * 这里转发是到http://news.baidu.com/guonei，也应该显示网关地址，但是由于百度的协议换成https，会自动被重定向成https的网址，
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder)
    {
        RouteLocatorBuilder.Builder routes = builder.routes();
        //把第22行的build去掉。22行的route只是负责add进List里面的。 整这两个build容易把人弄糊涂。
        //routes.route("news_guonei", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        //routes.route("news_guonei", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"));不写/guonei也可以,会自动在后面加，
        // 发现一样了就不会加，这里有问题如果uri加了该后缀会直接显示重定向
        // 既可以用多次route转发至外部链接，也可以注入多个RouteLocator
        // 如果转发过去的地址已经变更，如http://news.baidu.com/guonei变更为https://news.baidu.com/guonei,被目标服务器自身重定向，那么也将会显示重定向的效果
        //routes.route("news", r -> r.path("/guonei").uri("https://news.baidu.com.guonei"));
        routes.route("news", r -> r.path("/guonei").uri("https://news.baidu.com"));
        routes.route("gzw", r -> r.path("/index.html").uri("http://www.sasac.gov.cn"));
        routes.route("baidu", r -> r.path("/").uri("https://www.baidu.com"));
        return routes.build();
    }
    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder)
    {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("news_guoji", r -> r.path("/guoji").uri("http://news.baidu.com/guoji"));
        return routes.build();
    }

}
