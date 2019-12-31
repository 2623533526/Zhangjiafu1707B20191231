package com.bawei.zhangjiafu1707b20191231.presenter;

import com.bawei.zhangjiafu1707b20191231.contract.Contract;
import com.bawei.zhangjiafu1707b20191231.entity.Entity;
import com.bawei.zhangjiafu1707b20191231.model.Model;
import com.bawei.zhangjiafu1707b20191231.mvp.BasePresenter;

/**
 * @author: 张家辅
 * @date: 2019/12/31
 */
public class Presenter extends BasePresenter<Model, Contract.IView> implements Contract.IPresenter{
    @Override
    protected Model initModel() {
        return new Model();
    }

    @Override
    public void success(String string) {
   model.success(string, new Contract.IModel.CallBack() {
       @Override
       public void success(Entity entity) {
           getView().success(entity);
       }

       @Override
       public void error(Throwable throwable) {
          getView().error(throwable);
       }
   });
    }

    @Override
    public void error(Throwable throwable) {

    }
}
