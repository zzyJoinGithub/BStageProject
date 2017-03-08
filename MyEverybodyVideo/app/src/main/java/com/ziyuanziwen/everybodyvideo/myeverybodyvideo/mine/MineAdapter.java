package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.mine;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.AbsBaseAdapter;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 17/3/7.
 */

public class MineAdapter extends AbsBaseAdapter<MineEntity, MineAdapter.MyHolder> {


    public MineAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_fragment_mine;
    }

    @Override
    protected MyHolder onCreateViewHolder(View view) {
        return new MyHolder(view);
    }

    @Override
    protected void onBindViewHolder(MineEntity itemContent, int i, MyHolder holder) {
        holder.titleTv.setText(itemContent.getTitle());
        ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(
                context, itemContent.getImage(), holder.image);
        holder.box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }


    class MyHolder {
        TextView titleTv;
        CircleImageView image;
        CheckBox box;

        public MyHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.fragment_mineItemTitleTv);
            image = (CircleImageView) view.findViewById(R.id.fragment_mineItemIv);
            box = (CheckBox) view.findViewById(R.id.fragment_mineItemCheckBox);
        }
    }
}
