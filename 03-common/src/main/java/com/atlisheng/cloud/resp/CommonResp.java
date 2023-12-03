package com.atlisheng.cloud.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 通用返回类
 * @创建日期 2023/10/20
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResp<T> {
    //实际上响应码用枚举更加符合规范
    private Integer code;
    private String message;
    private T data;

    /**
     * @param code
     * @param message
     * @return
     * @描述 有参构造，还可以设置方法返回this达到链式变成的效果
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/20
     * @since 1.0.0
     */
    public CommonResp(Integer code, String message){
        this(code,message,null);
    }
}
