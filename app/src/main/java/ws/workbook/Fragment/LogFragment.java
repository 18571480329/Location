package ws.workbook.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ws.workbook.R;
import ws.workbook.adapter.WorkRvAdapter;
import ws.workbook.bean.WorkBean;

/**
 * 作者： 王爽
 * 日期： 2018/10/12
 * 描述：
 */

public class LogFragment extends BaseFragment {

    @BindView(R.id.document_rv)
    RecyclerView mDocumentRv;
    @BindView(R.id.work_rv)
    RecyclerView mWorkRv;
    @BindView(R.id.project_rv)
    RecyclerView mProjectRv;

    private WorkRvAdapter mWorkAdapter;
    private WorkRvAdapter mDocumentAdapter;
    private WorkRvAdapter mProjectAdapter;
    private List<WorkBean> mPerformanceWorkList = new ArrayList<>();
    private List<WorkBean> mDocumentWorkList = new ArrayList<>();
    private List<WorkBean> mProjectWorkList = new ArrayList<>();




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
        initView(view);
        initData();
    }


    private void initView(View view) {
    }

    private void initData() {
        for (int i = 0; i <= 6; i++) {
            mPerformanceWorkList.add(new WorkBean());
        }
        for (int i = 0; i <= 2; i++) {
            mDocumentWorkList.add(new WorkBean());
        }
        for (int i = 0; i <= 1; i++) {
            mProjectWorkList.add(new WorkBean());
        }

        mPerformanceWorkList.get(0).setImageId(R.drawable.ic_day_report);
        mPerformanceWorkList.get(0).setTitle(getString(R.string.day_report));
        mPerformanceWorkList.get(1).setImageId(R.drawable.ic_week_report);
        mPerformanceWorkList.get(1).setTitle(getString(R.string.week_report));
        mPerformanceWorkList.get(2).setImageId(R.drawable.ic_month_report);
        mPerformanceWorkList.get(2).setTitle(getString(R.string.month_report));
        mPerformanceWorkList.get(3).setImageId(R.drawable.ic_leader_journey);
        mPerformanceWorkList.get(3).setTitle(getString(R.string.leader_journey));
        mPerformanceWorkList.get(4).setImageId(R.drawable.ic_view_day);
        mPerformanceWorkList.get(4).setTitle(getString(R.string.view_day_report));
        mPerformanceWorkList.get(5).setImageId(R.drawable.ic_view_week);
        mPerformanceWorkList.get(5).setTitle(getString(R.string.view_week_report));
        mPerformanceWorkList.get(6).setImageId(R.drawable.ic_view_month);
        mPerformanceWorkList.get(6).setTitle(getString(R.string.view_month_report));
        mDocumentWorkList.get(0).setImageId(R.drawable.ic_receive);
        mDocumentWorkList.get(0).setTitle(getString(R.string.receive_report));
        mDocumentWorkList.get(1).setImageId(R.drawable.ic_send);
        mDocumentWorkList.get(1).setTitle(getString(R.string.send_report));
        mDocumentWorkList.get(2).setImageId(R.drawable.ic_view_send);
        mDocumentWorkList.get(2).setTitle(getString(R.string.check_send));
        mProjectWorkList.get(0).setImageId(R.drawable.ic_project_search);
        mProjectWorkList.get(0).setTitle(getString(R.string.project_search));
        mProjectWorkList.get(1).setImageId(R.drawable.ic_project_dynamic);
        mProjectWorkList.get(1).setTitle(getString(R.string.project_dynamic));

        mWorkAdapter = new WorkRvAdapter(R.layout.item_work_check, mPerformanceWorkList);
        mDocumentAdapter = new WorkRvAdapter(R.layout.item_work_check, mDocumentWorkList);
        mProjectAdapter = new WorkRvAdapter(R.layout.item_work_check, mProjectWorkList);



        mWorkRv.setNestedScrollingEnabled(false);
        mWorkRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mWorkRv.setHasFixedSize(true);
        mWorkRv.setAdapter(mWorkAdapter);
        mWorkAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mDocumentRv.setNestedScrollingEnabled(false);
        mDocumentRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mDocumentRv.setHasFixedSize(true);
        mDocumentRv.setAdapter(mDocumentAdapter);
        mDocumentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mProjectRv.setNestedScrollingEnabled(false);
        mProjectRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mProjectRv.setHasFixedSize(true);
        mProjectRv.setAdapter(mProjectAdapter);
        mProjectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });


    }


}
