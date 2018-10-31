package ws.workbook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ws.workbook.service.LocationService;

/**
 * 作者： 王爽
 * 日期： 2018/10/9
 * 描述：登录页面
 */

public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener, TextWatcher,
        CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.username_iv)
    ImageView mUsernameIv;
    @BindView(R.id.username_et)
    EditText mUsernameEt;
    @BindView(R.id.clear_iv)
    ImageView mClearIv;
    @BindView(R.id.password_iv)
    ImageView mPasswordIv;
    @BindView(R.id.password_et)
    EditText mPasswordEt;
    @BindView(R.id.show_password_chk)
    CheckBox mShowPasswordChk;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.login_regist_tv)
    TextView mLoginRegistTv;
    @BindView(R.id.login_resetpsw_tv)
    TextView mLoginResetpswTv;
    @BindView(R.id.background_ll)
    LinearLayout mBackgroundLl;

    private LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();

        //如果没有ACCESS_COARSE_LOCATION权限，动态请求用户允许使用该权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            requestLocation();
        }

    }

    private void initView() {
//        StatusBarUtil.setTransparent(this);
        mUsernameEt.setOnFocusChangeListener(this);
        mPasswordEt.setOnFocusChangeListener(this);
        mUsernameEt.addTextChangedListener(this);
        mPasswordEt.addTextChangedListener(this);
        mShowPasswordChk.setOnCheckedChangeListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String phoneStr = mUsernameEt.getText().toString();
        String pswStr = mPasswordEt.getText().toString();
        mLoginBtn.setEnabled(phoneStr.length() > 0 && pswStr.length() > 0);
        int visible = phoneStr.length() > 0 ? View.VISIBLE : View.INVISIBLE;
        mClearIv.setVisibility(visible);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int i = v.getId();
        if (i == R.id.username_et) {
            int resId = hasFocus ? R.drawable.phone_focus : R.drawable.phone_normal;
            mUsernameIv.setImageResource(resId);

        } else if (i == R.id.password_et) {
            int pswId = hasFocus ? R.drawable.psw_focus : R.drawable.psw_normal;
            mPasswordIv.setImageResource(pswId);

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int i = buttonView.getId();
        if (i == R.id.show_password_chk) {
            TransformationMethod method = isChecked ? HideReturnsTransformationMethod.getInstance()
                    : PasswordTransformationMethod.getInstance();
            mPasswordEt.setTransformationMethod(method);
            mPasswordEt.setSelection(mPasswordEt.getText().toString().length());
        }
    }

    @OnClick({R.id.clear_iv, R.id.login_btn, R.id.login_regist_tv, R.id.login_resetpsw_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_iv:
                mUsernameEt.setText("");
                break;
            case R.id.login_btn:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_regist_tv:
                break;
            case R.id.login_resetpsw_tv:
                break;
        }
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

    private void requestLocation() {
        locationService = ((LocationApplication) getApplication()).locationService;
        //获取locationService实例
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();// 定位SDK
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
            mLoginResetpswTv.setText(sb.toString());
        }
    };


}

