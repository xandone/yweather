package app.xandone.com.yweather.ui.fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.JokeImageRecyclerAdapter;
import app.xandone.com.yweather.ui.base.BaseFragment;
import app.xandone.com.yweather.utils.ImageLoader;
import app.xandone.com.yweather.utils.StringUtils;
import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by xandone on 2017/4/26.
 */

public class JokePagerFragment extends BaseFragment {
    @BindView(R.id.joke_pager_photoView)
    PhotoView joke_pager_photoView;

    private PhotoViewAttacher mAttacher;
    private RequestManager mRequestManager;
    private String img_url;

    @Override
    protected int setLayout() {
        return R.layout.frag_joke_pager;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            img_url = getArguments().getString(JokeImageRecyclerAdapter.JOKEIMAGERECYCLERADAPTER_URL);
        }
        if (StringUtils.isEmpty(img_url)) {
            return;
        }
        mRequestManager = Glide.with(this);
        mAttacher = new PhotoViewAttacher(joke_pager_photoView);
        ImageLoader.loadImage(mRequestManager, joke_pager_photoView, img_url);
        mAttacher.update();

    }
}
