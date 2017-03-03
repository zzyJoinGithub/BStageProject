package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import java.util.List;

/**
 * Created by dllo on 17/3/3.
 */

public class VideoPlayBean {

    /**
     * code : 0000
     * msg :
     * data : {"m3u8":{"currentQuality":"low","url":"http://qcloud.rrmj.tv/2017/02/28/b95044b6f83e433f85854667867ff062.mp4.f20.mp4?sign=377b6278cbc5ac3d3a63782def1f17f4&t=58b8e3da&clientType=null&clientVersion3.5.2","qualityArr":["low","high","super"],"parserType":"DIRECT","source":""},"video":{"watchLevel":0}}
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
        /**
         * m3u8 : {"currentQuality":"low","url":"http://qcloud.rrmj.tv/2017/02/28/b95044b6f83e433f85854667867ff062.mp4.f20.mp4?sign=377b6278cbc5ac3d3a63782def1f17f4&t=58b8e3da&clientType=null&clientVersion3.5.2","qualityArr":["low","high","super"],"parserType":"DIRECT","source":""}
         * video : {"watchLevel":0}
         */

        private M3u8Bean m3u8;
        private VideoBean video;

        public M3u8Bean getM3u8() {
            return m3u8;
        }

        public void setM3u8(M3u8Bean m3u8) {
            this.m3u8 = m3u8;
        }

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public static class M3u8Bean {
            /**
             * currentQuality : low
             * url : http://qcloud.rrmj.tv/2017/02/28/b95044b6f83e433f85854667867ff062.mp4.f20.mp4?sign=377b6278cbc5ac3d3a63782def1f17f4&t=58b8e3da&clientType=null&clientVersion3.5.2
             * qualityArr : ["low","high","super"]
             * parserType : DIRECT
             * source :
             */

            private String currentQuality;
            private String url;
            private String parserType;
            private String source;
            private List<String> qualityArr;

            public String getCurrentQuality() {
                return currentQuality;
            }

            public void setCurrentQuality(String currentQuality) {
                this.currentQuality = currentQuality;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getParserType() {
                return parserType;
            }

            public void setParserType(String parserType) {
                this.parserType = parserType;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public List<String> getQualityArr() {
                return qualityArr;
            }

            public void setQualityArr(List<String> qualityArr) {
                this.qualityArr = qualityArr;
            }
        }

        public static class VideoBean {
            /**
             * watchLevel : 0
             */

            private int watchLevel;

            public int getWatchLevel() {
                return watchLevel;
            }

            public void setWatchLevel(int watchLevel) {
                this.watchLevel = watchLevel;
            }
        }
    }
}
