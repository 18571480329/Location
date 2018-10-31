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

public class MainFragment extends BaseFragment {

    @BindView(R.id.document_rv)
    RecyclerView mDocumentRv;
    @BindView(R.id.project_rv)
    RecyclerView mProjectRv;
    @BindView(R.id.performance_rv)
    RecyclerView mPerformanceRv;
    @BindView(R.id.work_rv)
    RecyclerView mWorkRv;
    @BindView(R.id.department_rv)
    RecyclerView mDepartmentRv;
    @BindView(R.id.other_rv)
    RecyclerView mOtherRv;

    private WorkRvAdapter mPerformanceAdapter;

    private WorkRvAdapter mWorkAdapter;
    private WorkRvAdapter mDocumentAdapter;
    private WorkRvAdapter mProjectAdapter;
    private WorkRvAdapter mDepartmentAdapter;
    private WorkRvAdapter mOtherAdapter;
    private List<WorkBean> mCheckWorkList = new ArrayList<>();
    private List<WorkBean> mPerformanceWorkList = new ArrayList<>();
    private List<WorkBean> mDocumentWorkList = new ArrayList<>();
    private List<WorkBean> mProjectWorkList = new ArrayList<>();
    private List<WorkBean> mDepartmentWorkList = new ArrayList<>();
    private List<WorkBean> mOtherWorkList = new ArrayList<>();

    private String pkId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
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
        for (int i = 0; i <= 4; i++) {
            mCheckWorkList.add(new WorkBean());
        }
        for (int i = 0; i <= 6; i++) {
            mPerformanceWorkList.add(new WorkBean());
        }
        for (int i = 0; i <= 2; i++) {
            mDocumentWorkList.add(new WorkBean());
        }
        for (int i = 0; i <= 1; i++) {
            mProjectWorkList.add(new WorkBean());
        }
        for (int i = 0; i <= 3; i++) {
            mDepartmentWorkList.add(new WorkBean());
        }

        for (int i = 0; i <= 1; i++) {
            mOtherWorkList.add(new WorkBean());
        }

        mCheckWorkList.get(0).setImageId(R.drawable.ic_check);
        mCheckWorkList.get(0).setTitle(getString(R.string.punch_card));
        mCheckWorkList.get(1).setImageId(R.drawable.ic_leave);
        mCheckWorkList.get(1).setTitle(getString(R.string.leave));
        mCheckWorkList.get(2).setImageId(R.drawable.ic_leave_check);
        mCheckWorkList.get(2).setTitle(getString(R.string.leave_approval));
        mCheckWorkList.get(3).setImageId(R.drawable.ic_democratic_appraisal);
        mCheckWorkList.get(3).setTitle(getString(R.string.democratic_appraisal));
        mCheckWorkList.get(4).setImageId(R.drawable.ic_approval);
        mCheckWorkList.get(4).setTitle(getString(R.string.patch_approval));
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
        mDepartmentWorkList.get(0).setImageId(R.drawable.ic_department_apply);
        mDepartmentWorkList.get(0).setTitle(getString(R.string.department_office_apply));
        mDepartmentWorkList.get(1).setImageId(R.drawable.ic_department_view);
        mDepartmentWorkList.get(1).setTitle(getString(R.string.department_office_view));
        mDepartmentWorkList.get(2).setImageId(R.drawable.ic_department_approval);
        mDepartmentWorkList.get(2).setTitle(getString(R.string.department_office_approval));
        mDepartmentWorkList.get(3).setImageId(R.drawable.ic_department_attendance);
        mDepartmentWorkList.get(3).setTitle(getString(R.string.department_office_attendance));
        mOtherWorkList.get(0).setImageId(R.drawable.ic_bylaw);
        mOtherWorkList.get(0).setTitle(getString(R.string.bylaw));
        mOtherWorkList.get(1).setImageId(R.drawable.ic_infomation);
        mOtherWorkList.get(1).setTitle(getString(R.string.informationmanage));
        mPerformanceAdapter = new WorkRvAdapter(R.layout.item_work_check, mCheckWorkList);
        mWorkAdapter = new WorkRvAdapter(R.layout.item_work_check, mPerformanceWorkList);
        mDocumentAdapter = new WorkRvAdapter(R.layout.item_work_check, mDocumentWorkList);
        mProjectAdapter = new WorkRvAdapter(R.layout.item_work_check, mProjectWorkList);
        mDepartmentAdapter = new WorkRvAdapter(R.layout.item_work_check, mDepartmentWorkList);
        mOtherAdapter = new WorkRvAdapter(R.layout.item_work_check, mOtherWorkList);
        mPerformanceRv.setNestedScrollingEnabled(false);
        mPerformanceRv.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mPerformanceRv.setHasFixedSize(true);
        mPerformanceRv.setAdapter(mPerformanceAdapter);
        mPerformanceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });


        mWorkRv.setNestedScrollingEnabled(false);
        mWorkRv.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mWorkRv.setHasFixedSize(true);
        mWorkRv.setAdapter(mWorkAdapter);
        mWorkAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mDocumentRv.setNestedScrollingEnabled(false);
        mDocumentRv.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mDocumentRv.setHasFixedSize(true);
        mDocumentRv.setAdapter(mDocumentAdapter);
        mDocumentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mProjectRv.setNestedScrollingEnabled(false);
        mProjectRv.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mProjectRv.setHasFixedSize(true);
        mProjectRv.setAdapter(mProjectAdapter);
        mProjectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mDepartmentRv.setNestedScrollingEnabled(false);
        mDepartmentRv.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mDepartmentRv.setHasFixedSize(true);
        mDepartmentRv.setAdapter(mDepartmentAdapter);
        mDepartmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mOtherRv.setNestedScrollingEnabled(false);
        mOtherRv.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mOtherRv.setHasFixedSize(true);
        mOtherRv.setAdapter(mOtherAdapter);
        mOtherAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }


}
