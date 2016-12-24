package org.thanatos.mvp_nomal.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.thanatos.mvp_nomal.R;
import org.thanatos.mvp_nomal.adapter.MyAdapter;
import org.thanatos.mvp_nomal.base.BaseActivity;
import org.thanatos.mvp_nomal.base.BaseEntity;
import org.thanatos.mvp_nomal.presenter.PresenterMainActivity;
import org.thanatos.mvp_nomal.utils.LoadingDialog;
import org.thanatos.mvp_nomal.view.IMainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity<IMainActivity,PresenterMainActivity> implements IMainActivity{
    @InjectView(R.id.activity_main_lv)
    TextView tv;
    @InjectView(R.id.lv)
    ListView lv;

    private LoadingDialog dialog;



    @Override
    public void onCreateView(Bundle savedInstanceState) {
        setView(R.layout.activity_main);
        ButterKnife.inject(this);
        dialog=new LoadingDialog(this);
        PresenterMainActivity presenterMainActivity=new PresenterMainActivity(MainActivity.this);
        presenterMainActivity.getDate();


    }

    @Override
    public void onRefreshData() {
        createPresenter().getDate();
    }

    @Override
    public PresenterMainActivity createPresenter() {
        return new PresenterMainActivity(this);
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void hindDialog() {
       dialog.dismiss();
    }

    @Override
    public void complate(BaseEntity s) {
        hindSwipRFL();
        hindError();
        tv.setText(s.toString());
        lv.setAdapter(new MyAdapter(this,s.getData()));
    }

    @Override
    public void failer() {
        hindSwipRFL();
        Log.w("thanatos", "failer: " );
        showError();
    }
}
