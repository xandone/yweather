package app.xandone.com.yweather.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import app.xandone.com.yweather.utils.Utils;


public class GlideListView extends RelativeLayout {

    private Context mContext;
    private Scroller mScroller;

    public GlideListView(Context context) {
        this(context, null);
    }

    public GlideListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GlideListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        Interpolator interpolator = new BounceInterpolator();
        mScroller = new Scroller(mContext, interpolator);
    }

    public void yScroll(int duration) {
        mScroller.startScroll(0, Utils.getWH()[1], 0, -Utils.getWH()[1], duration);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            // 获取当前的位置Y坐标，执行滑动，直到停止
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
