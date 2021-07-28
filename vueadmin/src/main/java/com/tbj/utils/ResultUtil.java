package com.tbj.utils;


import com.tbj.entity.Result;



public class ResultUtil {


    public static Result sucResult(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }
}
