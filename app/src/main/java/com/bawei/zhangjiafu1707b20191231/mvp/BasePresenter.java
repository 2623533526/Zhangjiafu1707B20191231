package com.bawei.zhangjiafu1707b20191231.mvp;

import java.lang.ref.WeakReference;

/**
 * @author: 张家辅
 * @date: 2019/12/31
 */
public abstract class BasePresenter<M extends IBaseModel,V extends IBaseView> {
    public M model;
    private WeakReference<V> weakReference;

    //iii.（核心）项目采用MVP架构，分包明确
    public BasePresenter() {
        model=initModel();
    }
    public void Attach(V v){
        weakReference=new WeakReference<V>(v);
    }
    public void Detach(){
        if(weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }
    public V getView(){
        return weakReference.get();
    }

    protected abstract M initModel();
}
