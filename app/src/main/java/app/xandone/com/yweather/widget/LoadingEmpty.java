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
 * Created by xandone on 2016/12/23.
 */
public class LoadingEmpty extends LinearLayout {
    private ImageView load_empty_noContent;
    private ProgressBar load_empty_progress;
    private TextView load_empty_tv;
    private Button load_empty_again_btn;
    private String errorMsg;
    private OnReloadListener onReloadListener;

    public LoadingEmpty(Context context) {
        this(context, null);
    }

    public LoadingEmpty(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingEmpty(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        View.inflate(context, R.layout.loading_empty, this);
        load_empty_noContent = (ImageView) findViewById(R.id.load_empty_noContent);
        load_empty_progress = (ProgressBar) findViewById(R.id.load_empty_progress);
        load_empty_tv = (TextView) findViewById(R.id.load_empty_tv);
        load_empty_again_btn = (Button) findViewById(R.id.load_empty_again_btn);
        load_empty_again_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadListener != null) {
                    onReloadListener.reload();
                }
            }
        });
        setVisibility(GONE);
    }

    public void setLoadingTip(LoadStatus loadStatus) {
        switch (loadStatus) {
            case serverError:
                load_empty_noContent.setVisibility(VISIBLE);
                load_empty_progress.setVisibility(GONE);
                load_empty_again_btn.setVisibility(VISIBLE);
                if (StringUtils.isEmpty(errorMsg)) {
                    load_empty_tv.setText("网络访问错误");
                } else {
                    load_empty_tv.setText(errorMsg);
                }
                load_empty_noContent.setImageResource(R.drawable.ic_wrong);
                break;
            case netError:
                load_empty_noContent.setVisibility(VISIBLE);
                load_empty_progress.setVisibility(GONE);
                load_empty_again_btn.setVisibility(VISIBLE);
                if (StringUtils.isEmpty(errorMsg)) {
                    load_empty_tv.setText("网络访问错误");
                } else {
                    load_empty_tv.setText(errorMsg);
                }
                load_empty_noContent.setImageResource(R.drawable.ic_net_off);
                break;
            case empty:
                load_empty_noContent.setVisibility(VISIBLE);
                load_empty_progress.setVisibility(GONE);
                load_empty_again_btn.setVisibility(VISIBLE);
                load_empty_tv.setText("网络访问错误");
                load_empty_noContent.setImageResource(R.drawable.no_content_tip);
                break;
            case loading:
                load_empty_noContent.setVisibility(GONE);
                load_empty_progress.setVisibility(VISIBLE);
                load_empty_again_btn.setVisibility(GONE);
                load_empty_tv.setText("加载中..");
                break;
            case finish:
                setVisibility(GONE);
                break;
        }
    }


    public void setEmptyText(String str) {
        if (load_empty_tv != null) {
            load_empty_tv.setText(str);
        }
    }

    public void setOnReloadListener(OnReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }

    public interface OnReloadListener {
        void reload();
    }

    public static enum LoadStatus {
        serverError, netError, empty, loading, finish
    }
}
