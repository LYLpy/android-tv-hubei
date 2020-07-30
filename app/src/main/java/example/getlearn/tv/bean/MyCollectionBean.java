package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/8.
 */

public class MyCollectionBean implements Parcelable {


    /**
     * Collection : [{"id":"1008","name":"小学语文作文指导","icon":"images/2017-04-25/zuowen-xiaoxue.png","icon2":"","icon3":"","moduleid":"3","c_name":"语文"},{"id":"954","name":"学前：国学经典-百家姓","icon":"images/2017-04-26/guoxue_002.png","icon2":"images/2017-04-26/xinguoxue_bjx.png\r\n","icon3":"","moduleid":"2","c_name":"学前：国学经典"},{"id":"935","name":"初中单词王_opinion","icon":"images/2017-04-25/chuzhongdanci-opinion.png","icon2":"","icon3":"","moduleid":"5","c_name":"初中单词王"},{"id":"960","name":"学前：国学经典-儿童唐诗","icon":"images/2017-04-26/guoxue_006.png","icon2":"images/2017-04-26/xinguoxue_etts.png","icon3":"","moduleid":"2","c_name":"学前：国学经典"},{"id":"2451","name":"小伶玩具第三季","icon":"images/2018-08-06/5b67e88f0eb37.jpg","icon2":"images/2017-04-26/xiaolingwanju3.png","icon3":"","moduleid":"2","c_name":"艺术创想"},{"id":"2457","name":"星球大战系列","icon":"images/2018-08-06/5b67f20632436.png","icon2":"images/2017-04-26/xingqiudazhanxl.png","icon3":"","moduleid":"2","c_name":"科学探索"},{"id":"2363","name":"国学三字经（真人授课）","icon":"images/2018-08-06/5b6801f7b9c2f.jpg","icon2":"images/2018-08-06/5b6801f7ba296.png","icon3":"","moduleid":"4","c_name":"小学国学"},{"id":"39","name":"人教版小学语文2年级","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2yuwen.png","icon2":"","icon3":"","moduleid":"4","c_name":"小学同步精讲二年级"},{"id":"941","name":"学前：儿歌童谣-动物儿歌顺口溜","icon":"images/2017-04-25/erge_001.png","icon2":"images/2018-07-19/5b50300036eea.png","icon3":"","moduleid":"2","c_name":"绘本儿歌"},{"id":"940","name":"学前：儿歌童谣-英文儿歌","icon":"images/2017-04-25/erge_003.png","icon2":"images/2018-07-19/5b502febc8294.png","icon3":"","moduleid":"2","c_name":"绘本儿歌"},{"id":"2000","name":"状元大课堂语文一年级","icon":"images/2017-04-25/zhuangyuanketang-1yw.png","icon2":"","icon3":"","moduleid":"4","c_name":"状元课堂一年级"},{"id":"2323","name":"多彩伊比沙","icon":"images/2017-04-25/duocaiyibisha.jpg","icon2":"images/2018-07-25/5b5824e4d0fe1.png","icon3":"","moduleid":"2","c_name":"艺术创想"},{"id":"542","name":"北师大版初中数学7年级","icon":"images/2017-04-25/zhongxuetongbujingjiang-beishida7shuxue.png","icon2":"","icon3":"","moduleid":"5","c_name":"初中同步精讲7年级"},{"id":"2339","name":"小星猫魔幻乐章1","icon":"images/2017-04-25/xiaoxingmaomohuanyuezhang1.jpg","icon2":"images/2018-07-25/5b581b76cf8bc.png","icon3":"","moduleid":"2","c_name":"卡通动漫"},{"id":"2359","name":"快乐农庄","icon":"images/2017-04-25/kuailenongzhuang.jpg","icon2":"images/2018-07-25/5b58240825411.png","icon3":"","moduleid":"2","c_name":"卡通动漫"},{"id":"362","name":"小学单词王-Unit 4","icon":"images/2017-04-25/xiaoxuedanci-Unit4.png","icon2":"","icon3":"","moduleid":"4","c_name":"小学单词王"},{"id":"2374","name":"绘本奥斯卡","icon":"images/2018-08-06/5b67e03c5f006.jpg","icon2":"images/2017-04-26/huibenaosika.png","icon3":"","moduleid":"2","c_name":"绘本儿歌"},{"id":"57","name":"人教版小学数学6年级","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban6shuxue.png","icon2":"","icon3":"","moduleid":"4","c_name":"小学同步精讲六年级"},{"id":"2330","name":"生活大玩家","icon":"images/2017-04-25/shenghuodawanjia.jpg","icon2":"images/2018-07-25/5b5826bb91041.png","icon3":"","moduleid":"2","c_name":"艺术创想"},{"id":"512","name":"北师大版初中政治7年级 ","icon":"images/2017-04-25/zhongxuetongbujingjiang-beishida7zhengzhi.png","icon2":"","icon3":"","moduleid":"5","c_name":"初中同步精讲7年级"},{"id":"1215","name":"北师大版高中英语必修4","icon":"images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiuyingyu4.png","icon2":"","icon3":"","moduleid":"5","c_name":"高中同步精讲必修4"},{"id":"844","name":"苏教版高中英语必修四 ","icon":"images/2017-04-26/zhongxuetongbujingjiang-shujiaobangaozhongbixiu4.png","icon2":"","icon3":"","moduleid":"5","c_name":"高中同步精讲必修4"},{"id":"94","name":"人教版初中历史9年级","icon":"images/2017-04-26/zhongxuetongbujingjiang-renjiaobanlishi9.png","icon2":"","icon3":"","moduleid":"5","c_name":"初中同步精讲9年级"},{"id":"955","name":"学前：国学经典-弟子规","icon":"images/2017-04-26/guoxue_005.png","icon2":"images/2017-04-26/xinguoxue_dzg.png","icon3":"","moduleid":"2","c_name":"学前：国学经典"},{"id":"2359","name":"快乐农庄","icon":"images/2017-04-25/kuailenongzhuang.jpg","icon2":"images/2018-07-25/5b58240825411.png","icon3":"","moduleid":"2","c_name":"卡通动漫"},{"id":"1207","name":"北师大版高中数学必修1 ","icon":"images/2017-04-25/zhongxuetongbujingjiang-beishida-bixiushuxue1.png","icon2":"","icon3":"","moduleid":"5","c_name":"高中同步精讲必修1"},{"id":"54","name":"人教版小学数学3年级","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3shuxue.png","icon2":"","icon3":"","moduleid":"4","c_name":"小学同步精讲三年级"},{"id":"52","name":"人教版小学数学1年级","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban2shuxue.png","icon2":"","icon3":"","moduleid":"4","c_name":"小学同步精讲一年级"},{"id":"68","name":"人教版小学英语3年级","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban3yingyu.png","icon2":"","icon3":"","moduleid":"4","c_name":"小学同步精讲三年级"},{"id":"955","name":"学前：国学经典-弟子规","icon":"images/2017-04-26/guoxue_005.png","icon2":"images/2017-04-26/xinguoxue_dzg.png","icon3":"","moduleid":"2","c_name":"学前：国学经典"},{"id":"38","name":"人教版小学语文1年级","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png","icon2":"","icon3":"","moduleid":"4","c_name":"小学同步精讲一年级"},{"id":"38","name":"人教版小学语文1年级","icon":"images/2017-04-25/xiaoxuetongbujingjiang-renjiaoban1yuwen.png","icon2":"","icon3":"","moduleid":"4","c_name":"小学同步精讲一年级"}]
     * page : 32
     * count : 32
     * pagemj : 0
     * all_id :
     * emptyIcon : true
     * public_skin_img : /Public/Uploads/images/5b8ce89e37334.png
     */

