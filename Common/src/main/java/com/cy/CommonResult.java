package com.cy;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cy
 * @program: WuYeManagementProgram
 * @description:
 * @date 2022-01-05 08:44:18
 */
@Data
@NoArgsConstructor
public class CommonResult<T>
{

    /** 返回成功失败标示 */
    private int code;

    /** 返回成功失败信息 */
    private String msg;

    /** 返回成功失败附加数据 */
    private T data;



    public CommonResult(int code)
    {
        super();
        this.code = code;
    }

    public CommonResult(int code, String msg)
    {
        super();
        this.code = code;
        this.msg = msg;
    }

    public CommonResult(int code, String msg, T data)
    {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> CommonResult<T> success()
    {
        return new CommonResult<>(ResultStatus.SUCCESS,"请求成功");
    }

    public static <T> CommonResult<T> success(String msg)
    {
        return new CommonResult<>(ResultStatus.SUCCESS, msg);
    }

    public static <T> CommonResult<T> success(int code,String msg)
    {
        return new CommonResult<>(code, msg);
    }

    public static <T> CommonResult<T> success(String msg,T data)
    {
        return new CommonResult<>(ResultStatus.SUCCESS, msg,data);
    }


    public static <T> CommonResult<T> success(int code,String msg,T data)
    {
        return new CommonResult<>(code, msg, data);
    }


    public static <T> CommonResult<T> error()
    {
        return new CommonResult<>(ResultStatus.FAIL,"请求失败");
    }

    public static <T> CommonResult<T> error(String msg)
    {
        return new CommonResult<>(ResultStatus.FAIL, msg);
    }

    public static <T> CommonResult<T> error(int code, String msg)
    {
        return new CommonResult<>(code, msg);
    }

    public static <T> CommonResult<T> error(String msg, T data)
    {
        return new CommonResult<>(ResultStatus.FAIL, msg, data);
    }

    public static <T> CommonResult<T> error(int code, String msg, T data)
    {
        return new CommonResult<>(code, msg, data);
    }



    public static class ResultStatus
    {
        public static final int SUCCESS = 200;

        public static final int FAIL = 500;
    }

}
