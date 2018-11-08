package ws.workbook.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ws.workbook.LocationApplication;
import ws.workbook.R;
import ws.workbook.service.LocationService;
import ws.workbook.ui.Fragment.CardFragment;
import ws.workbook.ui.Fragment.ShopFragment;
import ws.workbook.ui.Fragment.MainFragment;
import ws.workbook.ui.Fragment.MyFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_content_fl)
    FrameLayout mMainContentFl;
    @BindView(R.id.main_navigation)
    BottomNavigationBar mMainNavigation;
    private Fragment mCurrentFragment;
    MainFragment mMainFragment;
    MyFragment mMyFragment;
    CardFragment mCardFragment;
    ShopFragment mLogFragment;
    List<Fragment> mFragments;         //声明List集合用来存放Fragment

    private LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        LocationPermission();
    }

    private void initView() {
        mMainNavigation
                .addItem(new BottomNavigationItem(R.drawable.home, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_card, "打卡"))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_work, "商品"))
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
        mLogFragment = new ShopFragment();
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

    private void LocationPermission() {
        //如果没有ACCESS_COARSE_LOCATION权限，动态请求用户允许使用该权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            requestLocation();
        }
    }

    private void requestLocation() {
        locationService = ((LocationApplication) getApplication()).locationService;
        //获取locationService实例
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();// 定位SDK
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {  //危险权限
                    requestLocation();
                } else {
                    Toast.makeText(this, "没有授予定位权限！", Toast.LENGTH_LONG).show();
                    finish();
                }
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    /**
     * 定位结果回调，重写onReceiveLocation方法
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuffer sb = new StringBuffer(256);
            sb.append("时间 : ");
            sb.append(location.getTime());
            sb.append("\n纬度 : ");
            sb.append(location.getLatitude());
            sb.append("\n经度 : ");
            sb.append(location.getLongitude());
            sb.append("\n地址信息 : ");
            sb.append(location.getAddrStr());
            sb.append("\n室内外判断结果: ");
            sb.append(location.getUserIndoorState());
            sb.append("\n方向");
            sb.append(location.getDirection());
            sb.append("\n周围建筑: ");
            sb.append(location.getLocationDescribe());
//            mLoginResetpswTv.setText(sb.toString());
        }
    };


}
