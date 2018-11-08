package ws.workbook.ui.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import ws.workbook.R;
import ws.workbook.adapter.MenuAdapter;

/**
 * 作者： 王爽
 * 日期： 2018/11/7
 * 描述：头布局
 */

public class HeaderView extends LinearLayout {
    private Context mContext;
    private Banner mBanner;
    private RecyclerView mMenuRv;
    private List<String> imageUrl;
    private List<String> titleUrl;

    public HeaderView(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.header_layout, this, true);
        mBanner = view.findViewById(R.id.banner);
        mMenuRv = view.findViewById(R.id.menu_rv);
        this.mContext = context;
        initView();
        initData();
    }



    private void initView() {
        titleUrl = new ArrayList<>();
        imageUrl = new ArrayList<>();

        for (int i = 0 ; i<5; i++){
            titleUrl.add("任志强不再“愤怒”：房地产市场仍大有可为");
            imageUrl.add("http://www.tanfangwang.com/uploads/20180803/b6ee2109c08b2993fdf14ea7ce0008dc.png");
        }

    }

    private void initData() {
        //设置轮播图
        setBanner();
        //初始化adapter
        MenuAdapter adapter = new MenuAdapter(getContext());
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mMenuRv.setLayoutManager(linearLayoutManager);
        mMenuRv.setAdapter(adapter);

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

}
