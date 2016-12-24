package org.thanatos.mvp_nomal.entity;

import org.thanatos.mvp_nomal.base.BaseEntity;

/**
 * Created by lxf52 on 2016/12/24.
 */

public class UserInfo extends BaseEntity.DataBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                '}';
    }
}
