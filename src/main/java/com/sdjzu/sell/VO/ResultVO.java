package com.sdjzu.sell.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * @author lychee
 * @date 2020/1/10 16:53
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     *具体信息
     */
    private T data;

}
