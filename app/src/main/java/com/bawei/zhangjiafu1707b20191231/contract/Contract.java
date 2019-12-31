package com.bawei.zhangjiafu1707b20191231.contract;

import com.bawei.zhangjiafu1707b20191231.entity.Entity;
import com.bawei.zhangjiafu1707b20191231.mvp.IBaseModel;
import com.bawei.zhangjiafu1707b20191231.mvp.IBaseView;

/**
 * @author: 张家辅
 * @date: 2019/12/31
 */
public class Contract {
    //使用契约统一管理MVP
  public   interface IView extends IBaseView {
        void success(Entity entity);
        void error(Throwable throwable);
    }
    public   interface IModel extends IBaseModel {
        void success(String string,CallBack callBack);
        void error(Throwable throwable);
        public interface CallBack{
            void success(Entity entity);
            void error(Throwable throwable);
        }
    }
    public   interface IPresenter{
        void success(String string);
        void error(Throwable throwable);
    }
}
