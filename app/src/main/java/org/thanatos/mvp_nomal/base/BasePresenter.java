package org.thanatos.mvp_nomal.base;

import java.lang.ref.WeakReference;

/**
 * Created by lxf52 on 2016/12/24.
 */

public class BasePresenter<V> {

    private WeakReference <V> reference;
    private V view;

     void attachView(V context){
        reference=new WeakReference<V>(context);
        view=reference.get();
    }

     void detcahView(){
        if (view!=null)
        view=null;
        reference.clear();
        reference=null;
    }
}
