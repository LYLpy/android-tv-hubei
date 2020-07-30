package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/5.
 */

public class CompositionGuidanceBean implements Parcelable {
    public CompositionGuidanceBean(int praiseFlag, int xueqiFlag, int collenctionFlag, String videoCount, int p, int pages, CourseInfoBean courseInfo, String courseId, String public_skin_img, List<VideoListBean> videoList, List<RecommendListBean> recommendList) {
        this.praiseFlag = praiseFlag;
        this.xueqiFlag = xueqiFlag;
        this.collenctionFlag = collenctionFlag;
        this.videoCount = videoCount;
        this.p = p;
        this.pages = pages;
        this.courseInfo = courseInfo;
        this.courseId = courseId;
        this.public_skin_img = public_skin_img;
        this.videoList = videoList;
        this.recommendList = recommendList;
    }

    public CompositionGuidanceBean() {
        super();
    }

    @Override
    public String toString() {
        return "CompositionGuidanceBean{" +
                "praiseFlag=" + praiseFlag +
                ", xueqiFlag=" + xueqiFlag +
                ", collenctionFlag=" + collenctionFlag +
                ", videoCount='" + videoCount + '\'' +
                ", p=" + p +
                ", pages=" + pages +
                ", courseInfo=" + courseInfo +
                ", courseId='" + courseId + '\'' +
                ", public_skin_img='" + public_skin_img + '\'' +
                ", videoList=" + videoList +
                ", recommendList=" + recommendList +
                '}';
    }

    /**
     * videoList : [{"id":"1","name":"人教版小学语文1年级上册识字1 天地人","xueqi_id":"1","is_free":"0"},{"id":"2","name":"人教版小学语文1年级上册识字2 金木水土火","xueqi_id":"1","is_free":"0"},{"id":"3","name":"人教版小学语文1年级上册识字3 口耳目","xueqi_id":"1","is_free":"0"},{"id":"24323","name":"测试视频啊啊啊","xueqi_id":"0","is_free":"0"},{"id":"4","name":"人教版小学语文1年级上册识字4 日月水火","xueqi_id":"1","is_free":"1"},{"id":"5","name":"人教版小学语文1年级上册识字5 对韵歌","xueqi_id":"1","is_free":"1"},{"id":"6","name":"人教版小学语文1年级上册识字6 画","xueqi_id":"1","is_free":"1"},{"id":"7","name":"人教版小学语文1年级上册识字7 大小多少","xueqi_id":"1","is_free":"1"},{"id":"8","name":"人教版小学语文1年级上册识字8 小书包","xueqi_id":"1","is_free":"1"},{"id":"9","name":"人教版小学语文1年级上册识字9 日月明","xueqi_id":"1","is_free":"1"}]
     * praiseFlag : 1
     * xueqiFlag : 1
     * collenctionFlag : 1
     * videoCount : 67
     * p : 1
     * pages : 7
     * recommendList : [{"id":"52","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2shuxue.png"},{"id":"66","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yingyu.png"}]
     * courseInfo : {"id":"38","name":"人教版小学语文1年级","status":"1","category_id":"7","tags":"2","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png","content":"有趣的拼音王国，可爱的字母卡通，教师借助形象的卡通图画和琅琅上口的字母儿歌与学习内容直观形象的结合在一起，帮助学生认识、熟悉字母，有趣的字母互动儿歌让刚学习拼音字母的学生体验学习拼音的乐趣，为孩子打开一个精彩的认字世界。","bgicon":"","title":"RJBXXYW","icon2":"","icon3":"","nianji_id":"1","price_id":"3","banben_id":"1","xueke_id":"1"}
     * courseId : 38
     * public_skin_img :
     */

    private int praiseFlag;
    private int xueqiFlag;
    private int collenctionFlag;
    private String videoCount;
    private int p;
    private int pages;
    private CourseInfoBean courseInfo;
    private String courseId;
    private String public_skin_img;
    private List<VideoListBean> videoList;
    private List<RecommendListBean> recommendList;

    public int getPraiseFlag() {
        return praiseFlag;
    }

    public void setPraiseFlag(int praiseFlag) {
        this.praiseFlag = praiseFlag;
    }

    public int getXueqiFlag() {
        return xueqiFlag;
    }

    public void setXueqiFlag(int xueqiFlag) {
        this.xueqiFlag = xueqiFlag;
    }

    public int getCollenctionFlag() {
        return collenctionFlag;
    }

    public void setCollenctionFlag(int collenctionFlag) {
        this.collenctionFlag = collenctionFlag;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(String videoCount) {
        this.videoCount = videoCount;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public CourseInfoBean getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfoBean courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPublic_skin_img() {
        return public_skin_img;
    }

    public void setPublic_skin_img(String public_skin_img) {
        this.public_skin_img = public_skin_img;
    }

    public List<VideoListBean> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoListBean> videoList) {
        this.videoList = videoList;
    }

    public List<RecommendListBean> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<RecommendListBean> recommendList) {
        this.recommendList = recommendList;
    }

    public static class CourseInfoBean implements Parcelable {
        /**
         * id : 38
         * name : 人教版小学语文1年级
         * status : 1
         * category_id : 7
         * tags : 2
         * icon : images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png
         * content : 有趣的拼音王国，可爱的字母卡通，教师借助形象的卡通图画和琅琅上口的字母儿歌与学习内容直观形象的结合在一起，帮助学生认识、熟悉字母，有趣的字母互动儿歌让刚学习拼音字母的学生体验学习拼音的乐趣，为孩子打开一个精彩的认字世界。
         * bgicon :
         * title : RJBXXYW
         * icon2 :
         * icon3 :
         * nianji_id : 1
         * price_id : 3
         * banben_id : 1
         * xueke_id : 1
         */

