package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.mine;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.AbsBaseAdapter;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.db.SQTool;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.LogTool;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 17/3/7.
 */

public class MineAdapter extends AbsBaseAdapter<MineEntity, MineAdapter.MyHolder> {

    private boolean flage = false;
//    private Map<Integer, Boolean> isCheck;

    public MineAdapter(Context context) {
        super(context);
//        isCheck = new HashMap<>();
    }

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_fragment_mine;
    }

    @Override
    public void setDatas(List<MineEntity> datas) {
        super.setDatas(datas);
//        for (int i = 0; i < datas.size(); i++) {
//            isCheck.put(i,false);
//        }
//        notifyDataSetChanged();
    }

    public void deleteData() {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isClicked()) {
                SQTool.getInstance().deleteData(datas.get(i).getTitle());
                datas.remove(i);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    protected MyHolder onCreateViewHolder(View view) {
        return new MyHolder(view);
    }

    @Override
    protected void onBindViewHolder(MineEntity itemContent, final int i, final MyHolder holder) {

        holder.titleTv.setText(itemContent.getTitle());
        ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(
                context, itemContent.getImage(), holder.image);
        if (flage) {
            holder.box.setVisibility(View.VISIBLE);
            LogTool.logI("MineAdapter", "显示");
            holder.box.setChecked(datas.get(i).isClicked());
            holder.box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//               if (isCheck.get(i)){
//                   isCheck.put(i, false);
//                   holder.box.setChecked();
//               }else {
//                   isCheck.put(i, true);
//               }
                    CheckBox box = (CheckBox) view;
                    boolean isCheck = box.isChecked();
                    datas.get(i).setClicked(isCheck);
                }
            });
        } else {
            holder.box.setVisibility(View.INVISIBLE);
            LogTool.logI("MineAdapter", "不显示");
        }


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
