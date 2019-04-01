package com.feri.resource.vo;

/**
 *@Author feri
 *@Date Created in 2019/2/19 10:24
 */
public class ResultVo {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    public static ResultVo exec(boolean istrue, String msg, Object data){
        ResultVo resultVo=new ResultVo();
        if(istrue){
            resultVo.setCode(1);
        }else {
            resultVo.setCode(101);
        }
        resultVo.setMsg(msg);
        resultVo.setData(data);
        return resultVo;
    }

}
