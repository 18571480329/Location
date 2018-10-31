/*
 * Copyright (C) 2016 LingDaNet.Co.Ltd. All Rights Reserved.
 */
package ws.workbook;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

/**
 * 描述：基础Activity，有头部的Activity都可以继承。头部分为左中右三部分，左和右均为图片，中间部分为文字描述。
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener {

    private static final int RIGHT_IV_RESID_NULL = -1;
    private static final int RIGHT_TXT_RESID_NULL = -1;
    private static final int TITLE_RESID_NULL = -1;

    //头部左边图标
    private ImageView mHeadLeftIv;
    //头部中间文字
    public TextView mHeadTitleTv;
    //头部右边图标
    private ImageView mHeadRightIv;
    //头部右侧文字
    public TextView mHeaderRightTv;
    //标题栏布局
    protected View mHeaderLayout;
    //activity主布局
    private FrameLayout mMainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        // mTintManager = new SystemBarTintManager(this);
        // mTintManager.setStatusBarTintEnabled(true);
        // mTintManager.setStatusBarTintResource(R.color.app_theme_color);
        // StatusBarCompat.compat(this, getResources().getColor(R.color.app_theme_color));
        StatusBarUtil.setColor(this, getResources().getColor(R.color.app_theme_color), 0);
        initBaseView();
    }

    /**
     * 初始化对应的标题栏控件
     */
    private void initBaseView() {
        //左边图标id
        mHeadLeftIv = (ImageView) findViewById(R.id.head_left_iv);
        //头部标题文字id
        mHeadTitleTv = (TextView) findViewById(R.id.title_tv);
        //左边图标id
        mHeadRightIv = (ImageView) findViewById(R.id.head_right_iv);
        mHeaderRightTv = (TextView) findViewById(R.id.head_right_tv);
        mHeadLeftIv.setOnClickListener(this);
        mHeadRightIv.setOnClickListener(this);
        mHeaderRightTv.setOnClickListener(this);
        mHeaderLayout = findViewById(R.id.header_layout);
        mMainLayout = (FrameLayout) findViewById(R.id.main_fl);
    }

    @Override
    public void setContentView(int layoutResID) {
        View mainView = LayoutInflater.from(this).inflate(layoutResID, null);
        mMainLayout.addView(mainView);
    }

    /**
     * 初始化头部图标或文字
     *
     * @param isLeftVisable  左边图标是否显示，true为显示，false为不显示
     * @param title          头部标题文字
     * @param isRightVisable 左边图标是否显示，true为显示，false为不显示
     */
    public void initHead(Boolean isLeftVisable, String title, Boolean isRightVisable) {
        //如果true,左边图标显示，否则不显示
        if (isLeftVisable) {
            mHeadLeftIv.setVisibility(View.VISIBLE);
        } else {
            mHeadLeftIv.setVisibility(View.GONE);
        }
        //设置头部标题文字
        mHeadTitleTv.setText(title);
        //如果true,右边图标显示，否则不显示
        if (isRightVisable) {
            mHeadRightIv.setVisibility(View.VISIBLE);
        } else {
            mHeadRightIv.setVisibility(View.GONE);
        }
        mHeaderLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_left_iv:
                finish();
                break;
        }
    }

    /**
     * 设置标题栏的名称
     *
     * @param titleResId resId
     */
    protected void setHeader(int titleResId) {
        setHeader(false, titleResId, RIGHT_IV_RESID_NULL, RIGHT_TXT_RESID_NULL);
    }

    /**
     * 设置标题栏的名称
     *
     * @param title String
     */
    protected void setHeader(String title) {
        setHeader(false, title, RIGHT_IV_RESID_NULL, null);
    }

    /**
     * 设置带有返回按钮的标题栏
     *
     * @param titleResId resId
     */
    protected void setHeaderWithBack(int titleResId) {
        setHeader(true, titleResId, RIGHT_IV_RESID_NULL, RIGHT_TXT_RESID_NULL);
    }

    /**
     * 设置带有返回按钮的标题栏
     *
     * @param title String
     */
    protected void setHeaderWithBack(String title) {
        setHeader(true, title, RIGHT_IV_RESID_NULL, null);
    }

    /**
     * 设置带有返回按钮与右侧文字按钮的标题栏
     *
     * @param titleResId titleResId
     * @param rightResId rightResId
     */
    protected void setHeaderWithBackAndRightText(int titleResId, int rightResId) {
        setHeader(true, titleResId, RIGHT_IV_RESID_NULL, rightResId);
    }

    /**
     * 设置带有返回按钮与右侧文字按钮的标题栏
     *
     * @param title 标题文字
     * @param right 右侧文字
     */
    protected void setHeaderWithBackAndRightText(String title, String right) {
        setHeader(true, title, RIGHT_IV_RESID_NULL, right);
    }

    /**
     * 设置带有返回按钮与右侧图标按钮的标题栏
     *
     * @param titleResId 标题文字
     * @param rightResId 右侧文字
     */
    protected void setHeaderWithBackAndRightImg(int titleResId, int rightResId) {
        setHeader(true, titleResId, rightResId, RIGHT_TXT_RESID_NULL);
    }

    /**
     * 设置标题栏信息
     *
     * @param hasLeft       是否存在返回按钮
     * @param titleResId    标题
     * @param rightIvResId  右侧按钮图标的resId
     * @param rightTxtResId 右侧文字按钮的resId
     */
    protected void setHeader(boolean hasLeft, int titleResId, int rightIvResId, int rightTxtResId) {
        String titleResStr = null;
        String rightTxtStr = null;
        if (TITLE_RESID_NULL != titleResId) {
            titleResStr = getResources().getString(titleResId);
        }
        if (RIGHT_TXT_RESID_NULL != rightTxtResId) {
            rightTxtStr = getResources().getString(rightTxtResId);
        }
        setHeader(hasLeft, titleResStr, rightIvResId, rightTxtStr);
    }

    /**
     * 设置标题栏信息
     *
     * @param hasLeft      是否存在返回按钮
     * @param titleResId   标题
     * @param rightIvResId 右侧按钮图标的resId
     * @param rightTxt     右侧文字按钮的resId
     */
    protected void setHeader(boolean hasLeft, String titleResId, int rightIvResId, String rightTxt) {
        if (hasLeft) {
            mHeadLeftIv.setVisibility(View.VISIBLE);
        } else {
            mHeadLeftIv.setVisibility(View.GONE);
        }
        mHeadTitleTv.setText(titleResId);
        if (rightIvResId == -1) {
            mHeadRightIv.setVisibility(View.GONE);
        } else {
            mHeadRightIv.setVisibility(View.VISIBLE);
            mHeadRightIv.setImageResource(rightIvResId);
        }
        if (TextUtils.isEmpty(rightTxt)) {
            mHeaderRightTv.setVisibility(View.GONE);
        } else {
            mHeaderRightTv.setVisibility(View.VISIBLE);
            mHeaderRightTv.setText(rightTxt);
        }
        mHeaderLayout.setVisibility(View.VISIBLE);
    }

    protected void setHeaderVisible(int visible) {
        mHeaderLayout.setVisibility(visible);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}

