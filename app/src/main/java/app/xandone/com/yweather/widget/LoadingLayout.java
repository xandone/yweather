package app.xandone.com.yweather.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.utils.StringUtils;

/**
 * Created by xandone on 2017/4/6.
 */
public class LoadingLayout extends LinearLayout {
    private ImageView img_tip_logo;
    private ProgressBar progress;
    private TextView tv_tips;
    private Button bt_operate;

    private OnReloadListener onReloadListener;
    private String errorMsg;
    private int nullPic;

    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public static enum LoadStatus {
        error,
        serverError,
        empty,
        loading,
        finish
    }

    public void initView(Context context) {
        View.inflate(context, R.layout.loading_tip_layout, this);
        img_tip_logo = (ImageView) findViewById(R.id.img_tip_logo);
        progress = (ProgressBar) findViewById(R.id.progress);
        tv_tips = (TextView) findViewById(R.id.tv_tips);
        bt_operate = (Button) findViewById(R.id.bt_operate);

        setVisibility(GONE);

        bt_operate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadListener != null) {
                    onReloadListener.reLoad();
                }
            }
        });

    }

    public void setTips(String tips) {
        if (tv_tips == null) {
            return;
        }
        tv_tips.setText(tips);
    }

    public int getNullPic() {
        return nullPic;
    }

    public void setNullPic(int nullPic) {
        this.nullPic = nullPic;
    }

    public void setLoadingTips(LoadStatus loadStatus) {
        switch (loadStatus) {
            case error:
                setVisibility(VISIBLE);
                img_tip_logo.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                if (StringUtils.isEmpty(errorMsg)) {
                    tv_tips.setText("山顶洞人");
                } else {
                    tv_tips.setText(errorMsg);
                }
                img_tip_logo.setImageResource(R.drawable.icon_net_error);
                break;
            case serverError:
                setVisibility(View.VISIBLE);
                img_tip_logo.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                if (StringUtils.isEmpty(errorMsg)) {
                    tv_tips.setText("未知错误");
                } else {
                    tv_tips.setText(errorMsg);
                }
                img_tip_logo.setImageResource(R.drawable.icon_net_error);
                break;
            case empty:
                setVisibility(View.VISIBLE);
                img_tip_logo.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                tv_tips.setText("暂无数据");
                if (nullPic <= 0) {
                    img_tip_logo.setImageResource(R.drawable.icon_net_error);
                } else {
                    img_tip_logo.setImageResource(nullPic);
                }
                break;
            case loading:
                setVisibility(VISIBLE);
                img_tip_logo.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                tv_tips.setText("加载..");
                break;
            case finish:
                setVisibility(GONE);
                break;
        }
    }

    public interface OnReloadListener {
        void reLoad();
    }

    public void setOnReloadListener(OnReloadListener onReloadListener) {
        onReloadListener.reLoad();
    }

}
