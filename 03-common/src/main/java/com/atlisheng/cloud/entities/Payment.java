package com.atlisheng.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 主实体：对应数据库中的payment表
 * 实现Serializable接口在分布式部署中可能用的上
 * @创建日期 2023/10/20
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    /**
     *主键，使用Long对应数据库表中的BIGINT
     */
    private Long id;
    /**
     *
     */
    private String serial;
}
