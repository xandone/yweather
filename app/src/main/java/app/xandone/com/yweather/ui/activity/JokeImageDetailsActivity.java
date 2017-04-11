package app.xandone.com.yweather.ui.activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.JokeImageRecyclerAdapter;
import app.xandone.com.yweather.ui.base.BaseActivity;
import app.xandone.com.yweather.utils.ImageLoader;
import app.xandone.com.yweather.utils.StringUtils;
import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by xandone on 2017/1/18.
 */
public class JokeImageDetailsActivity extends BaseActivity {
    @BindView(R.id.joke_details_photoView)
    PhotoView joke_details_photoView;

    private PhotoViewAttacher mAttacher;
    private RequestManager mRequestManager;
    private String img_url;

    @Override
    public int setLayout() {
        return R.layout.activity_joke_image_details;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        img_url = getIntent().getStringExtra(JokeImageRecyclerAdapter.JOKEIMAGERECYCLERADAPTER_URL);
        if (StringUtils.isEmpty(img_url)) {
            return;
        }
        mRequestManager = Glide.with(this);
        mAttacher = new PhotoViewAttacher(joke_details_photoView);
        ImageLoader.loadImage(mRequestManager, joke_details_photoView, img_url);
        mAttacher.update();
    }
}
