package ws.workbook.ui.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ws.workbook.R;
import ws.workbook.Utils.FileUtils;

/**
 * 作者： 王爽
 * 日期： 2018/10/12
 * 描述：
 */

public class MyFragment extends BaseFragment {


    @BindView(R.id.userName_tv)
    TextView mUserNameTv;
    @BindView(R.id.cache_tv)
    TextView mCacheTv;
    @BindView(R.id.logout_rl)
    RelativeLayout mLogoutRl;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }


    private void initView(View view) {

    }

    private void initData() {
        mUserNameTv.setText("姓名:");
    }

    /**
     * 注销dialog提示
     */
    private void showLogoutDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage("确定要退出吗？");
        dialog.setCancelable(true);
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialog.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }

                });
        dialog.show();

    }

    /**
     * 注销dialog提示
     */
    private void showClearDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage("确定要清除所有缓存吗？");
        dialog.setCancelable(true);
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialog.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        FileUtils.clearAllCache(mActivity);
                        try {
                            mCacheTv.setText(FileUtils.getTotalCacheSize(mActivity));
                        } catch (Exception ignored) {
                            mCacheTv.setText("未知");
                        }
                    }

                });
        dialog.show();

    }


    @OnClick({R.id.logout_rl, R.id.about_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_rl:
                showLogoutDialog();
                break;
            case R.id.about_rl:
                break;
        }
    }


    @OnClick(R.id.clear_rl)
    public void onClick() {
        showClearDialog();
    }
}