        private String id;
        private String name;
        private String status;
        private String category_id;
        private String tags;
        private String icon;
        private String content;
        private String bgicon;
        private String title;
        private String icon2;
        private String icon3;
        private String nianji_id;
        private String price_id;
        private String banben_id;
        private String xueke_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getBgicon() {
            return bgicon;
        }

        public void setBgicon(String bgicon) {
            this.bgicon = bgicon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon2() {
            return icon2;
        }

        public void setIcon2(String icon2) {
            this.icon2 = icon2;
        }

        public String getIcon3() {
            return icon3;
        }

        public void setIcon3(String icon3) {
            this.icon3 = icon3;
        }

        public String getNianji_id() {
            return nianji_id;
        }

        public void setNianji_id(String nianji_id) {
            this.nianji_id = nianji_id;
        }

        public String getPrice_id() {
            return price_id;
        }

        public void setPrice_id(String price_id) {
            this.price_id = price_id;
        }

        public String getBanben_id() {
            return banben_id;
        }

        public void setBanben_id(String banben_id) {
            this.banben_id = banben_id;
        }

        public String getXueke_id() {
            return xueke_id;
        }

        public void setXueke_id(String xueke_id) {
            this.xueke_id = xueke_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.status);
            dest.writeString(this.category_id);
            dest.writeString(this.tags);
            dest.writeString(this.icon);
            dest.writeString(this.content);
            dest.writeString(this.bgicon);
            dest.writeString(this.title);
            dest.writeString(this.icon2);
            dest.writeString(this.icon3);
            dest.writeString(this.nianji_id);
            dest.writeString(this.price_id);
            dest.writeString(this.banben_id);
            dest.writeString(this.xueke_id);
        }

        public CourseInfoBean() {
        }

        protected CourseInfoBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.status = in.readString();
            this.category_id = in.readString();
            this.tags = in.readString();
            this.icon = in.readString();
            this.content = in.readString();
            this.bgicon = in.readString();
            this.title = in.readString();
            this.icon2 = in.readString();
            this.icon3 = in.readString();
            this.nianji_id = in.readString();
            this.price_id = in.readString();
            this.banben_id = in.readString();
            this.xueke_id = in.readString();
        }

        public static final Creator<CourseInfoBean> CREATOR = new Creator<CourseInfoBean>() {
            @Override
            public CourseInfoBean createFromParcel(Parcel source) {
                return new CourseInfoBean(source);
            }

            @Override
            public CourseInfoBean[] newArray(int size) {
                return new CourseInfoBean[size];
            }
        };
    }

    public static class VideoListBean implements Parcelable {
        /**
         * id : 1
         * name : 人教版小学语文1年级上册识字1 天地人
         * xueqi_id : 1
         * is_free : 0
         */

        private String id;
        private String name;
        private String xueqi_id;
        private String is_free;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getXueqi_id() {
            return xueqi_id;
        }

        public void setXueqi_id(String xueqi_id) {
            this.xueqi_id = xueqi_id;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.xueqi_id);
            dest.writeString(this.is_free);
        }

        public VideoListBean() {
        }

        protected VideoListBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.xueqi_id = in.readString();
            this.is_free = in.readString();
        }

        public static final Creator<VideoListBean> CREATOR = new Creator<VideoListBean>() {
            @Override
            public VideoListBean createFromParcel(Parcel source) {
                return new VideoListBean(source);
            }

            @Override
            public VideoListBean[] newArray(int size) {
                return new VideoListBean[size];
            }
        };
    }

    public static class RecommendListBean implements Parcelable {
        /**
         * id : 52
         * icon : images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2shuxue.png
         */

        private String id;
        private String icon;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.icon);
        }

        public RecommendListBean() {
        }

        protected RecommendListBean(Parcel in) {
            this.id = in.readString();
            this.icon = in.readString();
        }

        public static final Creator<RecommendListBean> CREATOR = new Creator<RecommendListBean>() {
            @Override
            public RecommendListBean createFromParcel(Parcel source) {
                return new RecommendListBean(source);
            }

            @Override
            public RecommendListBean[] newArray(int size) {
                return new RecommendListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.praiseFlag);
        dest.writeInt(this.xueqiFlag);
        dest.writeInt(this.collenctionFlag);
        dest.writeString(this.videoCount);
        dest.writeInt(this.p);
        dest.writeInt(this.pages);
        dest.writeParcelable(this.courseInfo, flags);
        dest.writeString(this.courseId);
        dest.writeString(this.public_skin_img);
        dest.writeList(this.videoList);
        dest.writeList(this.recommendList);
    }

    protected CompositionGuidanceBean(Parcel in) {
        this.praiseFlag = in.readInt();
        this.xueqiFlag = in.readInt();
        this.collenctionFlag = in.readInt();
        this.videoCount = in.readString();
        this.p = in.readInt();
        this.pages = in.readInt();
        this.courseInfo = in.readParcelable(CourseInfoBean.class.getClassLoader());
        this.courseId = in.readString();
        this.public_skin_img = in.readString();
        this.videoList = new ArrayList<VideoListBean>();
        in.readList(this.videoList, VideoListBean.class.getClassLoader());
        this.recommendList = new ArrayList<RecommendListBean>();
        in.readList(this.recommendList, RecommendListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CompositionGuidanceBean> CREATOR = new Parcelable.Creator<CompositionGuidanceBean>() {
        @Override
        public CompositionGuidanceBean createFromParcel(Parcel source) {
            return new CompositionGuidanceBean(source);
        }

        @Override
        public CompositionGuidanceBean[] newArray(int size) {
            return new CompositionGuidanceBean[size];
        }
    };
}
