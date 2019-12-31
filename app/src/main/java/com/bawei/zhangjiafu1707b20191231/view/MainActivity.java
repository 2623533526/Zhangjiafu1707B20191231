package com.bawei.zhangjiafu1707b20191231.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.bawei.zhangjiafu1707b20191231.Main2Activity;
import com.bawei.zhangjiafu1707b20191231.R;
import com.bawei.zhangjiafu1707b20191231.adapter.MyRecycler;
import com.bawei.zhangjiafu1707b20191231.base.BaseActivity;
import com.bawei.zhangjiafu1707b20191231.entity.Entity;
import com.bawei.zhangjiafu1707b20191231.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<Presenter> {
   @BindView(R.id.rv)
    RecyclerView rv;

 @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {
     presenter.success("http://blog.zhaoliang5156.cn/api/news/ranking.json");
    }

    @Override
    protected void initView() {
     rv.setLayoutManager(new StaggeredGridLayoutManager(1,RecyclerView.VERTICAL));
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_main;
    }

    @Override
    public void success(Entity entity) {
     MyRecycler myRecycler = new MyRecycler(MainActivity.this, entity.getRanking());
     rv.setAdapter(myRecycler);
    }

    @Override
    public void error(Throwable throwable) {

    }
   // ii.（核心）点击图1中的红色文字“点击这里分享给朋友”跳转到图2，使用自己的名字生成二维码并展示
    @OnClick(R.id.share)
    public void share(){
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
}
