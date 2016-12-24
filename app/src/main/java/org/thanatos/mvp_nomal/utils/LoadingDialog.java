package org.thanatos.mvp_nomal.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import org.thanatos.mvp_nomal.R;

/**
 * Created by lxf52 on 2016/10/31.
 */
public class LoadingDialog extends ProgressDialog {
    public LoadingDialog(Context context) {
        this(context, R.style.loading_dialog);
        setCanceledOnTouchOutside(false);
    }

    private LoadingDialog(Context context, int theme) {
        super(context, theme);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_loading);
    }


}
