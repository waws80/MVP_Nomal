package org.thanatos.mvp_nomal.base;

/**
 * Created by lxf52 on 2016/12/23.
 */

public interface IBaseModel<E > {



    void getData(OnLoadDataListener<E> loadDataListener);
    interface OnLoadDataListener<E  >{
        void complate(E  e);
        void failer();
    }
}
