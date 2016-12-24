package org.thanatos.mvp_nomal.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import org.thanatos.mvp_nomal.R;
import org.thanatos.mvp_nomal.utils.CustomVolly;

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {

    private FrameLayout baseContentParent;

    private SwipeRefreshLayout baseContentSwipRFL;

    private View baseMainError;

    private P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_main);
        mPresenter=createPresenter();
        mPresenter.attachView((V)this);
        init(savedInstanceState);
        initListener();

    }

    private void init(Bundle savedInstanceState) {
        baseMainError=findViewById(R.id.base_main_error);
        baseContentSwipRFL= (SwipeRefreshLayout) findViewById(R.id.base_content_swipRFL);
        baseContentParent= (FrameLayout) findViewById(R.id.base_content_parent);
        onCreateView(savedInstanceState);

    }

    private void initListener(){
        baseContentSwipRFL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                baseContentSwipRFL.setRefreshing(true);
                if (!CustomVolly.isNetworkAvailable())
                    CustomVolly.showToast("网络异常，稍后再试!");

               
                onRefreshData();
            }
        });
    }

    public  abstract void  onCreateView(Bundle savedInstanceState);
    public abstract void back();
    public  abstract void onRefreshData();
    public abstract P createPresenter();

    public void setView(int layoutId){
        baseContentParent.addView(View.inflate(this,layoutId,null));
    }

    public void hindSwipRFL(){
        if (baseContentSwipRFL.isRefreshing()){
            baseContentSwipRFL.setRefreshing(false);
        }
    }

    public void hindError(){
        if (baseMainError.getVisibility()==View.VISIBLE)
            baseMainError.setVisibility(View.GONE);
    }

    public void showError(){
        if (baseMainError.getVisibility()==View.GONE)
            baseMainError.setVisibility(View.VISIBLE);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.detcahView();
        mPresenter=null;
        CustomVolly.cancelRequest();
        CustomVolly.unRegister();
        Log.w("test", "onDestroy: " );
        System.exit(0);
        System.runFinalization();
    }
}
