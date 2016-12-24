package org.thanatos.mvp_nomal.base;

import java.util.List;

/**
 * Created by lxf52 on 2016/12/23.
 */

public class BaseEntity<B extends BaseEntity.DataBean> {

    private String code;
    private String message;
    private List<B> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<B> getData() {
        return data;
    }

    public void setData(List<B> bList) {
        this.data = bList;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static abstract class DataBean{

    }
}
