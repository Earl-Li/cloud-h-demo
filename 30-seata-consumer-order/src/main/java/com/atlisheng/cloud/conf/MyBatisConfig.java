package com.atlisheng.cloud.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.atlisheng.cloud.mapper"})
public class MyBatisConfig {
}