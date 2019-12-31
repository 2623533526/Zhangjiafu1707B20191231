package com.bawei.zhangjiafu1707b20191231;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.zhangjiafu1707b20191231.base.BaseActivity;
import com.bawei.zhangjiafu1707b20191231.entity.Entity;
import com.bawei.zhangjiafu1707b20191231.presenter.Presenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class Main2Activity extends BaseActivity<Presenter> {
    @BindView(R.id.code)
    ImageView code;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }
    //iii.（核心）长按图2中的二维码，识别二维码并吐司识别的信息
     @OnLongClick(R.id.code)
    public void oncode(){
        CodeUtils.analyzeByImageView(code, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(Main2Activity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(Main2Activity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });
     }
    @Override
    protected void initData() {
        //ii.（核心）点击图1中的红色文字“点击这里分享给朋友”跳转到图2，使用自己的名字生成二维码并展示
        Bitmap bitmap = CodeUtils.createImage("张家辅", 300, 300, null);
        code.setImageBitmap(bitmap);
    }

    @Override
    protected void initView() {
        //在Activity中注册ButterKnife绑定控件和事件
        ButterKnife.bind(this);
        //在Activity创建的时候注册EventBus
        EventBus.getDefault().register(this);
        CodeUtils.init(this);
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_main2;
    }

    @Override
    public void success(Entity entity) {

    }

    @Override
    public void error(Throwable throwable) {

    }

    //点击图2中的微信的时候发送EventBus吐司微信，点击QQ的时候发送EventBus吐司QQ
     @OnClick(R.id.weixin)
     public void weixin(){
         EventBus.getDefault().postSticky("微信");
     }

    @OnClick(R.id.qq)
    public void qq(){
        EventBus.getDefault().postSticky("QQ");
    }

     @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
     public void sub(String s){
         Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
     }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在Activity销毁的时候注销注册
        EventBus.getDefault().unregister(this);
    }
}
