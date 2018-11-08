package ws.workbook.bean;

import java.util.List;

/**
 * 作者： 王爽
 * 日期： 2018/11/5
 * 描述：
 */

public class BannerBean {

    public List<lunbo> data;

    public List<lunbo> getData() {
        return data;
    }

    public void setData(List<lunbo> data) {
        this.data = data;
    }

    public class lunbo{
        private String title;
        private String image;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

}
