package com.bawei.zhangjiafu1707b20191231.model;

import android.util.Log;

import com.bawei.zhangjiafu1707b20191231.contract.Contract;
import com.bawei.zhangjiafu1707b20191231.entity.Entity;
import com.bawei.zhangjiafu1707b20191231.utlis.OkHttpUtlis;
import com.google.gson.Gson;

/**
 * @author: 张家辅
 * @date: 2019/12/31
 */
public class Model implements Contract.IModel {
    @Override
    public void success(String string, CallBack callBack) {
        OkHttpUtlis.getInstance().doGet(string, new OkHttpUtlis.CallBack() {
            @Override
            public void success(String string) {
                Entity entity = new Gson().fromJson(string, Entity.class);
                callBack.success(entity);
            }

            @Override
            public void error(Throwable throwable) {
            callBack.error(throwable);
            }
        });
    }

    @Override
    public void error(Throwable throwable) {

    }
}
