package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/8.
 */

public class NumberOneClassBaseBean implements Parcelable {
    public NumberOneClassBaseBean(String titel, String count, String unit, int page, int pages, List<ListBean> list) {
        this.titel = titel;
        this.count = count;
        this.unit = unit;
        this.page = page;
        this.pages = pages;
        this.list = list;
    }

    public NumberOneClassBaseBean() {
        super();
    }

    @Override
    public String toString() {
        return "NumberOneClassBaseBean{" +
                "titel='" + titel + '\'' +
                ", count='" + count + '\'' +
                ", unit='" + unit + '\'' +
                ", page=" + page +
                ", pages=" + pages +
                ", list=" + list +
                '}';
    }

    /**
     * list : [{"id":"658","name":"人教版小学数学1年级上册1 准备课 （第一课时）","banben_id":"0","nianfen_id":"0","xueduan_id":"1","xueke_id":"2","nianji_id":"1","xueqi_id":"1","course_id":"52","teacherid":"523","icon":"/uploadfile/vieo/2016-12-30/0198.png","clarity":"1","fileurl":"/uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201201.ts","createtime":"0000-00-00 00:00:00","status":"1","del":"2","hits":"0","collections":"0","content":"","is_free":"0","correlate_id":"80139220cb76df365a71e2570e58d0f5","movie_code":"f90cb3fc28469be18e66b3f200b68c3b","inpouring_status":"2","play_url":"http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201201.m3u8","volumn_count":"1","result_file_url":"http://117.71.39.8/chigo/2018/7/20/shichang_1532093479067.xml","handle_time":"1532093383","url":"index.php?m=&c=Index&a=videoPlay&video_id=658"},{"id":"659","name":"人教版小学数学1年级上册1 准备课 （第二课时）","banben_id":"0","nianfen_id":"0","xueduan_id":"1","xueke_id":"2","nianji_id":"1","xueqi_id":"1","course_id":"52","teacherid":"523","icon":"/uploadfile/vieo/2016-12-30/0199.png","clarity":"1","fileurl":"/uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201202.ts","createtime":"0000-00-00 00:00:00","status":"1","del":"3","hits":"0","collections":"0","content":"","is_free":"0","correlate_id":"60fbb274bead191e8bc4b30d8d1f7ce0","movie_code":"98f360f20b0e20b0336e3be5fede17d8","inpouring_status":"2","play_url":"http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201202.m3u8","volumn_count":"2","result_file_url":"http://117.71.39.8/chigo/2018/6/29/shichang_1530243907851.xml","handle_time":"1530243844","url":"index.php?m=&c=Index&a=videoPlay&video_id=659"},{"id":"660","name":"人教版小学数学1年级上册2 位置","banben_id":"0","nianfen_id":"0","xueduan_id":"1","xueke_id":"2","nianji_id":"1","xueqi_id":"1","course_id":"52","teacherid":"523","icon":"/uploadfile/vieo/2016-12-30/0200.png","clarity":"1","fileurl":"/uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201203.ts","createtime":"0000-00-00 00:00:00","status":"1","del":"3","hits":"0","collections":"0","content":"","is_free":"0","correlate_id":"a98445be6f9150bb395fb27a50454339","movie_code":"4bbe91091edd6f6ee371136b251a1f69","inpouring_status":"2","play_url":"http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201203.m3u8","volumn_count":"3","result_file_url":"http://117.71.39.8/chigo/2018/6/29/shichang_1530243909917.xml","handle_time":"1530243846","url":"index.php?m=&c=Index&a=videoPlay&video_id=660"},{"id":"661","name":"人教版小学数学1年级上册3 1~5的认识和加减法（第一课时）","banben_id":"0","nianfen_id":"0","xueduan_id":"1","xueke_id":"2","nianji_id":"1","xueqi_id":"1","course_id":"52","teacherid":"523","icon":"/uploadfile/vieo/2016-12-30/0201.png","clarity":"1","fileurl":"/uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201204.ts","createtime":"0000-00-00 00:00:00","status":"1","del":"2","hits":"0","collections":"0","content":"","is_free":"1","correlate_id":"621e952c3fe701472edb998c5acd53b4","movie_code":"516dadf220bc108a3909a39eaaf11637","inpouring_status":"2","play_url":"http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201204.m3u8","volumn_count":"4","result_file_url":"http://117.71.39.8/chigo/2018/6/29/shichang_1530243911986.xml","handle_time":"1530243849","url":"index.php?m=&c=Index&a=videoPlay&video_id=661"},{"id":"662","name":"人教版小学数学1年级上册3 1-5的认识和加减法（第二课时）","banben_id":"0","nianfen_id":"0","xueduan_id":"1","xueke_id":"2","nianji_id":"1","xueqi_id":"1","course_id":"52","teacherid":"523","icon":"/uploadfile/vieo/2016-12-30/0202.png","clarity":"1","fileurl":"/uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201205.ts","createtime":"0000-00-00 00:00:00","status":"1","del":"3","hits":"0","collections":"0","content":"","is_free":"1","correlate_id":"e7b6931ba5d367ecb7d397fc7a379ae9","movie_code":"31b9a60f9808ffafcc91a99394ea03e4","inpouring_status":"2","play_url":"http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201205.m3u8","volumn_count":"5","result_file_url":"http://117.71.39.8/chigo/2018/6/29/shichang_1530243914048.xml","handle_time":"1530243851","url":"index.php?m=&c=Index&a=videoPlay&video_id=662"},{"id":"663","name":"人教版小学数学1年级上册3 1-5的认识和加减法（第三课时）","banben_id":"0","nianfen_id":"0","xueduan_id":"1","xueke_id":"2","nianji_id":"1","xueqi_id":"1","course_id":"52","teacherid":"523","icon":"/uploadfile/vieo/2016-12-30/0203.png","clarity":"1","fileurl":"/uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201206.ts","createtime":"0000-00-00 00:00:00","status":"1","del":"1","hits":"0","collections":"0","content":"","is_free":"1","correlate_id":"392f9a2a01e79e17c923ebeace213e21","movie_code":"b2be25da650193c2303e170177c52a9c","inpouring_status":"2","play_url":"http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201206.m3u8","volumn_count":"6","result_file_url":"http://117.71.39.8/chigo/2018/6/29/shichang_1530243916116.xml","handle_time":"1530243853","url":"index.php?m=&c=Index&a=videoPlay&video_id=663"},{"id":"664","name":"人教版小学数学1年级上册4 认识图形（一）","banben_id":"0","nianfen_id":"0","xueduan_id":"1","xueke_id":"2","nianji_id":"1","xueqi_id":"1","course_id":"52","teacherid":"523","icon":"/uploadfile/vieo/2016-12-30/0204.png","clarity":"1","fileurl":"/uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201207.ts","createtime":"0000-00-00 00:00:00","status":"1","del":"1","hits":"0","collections":"0","content":"","is_free":"1","correlate_id":"9a095bfac9b811e4afa40fd6c88dbca3","movie_code":"a59bf038436092e8e621beba9b284dda","inpouring_status":"2","play_url":"http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201207.m3u8","volumn_count":"7","result_file_url":"http://117.71.39.8/chigo/2018/6/29/shichang_1530243918173.xml","handle_time":"1530243855","url":"index.php?m=&c=Index&a=videoPlay&video_id=664"},{"id":"665","name":"人教版小学数学1年级上册5 6~10的认识和加减法 （第一课时）","banben_id":"0","nianfen_id":"0","xueduan_id":"1","xueke_id":"2","nianji_id":"1","xueqi_id":"1","course_id":"52","teacherid":"523","icon":"/uploadfile/vieo/2016-12-30/0205.png","clarity":"1","fileurl":"/uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201208.ts","createtime":"0000-00-00 00:00:00","status":"1","del":"3","hits":"0","collections":"0","content":"","is_free":"1","correlate_id":"13daf56a479b32725b07b71d757dff58","movie_code":"263c177601c48334194c5d5ab9328878","inpouring_status":"2","play_url":"http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201208.m3u8","volumn_count":"8","result_file_url":"http://117.71.39.8/chigo/2018/6/29/shichang_1530243920243.xml","handle_time":"1530243857","url":"index.php?m=&c=Index&a=videoPlay&video_id=665"},{"id":"666","name":"人教版小学数学1年级上册5 6~10的认识和加减法 （第二课时）","banben_id":"0","nianfen_id":"0","xueduan_id":"1","xueke_id":"2","nianji_id":"1","xueqi_id":"1","course_id":"52","teacherid":"523","icon":"/uploadfile/vieo/2016-12-30/0206.png","clarity":"1","fileurl":"/uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201209.ts","createtime":"0000-00-00 00:00:00","status":"1","del":"2","hits":"0","collections":"0","content":"","is_free":"1","correlate_id":"d6267da9048deb4e9a874aec13373729","movie_code":"b2eec70459f6135d0e7cbb3138398d8f","inpouring_status":"2","play_url":"http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201209.m3u8","volumn_count":"9","result_file_url":"http://117.71.39.8/chigo/2018/6/29/shichang_1530243922310.xml","handle_time":"1530243859","url":"index.php?m=&c=Index&a=videoPlay&video_id=666"},{"id":"667","name":"人教版小学数学1年级上册5 6~10的认识和加减法 （第三课时）","banben_id":"0","nianfen_id":"0","xueduan_id":"1","xueke_id":"2","nianji_id":"1","xueqi_id":"1","course_id":"52","teacherid":"523","icon":"/uploadfile/vieo/2016-12-30/0207.png","clarity":"1","fileurl":"/uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201210.ts","createtime":"0000-00-00 00:00:00","status":"1","del":"2","hits":"0","collections":"0","content":"","is_free":"1","correlate_id":"b330d988395f2d738593a2ef256be286","movie_code":"b4f9bedb78baa3c3ee4def145991ece6","inpouring_status":"2","play_url":"http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201210.m3u8","volumn_count":"10","result_file_url":"http://117.71.39.8/chigo/2018/6/29/shichang_1530243924378.xml","handle_time":"1530243861","url":"index.php?m=&c=Index&a=videoPlay&video_id=667"}]
     * titel :
     * count : 34
     * unit : 个视频
     * page : 1
     * pages : 4
     */

