package com.bawei.zhangjiafu1707b20191231.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.zhangjiafu1707b20191231.contract.Contract;
import com.bawei.zhangjiafu1707b20191231.mvp.BasePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: 张家辅
 * @date: 2019/12/31
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements Contract.IView {
  public   P presenter;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutid());
        bind = ButterKnife.bind(this);
        presenter=initPresenter();
        presenter.Attach(this);
        initView();
        initData();
    }

    protected abstract P initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int layoutid();

    //ii.（核心）封装Activity基类，在基类中封装初始化P层的方法，并在基类中解决MVP内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.Detach();
        }
        if(bind!=null){
            bind.unbind();
        }
    }
}