    private int page;
    private int count;
    private int pagemj;
    private String all_id;
    private boolean emptyIcon;
    private String public_skin_img;
    private List<CollectionBean> Collection;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPagemj() {
        return pagemj;
    }

    public void setPagemj(int pagemj) {
        this.pagemj = pagemj;
    }

    public String getAll_id() {
        return all_id;
    }

    public void setAll_id(String all_id) {
        this.all_id = all_id;
    }

    public boolean isEmptyIcon() {
        return emptyIcon;
    }

    public void setEmptyIcon(boolean emptyIcon) {
        this.emptyIcon = emptyIcon;
    }

    public String getPublic_skin_img() {
        return public_skin_img;
    }

    public void setPublic_skin_img(String public_skin_img) {
        this.public_skin_img = public_skin_img;
    }

    public List<CollectionBean> getCollection() {
        return Collection;
    }

    public void setCollection(List<CollectionBean> Collection) {
        this.Collection = Collection;
    }

    public static class CollectionBean {
        /**w
         * id : 1008
         * name : 小学语文作文指导
         * icon : images/2017-04-25/zuowen-xiaoxue.png
         * icon2 :
         * icon3 :
         * moduleid : 3
         * c_name : 语文
         */

        private String id;
        private String name;
        private String icon;
        private String icon2;
        private String icon3;
        private String moduleid;
        private String c_name;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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

        public String getModuleid() {
            return moduleid;
        }

        public void setModuleid(String moduleid) {
            this.moduleid = moduleid;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }
    }

    public MyCollectionBean() {
        super();
    }

    @Override
    public String toString() {
        return "MyCollectionBean{" +
                "page=" + page +
                ", count=" + count +
                ", pagemj=" + pagemj +
                ", all_id='" + all_id + '\'' +
                ", emptyIcon=" + emptyIcon +
                ", public_skin_img='" + public_skin_img + '\'' +
                ", Collection=" + Collection +
                '}';
    }

    public MyCollectionBean(int page, int count, int pagemj, String all_id, boolean emptyIcon, String public_skin_img, List<CollectionBean> collection) {
        this.page = page;
        this.count = count;
        this.pagemj = pagemj;
        this.all_id = all_id;
        this.emptyIcon = emptyIcon;
        this.public_skin_img = public_skin_img;
        Collection = collection;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.count);
        dest.writeInt(this.pagemj);
        dest.writeString(this.all_id);
        dest.writeByte(this.emptyIcon ? (byte) 1 : (byte) 0);
        dest.writeString(this.public_skin_img);
        dest.writeList(this.Collection);
    }

    protected MyCollectionBean(Parcel in) {
        this.page = in.readInt();
        this.count = in.readInt();
        this.pagemj = in.readInt();
        this.all_id = in.readString();
        this.emptyIcon = in.readByte() != 0;
        this.public_skin_img = in.readString();
        this.Collection = new ArrayList<CollectionBean>();
        in.readList(this.Collection, CollectionBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<MyCollectionBean> CREATOR = new Parcelable.Creator<MyCollectionBean>() {
        @Override
        public MyCollectionBean createFromParcel(Parcel source) {
            return new MyCollectionBean(source);
        }

        @Override
        public MyCollectionBean[] newArray(int size) {
            return new MyCollectionBean[size];
        }
    };
}
