package ws.workbook.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private HeaderView mHeaderView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }


    private void initView() {

        StatusBarUtil.setTranslucent(getActivity(), 0);

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

    }

    private void initData() {

    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        null.unbind();
//    }
}
