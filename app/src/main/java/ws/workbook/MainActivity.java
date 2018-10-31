package ws.workbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ws.workbook.Fragment.CardFragment;
import ws.workbook.Fragment.LogFragment;
import ws.workbook.Fragment.MainFragment;
import ws.workbook.Fragment.MyFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_content_fl)
    FrameLayout mMainContentFl;
    @BindView(R.id.main_navigation)
    BottomNavigationBar mMainNavigation;
    private Fragment mCurrentFragment;
    MainFragment mMainFragment;
    MyFragment mMyFragment;
    CardFragment mCardFragment;
    LogFragment mLogFragment;
    List<Fragment> mFragments;          //声明List集合用来存放Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        mMainNavigation
                .addItem(new BottomNavigationItem(R.drawable.ic_main_work, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_card, "打卡"))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_log, "日志"))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_mine, "我的"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor(R.color.app_theme_color)
                .setInActiveColor(R.color.gray_666_color)
                .initialise();
        mMainNavigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        switchFragment(0);
                        break;
                    case 1:
                        switchFragment(1);
                        break;
                    case 2:
                        switchFragment(2);
                        break;
                    case 3:
                        switchFragment(3);
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    private void initData() {
        mMainFragment = new MainFragment();
        mMyFragment = new MyFragment();
        mCardFragment = new CardFragment();
        mLogFragment = new LogFragment();
        //初始化List集合
        mFragments = new ArrayList<>();
        //将首页，打卡，日志，我的 加入到list集合
        mFragments.add(mMainFragment);
        mFragments.add(mCardFragment);
        mFragments.add(mLogFragment);
        mFragments.add(mMyFragment);
        addFragment(mMainFragment);
        mCurrentFragment = mMainFragment;
        switchFragment(0);
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content_fl, fragment)
                .commit();
    }

    private void switchFragment(int index) {
        //根据id找到对应的fragment
        Fragment fragment = mFragments.get(index);
        if (fragment != mCurrentFragment) {
            if (fragment.isAdded()) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mCurrentFragment)
                        .show(fragment)
                        .commit();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mCurrentFragment)
                        .add(R.id.main_content_fl, fragment)
                        .commit();
            }
            mCurrentFragment = fragment;
        }

    }

}
