package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by dllo on 17/3/7.
 * ListView的基类
 */
//     传入两个泛型(数据, viewHolder)
public abstract class AbsBaseAdapter<s, VH> extends BaseAdapter {

//    定义context权限设置为protected 可以让子类使用
    protected Context context;
    protected List<s> datas;

    public AbsBaseAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<s> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    @Override
    public s getItem(int i) {
        return datas != null && datas.size() > 0 ? datas.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        VH holder = null;
        if (null == view){
            view = LayoutInflater.from(context).inflate(getItemLayout(), viewGroup, false);
            holder = onCreateViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (VH) view.getTag();
        }
//        通过getItem(i)方法获取对应位置
        s itemContent = getItem(i);

        onBindViewHolder(itemContent, i, holder);
        return view;
    }

    protected abstract int getItemLayout();

    protected abstract VH onCreateViewHolder(View view);

    protected abstract void onBindViewHolder(s itemContent, int i, VH holder);
}
