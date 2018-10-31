package ws.workbook.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

import ws.workbook.R;
import ws.workbook.bean.WorkBean;

/**
 * 作者： 吴骄权
 * 日期： 2017/10/11
 * 邮箱： Jpacino@163.com
 * 描述：
 */

public class WorkRvAdapter extends BaseQuickAdapter<WorkBean,BaseViewHolder> {
    public WorkRvAdapter(@LayoutRes int layoutResId, @Nullable List<WorkBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkBean item) {
       ImageView imageView =  helper.getView(R.id.icon_iv);
        imageView.setImageResource(item.getImageId());
        helper.setText(R.id.title_tv,item.getTitle());
    }
}
