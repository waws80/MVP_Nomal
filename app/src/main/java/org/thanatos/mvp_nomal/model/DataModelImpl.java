package org.thanatos.mvp_nomal.model;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.thanatos.mvp_nomal.base.BaseEntity;
import org.thanatos.mvp_nomal.base.IBaseModel;
import org.thanatos.mvp_nomal.utils.CustomVolly;

import java.util.HashMap;

/**
 * Created by lxf52 on 2016/12/24.
 */

public class DataModelImpl implements IBaseModel<BaseEntity> {
    @Override
    public void getData(final OnLoadDataListener<BaseEntity> loadDataListener) {
//        CustomVolly.getText(Request.Method.GET, "https://raw.githubusercontent.com/waws80/waws80.github.io/master/json", new HashMap<String, String>(), new CustomVolly.OnLoadingListener<String>() {
//            @Override
//            public void success(String s) {
//                loadDataListener.complate(s);
//            }
//
//            @Override
//            public void error(VolleyError e) {
//                loadDataListener.failer();
//            }
//        });

        CustomVolly.getEntity(Request.Method.GET, "https://raw.githubusercontent.com/waws80/waws80.github.io/master/one", null, new HashMap<String, String>(),
                new CustomVolly.OnLoadingListener<BaseEntity>() {
                    @Override
                    public void success(BaseEntity s) {
                        loadDataListener.complate(s);
                    }

                    @Override
                    public void error(VolleyError e) {
                        loadDataListener.failer();
                    }
                });


    }
}
