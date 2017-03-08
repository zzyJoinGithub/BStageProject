package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.db.SQTool;

import java.util.List;

/**
 * Created by dllo on 17/3/7.
 */

public class MineFragment extends BaseFragment {

    private ListView listView;
    private MineAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initTitle(RelativeLayout titleLayout, ImageView backIv, TextView titleTv, ImageView rightIv) {
        titleLayout.setVisibility(View.GONE);
    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.fragment_mineLv);
    }

    @Override
    protected void initData() {

        adapter = new MineAdapter(getContext());
//       没有添加数据
        List<MineEntity> data = SQTool.getInstance().queryAllData();
        adapter.setDatas(data);
        listView.setAdapter(adapter);
    }
}
