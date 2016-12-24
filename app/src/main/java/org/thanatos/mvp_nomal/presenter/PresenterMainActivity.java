package org.thanatos.mvp_nomal.presenter;

import org.thanatos.mvp_nomal.base.BaseEntity;
import org.thanatos.mvp_nomal.base.BasePresenter;
import org.thanatos.mvp_nomal.base.IBaseModel;
import org.thanatos.mvp_nomal.model.DataModelImpl;
import org.thanatos.mvp_nomal.view.IMainActivity;

/**
 * Created by lxf52 on 2016/12/24.
 */

public class PresenterMainActivity extends BasePresenter<IMainActivity> {
    private IMainActivity activity;
    private IBaseModel<BaseEntity> model;

    public PresenterMainActivity(IMainActivity activity) {
        this.activity = activity;
    }

    public void getDate(){
        activity.showDialog();
        model=new DataModelImpl();
        model.getData(new IBaseModel.OnLoadDataListener<BaseEntity>() {
            @Override
            public void complate(BaseEntity s) {
                activity.hindDialog();
                activity.complate(s);

            }

            @Override
            public void failer() {
                activity.hindDialog();
                activity.failer();

            }
        });
    }
}
