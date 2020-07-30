package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/7.
 */

public class PrimarySchoolClassBean implements Parcelable {


    /**
     * nianjiList : [{"nianji_id":1,"name":"一年级"},{"nianji_id":2,"name":"二年级"},{"nianji_id":3,"name":"三年级"},{"nianji_id":4,"name":"四年级"},{"nianji_id":5,"name":"五年级"},{"nianji_id":6,"name":"六年级"}]
     * nianjiId : 1
     * xuekeId : 1
     * p : 1
     * public_skin_img : /Public/Uploads/images/5b8df3d2c70c4.png
     */

    private int nianjiId;
    private String xuekeId;
    private int p;
    private String public_skin_img;
    private List<NianjiListBean> nianjiList;

    public int getNianjiId() {
        return nianjiId;
    }

    public void setNianjiId(int nianjiId) {
        this.nianjiId = nianjiId;
    }

    public String getXuekeId() {
        return xuekeId;
    }

    public void setXuekeId(String xuekeId) {
        this.xuekeId = xuekeId;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public String getPublic_skin_img() {
        return public_skin_img;
    }

    public void setPublic_skin_img(String public_skin_img) {
        this.public_skin_img = public_skin_img;
    }

    public List<NianjiListBean> getNianjiList() {
        return nianjiList;
    }

    public void setNianjiList(List<NianjiListBean> nianjiList) {
        this.nianjiList = nianjiList;
    }

    public static class NianjiListBean implements Parcelable {
        /**
         * nianji_id : 1
         * name : 一年级
         */

        private int nianji_id;
        private String name;

        public int getNianji_id() {
            return nianji_id;
        }

        public void setNianji_id(int nianji_id) {
            this.nianji_id = nianji_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.nianji_id);
            dest.writeString(this.name);
        }

        public NianjiListBean() {
        }

        protected NianjiListBean(Parcel in) {
            this.nianji_id = in.readInt();
            this.name = in.readString();
        }

        public static final Creator<NianjiListBean> CREATOR = new Creator<NianjiListBean>() {
            @Override
            public NianjiListBean createFromParcel(Parcel source) {
                return new NianjiListBean(source);
            }

            @Override
            public NianjiListBean[] newArray(int size) {
                return new NianjiListBean[size];
            }
        };
    }

    public PrimarySchoolClassBean() {
        super();
    }

    @Override
    public String toString() {
        return "PrimarySchoolClassBean{" +
                "nianjiId=" + nianjiId +
                ", xuekeId='" + xuekeId + '\'' +
                ", p=" + p +
                ", public_skin_img='" + public_skin_img + '\'' +
                ", nianjiList=" + nianjiList +
                '}';
    }

    public PrimarySchoolClassBean(int nianjiId, String xuekeId, int p, String public_skin_img, List<NianjiListBean> nianjiList) {
        this.nianjiId = nianjiId;
        this.xuekeId = xuekeId;
        this.p = p;
        this.public_skin_img = public_skin_img;
        this.nianjiList = nianjiList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.nianjiId);
        dest.writeString(this.xuekeId);
        dest.writeInt(this.p);
        dest.writeString(this.public_skin_img);
        dest.writeList(this.nianjiList);
    }

    protected PrimarySchoolClassBean(Parcel in) {
        this.nianjiId = in.readInt();
        this.xuekeId = in.readString();
        this.p = in.readInt();
        this.public_skin_img = in.readString();
        this.nianjiList = new ArrayList<NianjiListBean>();
        in.readList(this.nianjiList, NianjiListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PrimarySchoolClassBean> CREATOR = new Parcelable.Creator<PrimarySchoolClassBean>() {
        @Override
        public PrimarySchoolClassBean createFromParcel(Parcel source) {
            return new PrimarySchoolClassBean(source);
        }

        @Override
        public PrimarySchoolClassBean[] newArray(int size) {
            return new PrimarySchoolClassBean[size];
        }
    };
}
