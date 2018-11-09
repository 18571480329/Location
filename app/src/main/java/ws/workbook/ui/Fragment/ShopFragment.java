package ws.workbook.ui.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import ws.workbook.R;
import ws.workbook.Utils.HeaderAndFooterWrapper;
import ws.workbook.adapter.HomeAdapter;
import ws.workbook.ui.view.HeaderView;

/**
 * 作者： 王爽
 * 日期： 2018/10/12
 * 描述：
 */

public class ShopFragment extends BaseFragment {

    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.peri)
    TextView peri;
    @BindView(R.id.share)
    TextView share;
    @BindView(R.id.title_rl)
    RelativeLayout mTitleRl;
    private HeaderView mHeaderView;
    private int Height;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        ViewTreeObserver BannerVTO = HeaderView.mBanner.getViewTreeObserver();
        BannerVTO.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Height = HeaderView.mBanner.getHeight();
                HeaderView.mBanner.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

            //获取title高度
            ViewTreeObserver viewTreeObserver1 = mTitleRl.getViewTreeObserver();
            viewTreeObserver1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Height = Height - mTitleRl.getHeight();//计算滑动的总距离
//                    HeaderView.stickyScrollView.setStickTop(mTitleRl.getHeight()
//                            + getStatusHeight()
//                    );//设置距离多少悬浮
                    //注意要移除
                    mTitleRl.getViewTreeObserver()
                            .removeGlobalOnLayoutListener(this);
                }
            });
    }

    /**
     * 获取状态栏高度
     * @return
     */
    private int getStatusHeight() {
        int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getResources().getDimensionPixelSize(resourceId);
    }


    @SuppressLint("NewApi")
    private void initView() {

        mHeaderView = new HeaderView(getContext());

        //创建处理正常数据的adapter
        HomeAdapter adapter = new HomeAdapter(getContext());
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //创建负责处理Header和footer的adapter
        HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        headerAndFooterWrapper.addHeaderView(mHeaderView);
        mHomeRv.setLayoutManager(linearLayoutManager);
        mHomeRv.setAdapter(headerAndFooterWrapper);
        mHomeRv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //顶部是第t个条目
                int t = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                int height = Math.abs(recyclerView.getChildAt(0).getTop());
                if (t == 0 && height == 0) {
                    mTitleRl.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
                    back.setTextColor(Color.argb((int) 0, 255, 255, 255));
                    peri.setTextColor(Color.argb((int) 0, 255, 255, 255));
                    share.setTextColor(Color.argb((int) 0, 255, 255, 255));
                    StatusBarUtil.setTranslucentForImageView(getActivity(), 0, HeaderView.mBanner);
                } else if (t == 0 && Height > height) {
                    float scale =  (float)height/Height;
                    int alpha = (int) (255 * scale);
                    mTitleRl.setBackgroundColor(Color.argb((int) alpha, 36, 136, 198));//设置标题栏的透明度及颜色
                    back.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                    peri.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                    share.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                    StatusBarUtil.setTranslucentForImageView(getActivity(),alpha, HeaderView.mBanner);//设置状态栏的透明度
                } else {
                    mTitleRl.setBackgroundColor(Color.argb((int) 255, 36, 136, 198));
                    back.setTextColor(Color.argb((int) 255, 255, 255, 255));
                    peri.setTextColor(Color.argb((int) 255, 255, 255, 255));
                    share.setTextColor(Color.argb((int) 255, 255, 255, 255));
                    StatusBarUtil.setTranslucentForImageView(getActivity(), 255, HeaderView.mBanner);
                }


//                Log.e("dx,dy", ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() + "/" + recyclerView.getChildAt(0).getTop() + "");
            }
        });

    }

    private void initData() {

    }

}
