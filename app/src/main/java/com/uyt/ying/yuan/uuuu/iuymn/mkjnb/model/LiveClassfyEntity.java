package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.model;

import com.uyt.ying.yuan.uuuu.iuymn.mkjnb.utils.CommonStr;

import java.io.Serializable;
import java.util.List;

public class LiveClassfyEntity implements Serializable {

    /**
     * categoryList : [{"imgUrl":"xblive/images/20200608/53a95219394b42fa89bbd0c76bdd599b.png","name":Utils.getString(R.string.性感),"categoryId":"1254291154032500737"},{"imgUrl":"xblive/images/20200609/e1b87daa9782423c889e30e49a54f415.jpg","name":Utils.getString(R.string.激情),"categoryId":"1254291251055140866"},{"imgUrl":"xblive/images/20200609/9eb207461da54b4191d9c031ede6187c.jpg","name":Utils.getString(R.string.模特),"categoryId":"1254291303542661122"},{"imgUrl":"xblive/images/20200609/5366f3b4f1ff458ca2d6f3b0619a06f8.png","name":Utils.getString(R.string.颜值),"categoryId":"1270213688864854017"},{"imgUrl":"xblive/images/20200609/265b8f2fb3df40228eb4e7ea99934f80.png","name":Utils.getString(R.string.星秀大咖),"categoryId":"1270213819299319810"},{"imgUrl":"xblive/images/20200610/e542ab28f4d043ab89bf32f90880c83f.jpg","name":Utils.getString(R.string.户外),"categoryId":"1270639718616313857"},{"imgUrl":"xblive/images/20200630/37681a8b92834baa81919b0ae6dc04f4.png","name":Utils.getString(R.string.大秀),"categoryId":"1277912039127257089"},{"imgUrl":"xblive/images/20200708/c6dee6acdcff4684be74671956b3cd45.png","name":Utils.getString(R.string.御姐),"categoryId":"1280697775408930818"},{"imgUrl":"","name":Utils.getString(R.string.王菲和),"categoryId":"1280723623893655554"},{"imgUrl":"","name":Utils.getString(R.string.死胖子),"categoryId":"1280780325192065026"}]
     * message : 获取直播分类成功!!!!
     * status : success
     */

    private String message;
    private String status;
    private List<CategoryListBean> categoryList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public static class CategoryListBean implements Serializable {
        /**
         * imgUrl : xblive/images/20200608/53a95219394b42fa89bbd0c76bdd599b.png
         * name : 性感
         * categoryId : 1254291154032500737
         */

        private String imgUrl;
        private String name;
        private String categoryId = CommonStr.CATEGORY_DEFAULT_VALUE;
        private String area= CommonStr.AREA_DEFAULT_VALUE;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }


    }

}
