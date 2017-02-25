package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.newest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 17/2/24.
 */

//          最新的适配器
//          作者:赵子文

public class NewestAdapter extends FragmentPagerAdapter {

//    定义标题栏的集合
    private List<NewestEntity.DataBean.CategoryBean> newestList;

//    通过set方法将集合传入适配器中
    public void setNewestList(List<NewestEntity.DataBean.CategoryBean> newestList) {
        this.newestList = newestList;
        notifyDataSetChanged();
    }

//    构造方法(初始化适配器)
    public NewestAdapter(FragmentManager fm) {
        super(fm);
    }

//    ????
    @Override
    public Fragment getItem(int position) {
        if (0 == position) {
            return ReuseNewestFragment.getReuseNewestFragment(0);
        } else {
            int id = newestList.get(position - 1).getId();
            return ReuseNewestFragment.getReuseNewestFragment(id);
        }

    }

//    获取集合的总数量(由于集合中没有"全部",所以几何数量+1)
    @Override
    public int getCount() {
        return newestList != null && newestList.size() + 1 > 0 ? newestList.size() + 1 : 0;
    }

//    获取对应位置的标题
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "全部";
        } else {
            return newestList.get(position - 1).getName();

        }
    }
}
