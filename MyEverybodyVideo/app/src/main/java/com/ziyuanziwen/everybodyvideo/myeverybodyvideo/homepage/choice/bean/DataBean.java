package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean;

import java.util.List;

/**
 * Created by dllo on 17/2/20.
 */
public class DataBean {

    private List<OfficalAlbumBean> officalAlbum;
    private List<RecommendUpBean> recommendUp;
    private List<BannerBean> bannerTop;
    private List<CategoryBean> categoryList;
    private List<BriefBean> todayRecommend;

    public List<OfficalAlbumBean> getOfficalAlbum() {
        return officalAlbum;
    }

    public void setOfficalAlbum(List<OfficalAlbumBean> officalAlbum) {
        this.officalAlbum = officalAlbum;
    }

    public List<RecommendUpBean> getRecommendUp() {
        return recommendUp;
    }

    public void setRecommendUp(List<RecommendUpBean> recommendUp) {
        this.recommendUp = recommendUp;
    }

    public List<BannerBean> getBannerTop() {
        return bannerTop;
    }

    public void setBannerTop(List<BannerBean> bannerTop) {
        this.bannerTop = bannerTop;
    }

    public List<CategoryBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryBean> categoryList) {
        this.categoryList = categoryList;
    }

    public List<BriefBean> getTodayRecommend() {
        return todayRecommend;
    }

    public void setTodayRecommend(List<BriefBean> todayRecommend) {
        this.todayRecommend = todayRecommend;
    }

    public static class OfficalAlbumBean{
        private String brief;
        private String cover;
        private String name;
        private int id;
        private List<BriefBean> resultList;

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
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

        public List<BriefBean> getResultList() {
            return resultList;
        }

        public void setResultList(List<BriefBean> resultList) {
            this.resultList = resultList;
        }
    }

    public static class RecommendUpBean{

        private String roleInfo;
        private int fansCount;
        private String headImg;
        private String intro;
        private String userIntro;
        private String description;
        private String name;
        private int id;

        public String getRoleInfo() {
            return roleInfo;
        }

        public void setRoleInfo(String roleInfo) {
            this.roleInfo = roleInfo;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getUserIntro() {
            return userIntro;
        }

        public void setUserIntro(String userIntro) {
            this.userIntro = userIntro;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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
    }

    public static class CategoryBean{
        private String categoryName;
        private int id;
        private List<BriefBean> videoList;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<BriefBean> getVideoList() {
            return videoList;
        }

        public void setVideoList(List<BriefBean> videoList) {
            this.videoList = videoList;
        }
    }
}
