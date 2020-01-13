package com.sdjzu.sell.exception;

import com.sdjzu.sell.enums.ResultEnum;

/**
 * @author lychee
 * @date 2020/1/11 21:22
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = code;
    }
}
