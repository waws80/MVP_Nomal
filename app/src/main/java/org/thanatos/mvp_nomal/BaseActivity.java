package org.thanatos.mvp_nomal;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public abstract class BaseActivity extends AppCompatActivity {

    private FrameLayout baseContentParent;

    private SwipeRefreshLayout baseContentSwipRFL;

    private View baseMainLoading;

    private View baseMainError;

    private View baseMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_main);
        init();
        initListener();
        onCreateView(savedInstanceState);
    }

    private void init() {
        baseMainLoading=findViewById(R.id.base_main_loading);
        baseMainError=findViewById(R.id.base_main_error);
        baseMainContent=findViewById(R.id.base_main_content);
        baseContentSwipRFL= (SwipeRefreshLayout) findViewById(R.id.base_content_swipRFL);
        baseContentParent= (FrameLayout) findViewById(R.id.base_content_parent);
    }

    private void initListener(){
        baseContentSwipRFL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                baseContentSwipRFL.setRefreshing(true);
                onRefreshData();
            }
        });
    }

    public  abstract void  onCreateView(Bundle savedInstanceState);
    public abstract void back();
    public  void onRefreshData(){}

    public void setView(int layoutId){
        baseContentParent.addView(View.inflate(this,layoutId,null));
    }

    public void hindSwipRFL(){
        if (baseContentSwipRFL.isRefreshing()){
            baseContentSwipRFL.setRefreshing(false);
        }
    }

    public void hindProgress(){
        if (baseMainLoading.getVisibility()==View.VISIBLE)
            baseMainLoading.setVisibility(View.GONE);
    }

    public void showProgress(){
        if (baseMainLoading.getVisibility()==View.GONE)
            baseMainLoading.setVisibility(View.VISIBLE);
    }

    public void hindError(){
        if (baseMainError.getVisibility()==View.VISIBLE)
            baseMainError.setVisibility(View.GONE);
    }

    public void showError(){
        if (baseMainError.getVisibility()==View.GONE)
            baseMainError.setVisibility(View.VISIBLE);
    }

    public void hindContent(){
        if (baseMainContent.getVisibility()==View.VISIBLE)
            baseMainContent.setVisibility(View.GONE);
    }

    public void showContent(){
        if (baseMainContent.getVisibility()==View.GONE)
            baseMainContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();

    }


}
