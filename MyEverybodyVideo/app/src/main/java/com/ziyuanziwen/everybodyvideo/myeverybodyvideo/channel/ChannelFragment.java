package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.channel;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseApplication;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.LogTool;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.ToastTool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * created by Zzy
 * 频道界面 目前准备制作地图定位
 */
public class ChannelFragment extends BaseFragment implements View.OnClickListener {

    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient;
    //声明定位回调监听器
    private AMapLocationListener mAMapLocationListener;
    //    public AMapLocationListener mLocationListener;
    //声明AMapLocationClientOption对象
    private AMapLocationClientOption mLocationOption;
    private TextView showAddressTv;
    private Handler handler;

    public static ChannelFragment newInstance() {

        Bundle args = new Bundle();

        ChannelFragment fragment = new ChannelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_channel;
    }

    @Override
    protected void initTitle(RelativeLayout titleLayout, ImageView backIv, TextView titleTv, ImageView rightIv) {
        titleTv.setText("地图界面");
    }

    @Override
    protected void initView(View view) {
        showAddressTv = byView(view, R.id.channelFrag_showAddress);
        //初始化定位
        mLocationClient = new AMapLocationClient(BaseApplication.getContext());
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
    }

    @Override
    protected void initData() {
        showAddressTv.setOnClickListener(this);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (0 == msg.what) {
                    String address = (String) msg.obj;
                    showAddressTv.setText(address);
                }
                return false;
            }
        });
    }

    private void startGetArddess() {
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，
        // 启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，
        // 反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(5000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否强制刷新WIFI，默认为true，强制刷新。但是消耗电量
        mLocationOption.setWifiActiveScan(false);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(true);
        //设置定位请求超时时间，默认为30秒。
        // 单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(50000);
        //设置是否开启定位缓存机制
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);

        //设置定位回调监听
        //异步获取定位结果
        //解析定位结果
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        //获取定位时间
                        String messages = "";
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        messages += df.format(date);
                        messages += amapLocation.getCountry();//国家信息
                        messages += amapLocation.getProvince();//省信息
                        messages += amapLocation.getCity();//城市信息
                        messages += amapLocation.getDistrict();//城区信息
                        messages += amapLocation.getStreet();//街道信息
                        messages += amapLocation.getStreetNum();//街道门牌号信息
                        messages += amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                        messages += amapLocation.getFloor();//获取当前室内定位的楼层
                        ToastTool.showDebugToast(mContext, messages);
                        Message msg = Message.obtain();
                        msg.what = 0;
                        msg.obj = messages;
                        handler.sendMessage(msg);
                        LogTool.logI("AmapError", "是否获取到地址" + messages);
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        LogTool.logI("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        });
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.channelFrag_showAddress:
                startGetArddess();
                break;
            case R.id.channelFrag_stopGetAddress:
                mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}