    private String titel;
    private String count;
    private String unit;
    private int page;
    private int pages;
    private List<ListBean> list;

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {
        /**
         * id : 658
         * name : 人教版小学数学1年级上册1 准备课 （第一课时）
         * banben_id : 0
         * nianfen_id : 0
         * xueduan_id : 1
         * xueke_id : 2
         * nianji_id : 1
         * xueqi_id : 1
         * course_id : 52
         * teacherid : 523
         * icon : /uploadfile/vieo/2016-12-30/0198.png
         * clarity : 1
         * fileurl : /uploadfile/vieo/tongbushipin_xiaoxue/rjbxxsx1njsc201201.ts
         * createtime : 0000-00-00 00:00:00
         * status : 1
         * del : 2
         * hits : 0
         * collections : 0
         * content :
         * is_free : 0
         * correlate_id : 80139220cb76df365a71e2570e58d0f5
         * movie_code : f90cb3fc28469be18e66b3f200b68c3b
         * inpouring_status : 2
         * play_url : http://itv.100.ahct.lv1.vcache.cn/4003/rjbxxsx1/rjbxxsx1njsc201201.m3u8
         * volumn_count : 1
         * result_file_url : http://117.71.39.8/chigo/2018/7/20/shichang_1532093479067.xml
         * handle_time : 1532093383
         * url : index.php?m=&c=Index&a=videoPlay&video_id=658
         */

        private String id;
        private String name;
        private String banben_id;
        private String nianfen_id;
        private String xueduan_id;
        private String xueke_id;
        private String nianji_id;
        private String xueqi_id;
        private String course_id;
        private String teacherid;
        private String icon;
        private String clarity;
        private String fileurl;
        private String createtime;
        private String status;
        private String del;
        private String hits;
        private String collections;
        private String content;
        private String is_free;
        private String correlate_id;
        private String movie_code;
        private String inpouring_status;
        private String play_url;
        private String volumn_count;
        private String result_file_url;
        private String handle_time;
        private String url;

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

        public String getBanben_id() {
            return banben_id;
        }

        public void setBanben_id(String banben_id) {
            this.banben_id = banben_id;
        }

        public String getNianfen_id() {
            return nianfen_id;
        }

        public void setNianfen_id(String nianfen_id) {
            this.nianfen_id = nianfen_id;
        }

        public String getXueduan_id() {
            return xueduan_id;
        }

        public void setXueduan_id(String xueduan_id) {
            this.xueduan_id = xueduan_id;
        }

        public String getXueke_id() {
            return xueke_id;
        }

        public void setXueke_id(String xueke_id) {
            this.xueke_id = xueke_id;
        }

        public String getNianji_id() {
            return nianji_id;
        }

        public void setNianji_id(String nianji_id) {
            this.nianji_id = nianji_id;
        }

        public String getXueqi_id() {
            return xueqi_id;
        }

        public void setXueqi_id(String xueqi_id) {
            this.xueqi_id = xueqi_id;
        }

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getTeacherid() {
            return teacherid;
        }

        public void setTeacherid(String teacherid) {
            this.teacherid = teacherid;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getClarity() {
            return clarity;
        }

        public void setClarity(String clarity) {
            this.clarity = clarity;
        }

        public String getFileurl() {
            return fileurl;
        }

        public void setFileurl(String fileurl) {
            this.fileurl = fileurl;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDel() {
            return del;
        }

        public void setDel(String del) {
            this.del = del;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getCollections() {
            return collections;
        }

        public void setCollections(String collections) {
            this.collections = collections;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIs_free() {
            return is_free;
        }

        public void setIs_free(String is_free) {
            this.is_free = is_free;
        }

        public String getCorrelate_id() {
            return correlate_id;
        }

        public void setCorrelate_id(String correlate_id) {
            this.correlate_id = correlate_id;
        }

        public String getMovie_code() {
            return movie_code;
        }

        public void setMovie_code(String movie_code) {
            this.movie_code = movie_code;
        }

        public String getInpouring_status() {
            return inpouring_status;
        }

        public void setInpouring_status(String inpouring_status) {
            this.inpouring_status = inpouring_status;
        }

        public String getPlay_url() {
            return play_url;
        }

        public void setPlay_url(String play_url) {
            this.play_url = play_url;
        }

        public String getVolumn_count() {
            return volumn_count;
        }

        public void setVolumn_count(String volumn_count) {
            this.volumn_count = volumn_count;
        }

        public String getResult_file_url() {
            return result_file_url;
        }

        public void setResult_file_url(String result_file_url) {
            this.result_file_url = result_file_url;
        }

        public String getHandle_time() {
            return handle_time;
        }

        public void setHandle_time(String handle_time) {
            this.handle_time = handle_time;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.banben_id);
            dest.writeString(this.nianfen_id);
            dest.writeString(this.xueduan_id);
            dest.writeString(this.xueke_id);
            dest.writeString(this.nianji_id);
            dest.writeString(this.xueqi_id);
            dest.writeString(this.course_id);
            dest.writeString(this.teacherid);
            dest.writeString(this.icon);
            dest.writeString(this.clarity);
            dest.writeString(this.fileurl);
            dest.writeString(this.createtime);
            dest.writeString(this.status);
            dest.writeString(this.del);
            dest.writeString(this.hits);
            dest.writeString(this.collections);
            dest.writeString(this.content);
            dest.writeString(this.is_free);
            dest.writeString(this.correlate_id);
            dest.writeString(this.movie_code);
            dest.writeString(this.inpouring_status);
            dest.writeString(this.play_url);
            dest.writeString(this.volumn_count);
            dest.writeString(this.result_file_url);
            dest.writeString(this.handle_time);
            dest.writeString(this.url);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.banben_id = in.readString();
            this.nianfen_id = in.readString();
            this.xueduan_id = in.readString();
            this.xueke_id = in.readString();
            this.nianji_id = in.readString();
            this.xueqi_id = in.readString();
            this.course_id = in.readString();
            this.teacherid = in.readString();
            this.icon = in.readString();
            this.clarity = in.readString();
            this.fileurl = in.readString();
            this.createtime = in.readString();
            this.status = in.readString();
            this.del = in.readString();
            this.hits = in.readString();
            this.collections = in.readString();
            this.content = in.readString();
            this.is_free = in.readString();
            this.correlate_id = in.readString();
            this.movie_code = in.readString();
            this.inpouring_status = in.readString();
            this.play_url = in.readString();
            this.volumn_count = in.readString();
            this.result_file_url = in.readString();
            this.handle_time = in.readString();
            this.url = in.readString();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.titel);
        dest.writeString(this.count);
        dest.writeString(this.unit);
        dest.writeInt(this.page);
        dest.writeInt(this.pages);
        dest.writeList(this.list);
    }

    protected NumberOneClassBaseBean(Parcel in) {
        this.titel = in.readString();
        this.count = in.readString();
        this.unit = in.readString();
        this.page = in.readInt();
        this.pages = in.readInt();
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, ListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<NumberOneClassBaseBean> CREATOR = new Parcelable.Creator<NumberOneClassBaseBean>() {
        @Override
        public NumberOneClassBaseBean createFromParcel(Parcel source) {
            return new NumberOneClassBaseBean(source);
        }

        @Override
        public NumberOneClassBaseBean[] newArray(int size) {
            return new NumberOneClassBaseBean[size];
        }
    };
}
