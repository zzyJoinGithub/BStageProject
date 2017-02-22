package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

//               首页fragment的适配器
public class HomePageAdapter extends FragmentPagerAdapter {

//     定义fragment集合和标题
    private List<Fragment> datas;
    private String title[] = {"最新", "精选", "订阅"};

//    构造方法(将布局管理者传入适配器)
    public HomePageAdapter(FragmentManager fm) {
        super(fm);
    }

//    通过setDatas方法将集合传入适配器
    public void setDatas(List<Fragment> datas) {
        this.datas = datas;
    }


//     获取某一位置视图
    @Override
    public Fragment getItem(int position) {
        return datas != null && datas.size() > 0 ? datas.get(position) : null;
    }

//    获取集合总数量
    @Override
    public int getCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

//    将标题相对应设置给集合的每一个fragment
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
