package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;

/**
 * Created by dllo on 2017/2/22-下午4:53.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 * Fragment基类的创建
 */

public abstract class BaseFragment extends Fragment {

    //基类中的Context,子类可以直接使用
    protected Context mContext;

    //初始化基类中的Context
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    //引入视图
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * 封装简单的标题栏步骤
         * 1.给基类绑定一个大的布局，在这个布局中包裹标题栏
         * 2.将getLayout方法获得的每个子类的布局加入到大的布局中
         */
        // 定制界面流程
        /**
         * 不使用toolbar作为fragment基类的标题栏是因为toolBar在不同
         * fragment的切换中导致toolBar重叠显示(原因是toolBar的设置需要getActivity...什么的
         * 反正就是根据Activity设置的[Activity还必须是AppCompatActivity的子类才行],又由于
         * Fragment依存于Activity,Activity为同一个的时候,会导致重叠显示)
         */

        // 1.绑定跟布局
        View rootView = inflater.inflate(R.layout.fragment_base_rootlayout, container, false);

        LinearLayout rootLayout = (LinearLayout) rootView.findViewById(R.id.baseFragment_rootlayout);

        // 2.处理子界面的布局
        View childView = inflater.inflate(getLayout(), container, false);
        // 3.将子界面加入根布局
        rootLayout.addView(childView);

//        titleLayout = (RelativeLayout) rootView.findViewById(R.id.title_layout);
//        backIv = (ImageView) rootView.findViewById(R.id.title_back_iv);
//        titleTv = (TextView) rootView.findViewById(R.id.title_middle_tv);
//        rightIv = (ImageView) rootView.findViewById(R.id.title_right_iv);
//        backIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        /* 4.处理标题栏 组件初始化*/
        RelativeLayout titleLayout = byView(rootView, R.id.title_rootlayout);
        ImageView backIv = byView(rootView, R.id.title_rootlayout_backIv);
        TextView titleTv = byView(rootView, R.id.title_rootlayout_middleTv);
        ImageView rightIv = byView(rootView, R.id.title_rootlayout_rightIv);


        initTitle(titleLayout, backIv, titleTv, rightIv);
        return rootView;
    }

    //子类复写(重写) 引入视图 的方法
    protected abstract int getLayout();

    //子类复写(重写) 设置标题栏的方法
    protected abstract void initTitle(RelativeLayout titleLayout, ImageView backIv, TextView titleTv, ImageView rightIv);


    //绑定组件初始化组件
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化当前页面的组件
        initView(view);
    }

    //子类复写(重写) 绑定组件初始化组件 的方法
    protected abstract void initView(View view);

    //初始化数据 逻辑代码等
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据 逻辑代码等
        initData();
    }

    //子类复写(重写) 初始化数据 逻辑代码等 的方法
    protected abstract void initData();

    /**
     * findViewById精简 (组件)
     *
     * @param view  组件所在视图
     * @param resId 组件id
     * @param <T>   组件的泛型
     * @return 返回组件的findviewbyid
     */
    protected <T extends View> T byView(View view, int resId) {
        return (T) view.findViewById(resId);
    }

    //显示toast的方法
    protected void showToast(String info){
        Toast.makeText(mContext, info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 跳转
     *
     * @param to 要跳转到的Activity
     */
    protected void goTo(Class<? extends BaseActivity> to) {
        Intent intent = new Intent();
        intent.setClass(mContext, to);
        mContext.startActivity(intent);
//        overridePendingTransition(R.anim.in, R.anim.out);//切换动画 待完善
    }

    /**
     * 跳转传String
     *
     * @param to         要跳转到的Activity
     * @param key        跳转传值的key值
     * @param extraValue 跳转传String类型的值
     */
    protected void goTo(Class<? extends BaseActivity> to, String key, String extraValue) {
        Intent intent = new Intent(mContext, to);
        intent.putExtra(key, extraValue);
        startActivity(intent);
    }

    /**
     * 跳转传String
     *
     * @param to         要跳转到的Activity
     * @param key        跳转传值的key值
     * @param extraValue 跳转传int类型的值
     */
    protected void goTo(Class<? extends BaseActivity> to, String key, int extraValue) {
        Intent intent = new Intent(mContext, to);
        intent.putExtra(key, extraValue);
        mContext.startActivity(intent);
    }


    /**
     * 传不确定个数和类型的对象
     *
     * @param to     要跳转到的Activity
     * @param bundle 由于要传的值是不确定个数和类型 所以需要借助bundle进行传递
     */
    protected void goTo(Class<? extends BaseActivity> to, Bundle bundle) {
        Intent intent = new Intent(mContext, to);
        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }
}
