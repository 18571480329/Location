package ws.workbook.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

import butterknife.ButterKnife;
import ws.workbook.R;
import ws.workbook.service.LocationService;

/**
 * 作者： 王爽
 * 日期： 2018/10/12
 * 描述：打卡定位页面
 */

public class CardFragment extends BaseFragment {

    private LocationService locationService;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        locationService = getApplication().locationService;

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

    @Override
    public void onStop() {
        super.onStop();
    }
}
