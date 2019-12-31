package com.bawei.zhangjiafu1707b20191231.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.zhangjiafu1707b20191231.R;
import com.bawei.zhangjiafu1707b20191231.contract.Contract;
import com.bawei.zhangjiafu1707b20191231.entity.Entity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 张家辅
 * @date: 2019/12/31
 */
public class MyRecycler extends RecyclerView.Adapter<MyRecycler.MyHoder> {
    Context context;
    List<Entity.RankingBean> list;

    public MyRecycler(Context context, List<Entity.RankingBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_layout, null);
        MyHoder myHoder = new MyHoder(inflate);
        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHoder holder, int position) {
        Entity.RankingBean rankingBean = list.get(position);
        holder.lay_name.setText(rankingBean.getName());
        holder.lay_rank.setText(rankingBean.getRank());
        //使用Glide圆形图片展示排行头像，并配置占位图和错误图
        Glide.with(context).load(rankingBean.getAvatar())
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .circleCrop()
                .into(holder.lay_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHoder extends RecyclerView.ViewHolder {

       @BindView(R.id.lay_image)
       ImageView lay_image;
       @BindView(R.id.lay_name)
       TextView lay_name;
       @BindView(R.id.lay_rank)
       TextView lay_rank;
       public MyHoder(@NonNull View itemView) {
           super(itemView);
           //ii.在Adapter中注册ButterKnife绑定控件
           ButterKnife.bind(this,itemView);
       }
   }
}
