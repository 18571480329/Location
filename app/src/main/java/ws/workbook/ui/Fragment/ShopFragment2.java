package ws.workbook.ui.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import ws.workbook.R;
import ws.workbook.adapter.HomeAdapter;
import ws.workbook.adapter.MenuAdapter;
import ws.workbook.ui.view.StickyScrollView;

/**
 * 作者： 王爽
 * 日期： 2018/10/12
 * 描述：
 */

public class ShopFragment2 extends BaseFragment implements StickyScrollView.OnScrollChangedListener {

    private StickyScrollView stickyScrollView;
    private int height;
    private LinearLayout llContent;
    private Banner mBanner;
    private RelativeLayout llTitle;
    private RecyclerView menu_rv;
    private RecyclerView shop_rv;
    private TextView back;
    private TextView peri;
    private TextView share ;
    private List<String> imageUrl;
    private List<String> titleUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log2, null);
        ButterKnife.bind(this, view);
        stickyScrollView = view.findViewById(R.id.stickyScrollView);
        llContent = view.findViewById(R.id.ll_content);
        mBanner = view.findViewById(R.id.banner);
        menu_rv = view.findViewById(R.id.menu_rv);
        shop_rv = view.findViewById(R.id.shop_rv);
        llTitle = view.findViewById(R.id.ll_good_detail);
        back = view.findViewById(R.id.back);
        peri = view.findViewById(R.id.peri);
        share = view.findViewById(R.id.share);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListeners();
        initData();
    }


    private void initView() {

        titleUrl = new ArrayList<>();
        imageUrl = new ArrayList<>();

        for (int i = 0 ; i<5; i++){
            titleUrl.add("任志强不再“愤怒”：房地产市场仍大有可为");
            imageUrl.add("http://www.tanfangwang.com/uploads/20180803/b6ee2109c08b2993fdf14ea7ce0008dc.png");
        }
        //设置轮播图
        setBanner();
        MenuAdapter Menuadapter = new MenuAdapter(getContext());
        //设置布局管理器
        LinearLayoutManager Manager = new LinearLayoutManager(getContext());
        Manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        menu_rv.setLayoutManager(Manager);
        menu_rv.setAdapter(Menuadapter);

        HomeAdapter adapter = new HomeAdapter(getContext());
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shop_rv.setLayoutManager(linearLayoutManager);
        shop_rv.setAdapter(adapter);

        stickyScrollView.setOnScrollListener(this);
        StatusBarUtil.setTranslucentForImageView(getActivity(), 0, mBanner);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) llTitle.getLayoutParams();
        params.setMargins(0, getStatusHeight(), 0, 0);
        llTitle.setLayoutParams(params);

    }

    private void setBanner() {
//设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(imageUrl);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titleUrl);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }

    }

    private void initListeners() {

        ViewTreeObserver BannerVTO = mBanner.getViewTreeObserver();
        BannerVTO.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = mBanner.getHeight();
                mBanner.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        //获取title高度
        ViewTreeObserver viewTreeObserver1 = llTitle.getViewTreeObserver();
        viewTreeObserver1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = height - llTitle.getHeight() - getStatusHeight();//计算滑动的总距离
                stickyScrollView.setStickTop(llTitle.getHeight() + getStatusHeight());//设置距离多少悬浮
                //注意要移除
                llTitle.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
            }
        });

//        //获取内容总高度
//            final ViewTreeObserver vto = llContent.getViewTreeObserver();
//            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    height = llContent.getHeight();
//                    //注意要移除
//                    llContent.getViewTreeObserver()
//                            .removeGlobalOnLayoutListener(this);
//
//                }
//            });
//
//            //获取shop_rv高度
//            ViewTreeObserver viewTreeObserver = shop_rv.getViewTreeObserver();
//            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    height = height - shop_rv.getHeight();
//                    //注意要移除
//                    shop_rv.getViewTreeObserver()
//                            .removeGlobalOnLayoutListener(this);
//                }
//            });
//
//            //获取title高度
//            ViewTreeObserver viewTreeObserver1 = llTitle.getViewTreeObserver();
//            viewTreeObserver1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    height = height - llTitle.getHeight() - getStatusHeight();//计算滑动的总距离
//                    stickyScrollView.setStickTop(llTitle.getHeight() + getStatusHeight());//设置距离多少悬浮
//                    //注意要移除
//                    llTitle.getViewTreeObserver()
//                            .removeGlobalOnLayoutListener(this);
//                }
//            });
    }

    private void initData() {

    }

    /**
     * 获取状态栏高度
     * @return
     */
    private int getStatusHeight() {
        int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getResources().getDimensionPixelSize(resourceId);

    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (t <= 0) {
            llTitle.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
            back.setTextColor(Color.argb((int) 0, 255, 255, 255));
            peri.setTextColor(Color.argb((int) 0, 255, 255, 255));
            share.setTextColor(Color.argb((int) 0, 255, 255, 255));
            StatusBarUtil.setTranslucentForImageView(getActivity(), 0, mBanner);
        } else if (t > 0 && t <= height) {
            float scale = (float) t / height;
            int alpha = (int) (255 * scale);
            llTitle.setBackgroundColor(Color.argb((int) alpha, 36, 136, 198));//设置标题栏的透明度及颜色
            back.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            peri.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            share.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            StatusBarUtil.setTranslucentForImageView(getActivity(), alpha, mBanner);
        } else {
            llTitle.setBackgroundColor(Color.argb((int) 255, 36, 136, 198));
            back.setTextColor(Color.argb((int) 255, 255, 255, 255));
            peri.setTextColor(Color.argb((int) 255, 255, 255, 255));
            share.setTextColor(Color.argb((int) 255, 255, 255, 255));
            StatusBarUtil.setTranslucentForImageView(getActivity(), 255, mBanner);
        }
    }
}
