package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.customize;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by dell on 2017/2/22.
 */

public class RecommandGridView extends GridView {
    public RecommandGridView(Context context) {
        super(context);
    }

    public RecommandGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecommandGridView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
