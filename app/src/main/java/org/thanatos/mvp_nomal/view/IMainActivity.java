package org.thanatos.mvp_nomal.view;

import org.thanatos.mvp_nomal.base.BaseEntity;
import org.thanatos.mvp_nomal.entity.UserInfo;

/**
 * Created by lxf52 on 2016/12/24.
 */

public interface IMainActivity {

    void showDialog();
    void hindDialog();
    void complate(BaseEntity<UserInfo> s);
    void failer();
}
