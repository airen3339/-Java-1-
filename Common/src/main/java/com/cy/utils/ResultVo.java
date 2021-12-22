package com.cy.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cy
 * @program: newWuye
 * @description: 封装返回信息
 * @date 2021-12-22 18:27:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  ResultVo<T> {
    private String msg;
    private int code;
    private T data;
}
