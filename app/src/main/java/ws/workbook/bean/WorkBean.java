package ws.workbook.bean;


import ws.workbook.R;

/**
 * 作者： 吴骄权
 * 日期： 2017/10/11
 * 邮箱： Jpacino@163.com
 * 描述：
 */

public class WorkBean {
    private String title;
    private int imageId = R.drawable.ic_main_work;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
