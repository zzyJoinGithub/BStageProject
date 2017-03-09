package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.mine;

import android.view.View;
import android.widget.Button;
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

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private ListView listView;
    private MineAdapter adapter;
    private Button editBtn;
    private Button deleteBtn;
    private List<MineEntity> data;

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
        listView = byView(view, R.id.fragment_mineLv);
        editBtn = byView(view, R.id.fragment_mineEditBtn);
        deleteBtn = byView(view, R.id.fragment_mineDeleteBtn);
        editBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        adapter = new MineAdapter(getContext());
//       没有添加数据
        data = SQTool.getInstance().queryAllData();
        adapter.setDatas(data);
        listView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_mineEditBtn:
                if (adapter.isFlage()) {
                    editBtn.setText("编辑");
                    adapter.setFlage(false);
                } else {
                    editBtn.setText("取消");
                    adapter.setFlage(true);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.fragment_mineDeleteBtn:
               adapter.deleteData();

                break;
        }
    }
}
