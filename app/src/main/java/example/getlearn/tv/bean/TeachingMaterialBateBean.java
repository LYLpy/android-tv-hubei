package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/7.
 */

public class TeachingMaterialBateBean implements Parcelable {

    /**
     * pages : 1
     * course : [{"id":"940","name":"学前：儿歌童谣-英文儿歌","category_id":"67","icon2":"images/2018-07-19/5b502febc8294.png"},{"id":"941","name":"学前：儿歌童谣-动物儿歌顺口溜","category_id":"67","icon2":"images/2018-07-19/5b50300036eea.png"},{"id":"942","name":"学前：儿歌童谣-自然儿歌顺口溜","category_id":"67","icon2":"images/2018-07-19/5b50301471f37.png"},{"id":"943","name":"学前：儿歌童谣-植物儿歌顺口溜","category_id":"67","icon2":"images/2018-07-19/5b503025097f4.png"},{"id":"944","name":"学前：儿歌童谣-生活儿歌顺口溜","category_id":"67","icon2":"images/2018-07-19/5b50303cdf81e.png"},{"id":"945","name":"学前：儿歌童谣-认知儿歌顺口溜","category_id":"67","icon2":"images/2018-07-19/5b5039af06ab6.png"},{"id":"946","name":"学前：故事小屋-睡前故事","category_id":"67","icon2":"images/2018-07-19/5b5030c09cc68.png"},{"id":"947","name":"学前：故事小屋-童话故事","category_id":"67","icon2":"images/2018-07-19/5b5030e184f25.png"},{"id":"948","name":"学前：故事小屋-伊索寓言","category_id":"67","icon2":"images/2018-07-19/5b503109c54fd.png"},{"id":"949","name":"学前：故事小屋-谚语故事","category_id":"67","icon2":"images/2018-07-19/5b50313b2c77f.png"},{"id":"950","name":"学前：故事小屋-成语故事","category_id":"67","icon2":"images/2018-07-19/5b503153a428c.png"},{"id":"951","name":"学前：故事小屋-笑话故事","category_id":"67","icon2":"images/2018-07-19/5b5031efeebfd.png"}]
     * p : 1
     */

    private int pages;
    private int p;
    private List<CourseBean> course;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public List<CourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<CourseBean> course) {
        this.course = course;
    }

    public static class CourseBean implements Parcelable {
        /**
         * id : 940
         * name : 学前：儿歌童谣-英文儿歌
         * category_id : 67
         * icon2 : images/2018-07-19/5b502febc8294.png
         */

        private String id;
        private String name;
        private String category_id;
        private String icon2;

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

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getIcon2() {
            return icon2;
        }

        public void setIcon2(String icon2) {
            this.icon2 = icon2;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.category_id);
            dest.writeString(this.icon2);
        }

        public CourseBean() {
        }

        protected CourseBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.category_id = in.readString();
            this.icon2 = in.readString();
        }

        public static final Creator<CourseBean> CREATOR = new Creator<CourseBean>() {
            @Override
            public CourseBean createFromParcel(Parcel source) {
                return new CourseBean(source);
            }

            @Override
            public CourseBean[] newArray(int size) {
                return new CourseBean[size];
            }
        };
    }

    public TeachingMaterialBateBean() {
        super();
    }

    @Override
    public String toString() {
        return "TeachingMaterialBateBean{" +
                "pages=" + pages +
                ", p=" + p +
                ", course=" + course +
                '}';
    }

    public TeachingMaterialBateBean(int pages, int p, List<CourseBean> course) {
        this.pages = pages;
        this.p = p;
        this.course = course;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pages);
        dest.writeInt(this.p);
        dest.writeList(this.course);
    }

    protected TeachingMaterialBateBean(Parcel in) {
        this.pages = in.readInt();
        this.p = in.readInt();
        this.course = new ArrayList<CourseBean>();
        in.readList(this.course, CourseBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TeachingMaterialBateBean> CREATOR = new Parcelable.Creator<TeachingMaterialBateBean>() {
        @Override
        public TeachingMaterialBateBean createFromParcel(Parcel source) {
            return new TeachingMaterialBateBean(source);
        }

        @Override
        public TeachingMaterialBateBean[] newArray(int size) {
            return new TeachingMaterialBateBean[size];
        }
    };
}
