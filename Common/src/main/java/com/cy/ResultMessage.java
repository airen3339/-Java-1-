package com.cy;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cy
 * @program: newWuye
 * @description: json数据封装
 * @date 2021-12-22 22:19:07
 */
@Data
public class ResultMessage {
    //状态码   200-成功    500-失败   600 - no_LOGIN    700 - no_Auth
    private int code;
    //提示信息
    private String msg;

    //用户要返回给浏览器的数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public static ResultMessage success(){
        ResultMessage result = new ResultMessage();
        result.setCode(200);
        result.setMsg("数据处理成功！");
        return result;
    }

    public static ResultMessage success(String msg){
        ResultMessage result = new ResultMessage();
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    public static ResultMessage fail(){
        ResultMessage result = new ResultMessage();
        result.setCode(500);
        result.setMsg("服务器处理请求失败");
        return result;
    }

    public static ResultMessage fail(String msg){
        ResultMessage result = new ResultMessage();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    public static ResultMessage fail(int code , String msg){
        ResultMessage result = new ResultMessage();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public ResultMessage add(String key,Object value){
        this.getData().put(key,value);
        return this;
    }
}
