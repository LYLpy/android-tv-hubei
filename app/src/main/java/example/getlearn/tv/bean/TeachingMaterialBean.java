package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/6.
 */

public class TeachingMaterialBean implements Parcelable {
//    public static final int TEXT = 1;
//    public static final int IMG = 2;
//    private int itemType;
//    MultiItemEntity
    /**
     * rec_data : [{"course_id":"941","course_name":"动物儿歌顺口溜","img":"images/2018-07-19/5b4ffbadc4841.png"},{"course_id":"946","course_name":"睡前故事","img":"images/2018-07-19/5b4ffbb88a357.png"},{"course_id":"960","course_name":"国学经典-儿童唐诗","img":"images/2018-07-19/5b4ffbc365ed4.png"},{"course_id":"959","course_name":"国学经典-增广贤文","img":"images/2018-07-19/5b4ffbcaa0579.png"},{"course_id":"961","course_name":"亲子乐园-安全知识","img":"images/2018-07-19/5b4ffbd42f1e5.png"},{"course_id":"966","course_name":"认识身体和服饰","img":"images/2018-07-19/5b4ffbdc1f271.png"},{"course_id":"988","course_name":"欢乐动画-乐比悠悠1","img":"images/2018-07-19/5b4ffbe3c90a9.png"}]
     * cate_data : [{"cname":"国学课堂","cid":"56"},{"cname":"绘本儿歌","cid":"67"},{"cname":"亲子早教","cid":"70"},{"cname":"艺术创想","cid":"68"},{"cname":"科学探索","cid":"69"},{"cname":"少儿英语","cid":"71"},{"cname":"卡通动漫","cid":"73"},{"cname":"安全知识","cid":"72"}]
     * public_skin_img : /Public/Uploads/images/5b8df2517448c.png
     */

    private String public_skin_img;
    private List<RecDataBean> rec_data;
    private List<CateDataBean> cate_data;
//
//    @Override
//    public int getItemType() {
//        return 0;
//    }

    public String getPublic_skin_img() {
        return public_skin_img;
    }

    public void setPublic_skin_img(String public_skin_img) {
        this.public_skin_img = public_skin_img;
    }

    public List<RecDataBean> getRec_data() {
        return rec_data;
    }

    public void setRec_data(List<RecDataBean> rec_data) {
        this.rec_data = rec_data;
    }

    public List<CateDataBean> getCate_data() {
        return cate_data;
    }

    public void setCate_data(List<CateDataBean> cate_data) {
        this.cate_data = cate_data;
    }


    public static class RecDataBean implements Parcelable {
        /**
         * course_id : 941
         * course_name : 动物儿歌顺口溜
         * img : images/2018-07-19/5b4ffbadc4841.png
         */

        private String course_id;
        private String course_name;
        private String img;

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.course_id);
            dest.writeString(this.course_name);
            dest.writeString(this.img);
        }

        public RecDataBean() {
        }

        protected RecDataBean(Parcel in) {
            this.course_id = in.readString();
            this.course_name = in.readString();
            this.img = in.readString();
        }

        public static final Creator<RecDataBean> CREATOR = new Creator<RecDataBean>() {
            @Override
            public RecDataBean createFromParcel(Parcel source) {
                return new RecDataBean(source);
            }

            @Override
            public RecDataBean[] newArray(int size) {
                return new RecDataBean[size];
            }
        };
    }

    public static class CateDataBean implements Parcelable {
        /**
         * cname : 国学课堂
         * cid : 56
         */

        private String cname;
        private String cid;

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cname);
            dest.writeString(this.cid);
        }

        public CateDataBean() {
        }

        protected CateDataBean(Parcel in) {
            this.cname = in.readString();
            this.cid = in.readString();
        }

        public static final Creator<CateDataBean> CREATOR = new Creator<CateDataBean>() {
            @Override
            public CateDataBean createFromParcel(Parcel source) {
                return new CateDataBean(source);
            }

            @Override
            public CateDataBean[] newArray(int size) {
                return new CateDataBean[size];
            }
        };
    }

    public TeachingMaterialBean() {
        super();
    }

    @Override
    public String toString() {
        return "TeachingMaterialBean{" +
                "public_skin_img='" + public_skin_img + '\'' +
                ", rec_data=" + rec_data +
                ", cate_data=" + cate_data +
                '}';
    }

    public TeachingMaterialBean(String public_skin_img, List<RecDataBean> rec_data, List<CateDataBean> cate_data) {
        this.public_skin_img = public_skin_img;
        this.rec_data = rec_data;
        this.cate_data = cate_data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.public_skin_img);
        dest.writeList(this.rec_data);
        dest.writeList(this.cate_data);
    }

    protected TeachingMaterialBean(Parcel in) {
        this.public_skin_img = in.readString();
        this.rec_data = new ArrayList<RecDataBean>();
        in.readList(this.rec_data, RecDataBean.class.getClassLoader());
        this.cate_data = new ArrayList<CateDataBean>();
        in.readList(this.cate_data, CateDataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TeachingMaterialBean> CREATOR = new Parcelable.Creator<TeachingMaterialBean>() {
        @Override
        public TeachingMaterialBean createFromParcel(Parcel source) {
            return new TeachingMaterialBean(source);
        }

        @Override
        public TeachingMaterialBean[] newArray(int size) {
            return new TeachingMaterialBean[size];
        }
    };
}
