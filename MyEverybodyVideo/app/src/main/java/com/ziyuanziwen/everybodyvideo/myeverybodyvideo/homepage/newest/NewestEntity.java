package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.newest;

import java.util.List;

/**
 * Created by dllo on 17/2/24.
 */

/**
 *  最新界面标题栏的实体类
 *  作者:赵子文
 */

public class NewestEntity {

    /**
     * code : 0000
     * msg :
     * data : {"category":[{"leafCategory":[],"parentId":2,"name":"热点","id":129},{"leafCategory":[],"parentId":7,"name":"翻唱MV","id":132},{"leafCategory":[],"parentId":10,"name":"极限运动","id":140},{"leafCategory":[],"parentId":9,"name":"搞笑","id":142},{"leafCategory":[],"parentId":9,"name":"美食","id":144},{"leafCategory":[],"parentId":9,"name":"萌宠","id":145},{"leafCategory":[],"parentId":9,"name":"美女","id":146},{"leafCategory":[],"parentId":3,"name":"预告","id":147},{"leafCategory":[],"parentId":3,"name":"美漫","id":150},{"leafCategory":[],"parentId":8,"name":"科普","id":154},{"leafCategory":[],"parentId":5,"name":"外语教学","id":156}]}
     */


    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CategoryBean> category;

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

        public static class CategoryBean {
            /**
             * leafCategory : []
             * parentId : 2
             * name : 热点
             * id : 129
             */

            private int parentId;
            private String name;
            private int id;
            private List<?> leafCategory;

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<?> getLeafCategory() {
                return leafCategory;
            }

            public void setLeafCategory(List<?> leafCategory) {
                this.leafCategory = leafCategory;
            }
        }
    }
}
