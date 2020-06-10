package com.atguigu.guli.service.base.exception;

import com.atguigu.guli.common.base.result.ResultCodeEnum;
import lombok.Data;

/**
 * 项目自定义异常一般都要继承运行时异常
 */
@Data
public class CustomException extends RuntimeException{

    private Integer code;

    public CustomException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public CustomException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "code=" + code +
                ",message=" + this.getMessage() +
                '}';
    }
}
