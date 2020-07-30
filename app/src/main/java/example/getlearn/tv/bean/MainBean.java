package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zlw on 2018/11/9.
 */

public class MainBean implements Parcelable {
    public MainBean(DataBean data, String public_skin_img) {
        this.data = data;
        this.public_skin_img = public_skin_img;
    }

    @Override
    public String toString() {
        return "MainBean{" +
                "data=" + data +
                ", public_skin_img='" + public_skin_img + '\'' +
                '}';
    }

    public MainBean() {
        super();
    }

    /**
     * data : {"home1":{"id":"1","template_id":"1","name":"VIP订购","keyname":"home1","icon":"/Public/Uploads/images/5b8c9fd858ffe.png","bgimg":"/Public/Uploads/images/5b8f8ecc38ee0.jpg","tourl":"/index.php?m=Index&c=pay&a=index&act=1"},"home2":{"id":"2","template_id":"1","name":"观看记录","keyname":"home2","icon":"/Public/Uploads/images/5b850f8645764.png","bgimg":"/Public/Uploads/images/5b8ce82eeb604.jpg","tourl":"/index.php?m=Index&c=Index&a=record"},"home3":{"id":"3","template_id":"1","name":"推荐位1","keyname":"home3","icon":["/Public/Uploads/images/5b86544a999ba.png","/Public/Uploads/images/5b86544fef9ec.png","/Public/Uploads/images/5b8654564972d.png"],"bgimg":"","tourl":["/index.php?a=courseInfo&type=course&id=935","/index.php?a=courseList&type=module&id=4","/index.php?a=courseInfo&type=course&id=950"]},"home4":{"id":"4","template_id":"1","name":"推荐位2","keyname":"home4","icon":"/Public/Uploads/images/5b97371a6bd00.png","bgimg":"/Public/Uploads/images/5b9f28b53ef2b.png","tourl":"/index.php?a=courseInfo&type=category&id=72"},"home5":{"id":"5","template_id":"1","name":"推荐位3","keyname":"home5","icon":"/Public/Uploads/images/5b973722011f8.png","bgimg":"/Public/Uploads/images/5b8debac8e878.png","tourl":"/index.php?a=courseInfo&type=course&id=1008"},"home6":{"id":"6","template_id":"1","name":"搜索","keyname":"home6","icon":"/Public/Uploads/images/5b85105137733.png","bgimg":"/Public/Uploads/images/5b8ce146a29f4.png","tourl":"/index.php?m=Index&c=pay&a=sreach"},"home7":{"id":"7","template_id":"1","name":"我的收藏","keyname":"home7","icon":"/Public/Uploads/images/5b85105cedfcb.png","bgimg":"/Public/Uploads/images/5b8ce89e37334.png","tourl":"/index.php?m=Index&c=Index&a=collection"},"home8":{"id":"8","template_id":"1","name":"专题1","keyname":"home8","icon":"/Public/Uploads/images/5b97369cf11d0.png","bgimg":"/Public/Uploads/images/5b8df2517448c.png","tourl":"/index.php?m=Index&c=index&a=pschool&is_akb=8"},"home9":{"id":"9","template_id":"1","name":"专题2","keyname":"home9","icon":"/Public/Uploads/images/5b850ff70abbd.png","bgimg":"/Public/Uploads/images/5b8df3d2c70c4.png","tourl":"/index.php?a=courseList&type=module&id=4&is_akb=9"},"home10":{"id":"10","template_id":"1","name":"专题3","keyname":"home10","icon":"/Public/Uploads/images/5b9736bb63830.png","bgimg":"/Public/Uploads/images/5b8df3e580fac.jpg","tourl":"/index.php?m=Index&c=index&a=getTopicDetail&topic_id=5"},"home11":{"id":"11","template_id":"1","name":"专题4","keyname":"home11","icon":"/Public/Uploads/images/5b9736e253660.png","bgimg":"/Public/Uploads/images/5b8df3ffb827c.jpg","tourl":"/index.php?a=courseInfo&type=category&id=56"},"logo":{"id":"14","template_id":"1","name":"logo","keyname":"logo","icon":"/Public/Uploads/images/5b866625ab8a4.png","bgimg":"","tourl":"1"}}
     * public_skin_img : ./Public/Uploads/images/2018-08-31/5b88f93d39210.jpg
     */

    private DataBean data;
    private String public_skin_img;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getPublic_skin_img() {
        return public_skin_img;
    }

    public void setPublic_skin_img(String public_skin_img) {
        this.public_skin_img = public_skin_img;
    }

    public static class DataBean implements Parcelable {
        /**
         * home1 : {"id":"1","template_id":"1","name":"VIP订购","keyname":"home1","icon":"/Public/Uploads/images/5b8c9fd858ffe.png","bgimg":"/Public/Uploads/images/5b8f8ecc38ee0.jpg","tourl":"/index.php?m=Index&c=pay&a=index&act=1"}
         * home2 : {"id":"2","template_id":"1","name":"观看记录","keyname":"home2","icon":"/Public/Uploads/images/5b850f8645764.png","bgimg":"/Public/Uploads/images/5b8ce82eeb604.jpg","tourl":"/index.php?m=Index&c=Index&a=record"}
         * home3 : {"id":"3","template_id":"1","name":"推荐位1","keyname":"home3","icon":["/Public/Uploads/images/5b86544a999ba.png","/Public/Uploads/images/5b86544fef9ec.png","/Public/Uploads/images/5b8654564972d.png"],"bgimg":"","tourl":["/index.php?a=courseInfo&type=course&id=935","/index.php?a=courseList&type=module&id=4","/index.php?a=courseInfo&type=course&id=950"]}
         * home4 : {"id":"4","template_id":"1","name":"推荐位2","keyname":"home4","icon":"/Public/Uploads/images/5b97371a6bd00.png","bgimg":"/Public/Uploads/images/5b9f28b53ef2b.png","tourl":"/index.php?a=courseInfo&type=category&id=72"}
         * home5 : {"id":"5","template_id":"1","name":"推荐位3","keyname":"home5","icon":"/Public/Uploads/images/5b973722011f8.png","bgimg":"/Public/Uploads/images/5b8debac8e878.png","tourl":"/index.php?a=courseInfo&type=course&id=1008"}
         * home6 : {"id":"6","template_id":"1","name":"搜索","keyname":"home6","icon":"/Public/Uploads/images/5b85105137733.png","bgimg":"/Public/Uploads/images/5b8ce146a29f4.png","tourl":"/index.php?m=Index&c=pay&a=sreach"}
         * home7 : {"id":"7","template_id":"1","name":"我的收藏","keyname":"home7","icon":"/Public/Uploads/images/5b85105cedfcb.png","bgimg":"/Public/Uploads/images/5b8ce89e37334.png","tourl":"/index.php?m=Index&c=Index&a=collection"}
         * home8 : {"id":"8","template_id":"1","name":"专题1","keyname":"home8","icon":"/Public/Uploads/images/5b97369cf11d0.png","bgimg":"/Public/Uploads/images/5b8df2517448c.png","tourl":"/index.php?m=Index&c=index&a=pschool&is_akb=8"}
         * home9 : {"id":"9","template_id":"1","name":"专题2","keyname":"home9","icon":"/Public/Uploads/images/5b850ff70abbd.png","bgimg":"/Public/Uploads/images/5b8df3d2c70c4.png","tourl":"/index.php?a=courseList&type=module&id=4&is_akb=9"}
         * home10 : {"id":"10","template_id":"1","name":"专题3","keyname":"home10","icon":"/Public/Uploads/images/5b9736bb63830.png","bgimg":"/Public/Uploads/images/5b8df3e580fac.jpg","tourl":"/index.php?m=Index&c=index&a=getTopicDetail&topic_id=5"}
         * home11 : {"id":"11","template_id":"1","name":"专题4","keyname":"home11","icon":"/Public/Uploads/images/5b9736e253660.png","bgimg":"/Public/Uploads/images/5b8df3ffb827c.jpg","tourl":"/index.php?a=courseInfo&type=category&id=56"}
         * logo : {"id":"14","template_id":"1","name":"logo","keyname":"logo","icon":"/Public/Uploads/images/5b866625ab8a4.png","bgimg":"","tourl":"1"}
         */

        private Home1Bean home1;
        private Home2Bean home2;
        private Home3Bean home3;
        private Home4Bean home4;
        private Home5Bean home5;
        private Home6Bean home6;
        private Home7Bean home7;
        private Home8Bean home8;
        private Home9Bean home9;
        private Home10Bean home10;
        private Home11Bean home11;
        private LogoBean logo;

        public Home1Bean getHome1() {
            return home1;
        }

        public void setHome1(Home1Bean home1) {
            this.home1 = home1;
        }

        public Home2Bean getHome2() {
            return home2;
        }

        public void setHome2(Home2Bean home2) {
            this.home2 = home2;
        }

        public Home3Bean getHome3() {
            return home3;
        }

        public void setHome3(Home3Bean home3) {
            this.home3 = home3;
        }

        public Home4Bean getHome4() {
            return home4;
        }

        public void setHome4(Home4Bean home4) {
            this.home4 = home4;
        }

        public Home5Bean getHome5() {
            return home5;
        }

        public void setHome5(Home5Bean home5) {
            this.home5 = home5;
        }

        public Home6Bean getHome6() {
            return home6;
        }

        public void setHome6(Home6Bean home6) {
            this.home6 = home6;
        }

        public Home7Bean getHome7() {
            return home7;
        }

        public void setHome7(Home7Bean home7) {
            this.home7 = home7;
        }

        public Home8Bean getHome8() {
            return home8;
        }

        public void setHome8(Home8Bean home8) {
            this.home8 = home8;
        }

        public Home9Bean getHome9() {
            return home9;
        }

        public void setHome9(Home9Bean home9) {
            this.home9 = home9;
        }

        public Home10Bean getHome10() {
            return home10;
        }

        public void setHome10(Home10Bean home10) {
            this.home10 = home10;
        }

        public Home11Bean getHome11() {
            return home11;
        }

        public void setHome11(Home11Bean home11) {
            this.home11 = home11;
        }

        public LogoBean getLogo() {
            return logo;
        }

        public void setLogo(LogoBean logo) {
            this.logo = logo;
        }

        public static class Home1Bean implements Parcelable {
            /**
             * id : 1
             * template_id : 1
             * name : VIP订购
             * keyname : home1
             * icon : /Public/Uploads/images/5b8c9fd858ffe.png
             * bgimg : /Public/Uploads/images/5b8f8ecc38ee0.jpg
             * tourl : /index.php?m=Index&c=pay&a=index&act=1
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public Home1Bean() {
            }

            protected Home1Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<Home1Bean> CREATOR = new Creator<Home1Bean>() {
                @Override
                public Home1Bean createFromParcel(Parcel source) {
                    return new Home1Bean(source);
                }

                @Override
                public Home1Bean[] newArray(int size) {
                    return new Home1Bean[size];
                }
            };
        }

        public static class Home2Bean implements Parcelable {
            /**
             * id : 2
             * template_id : 1
             * name : 观看记录
             * keyname : home2
             * icon : /Public/Uploads/images/5b850f8645764.png
             * bgimg : /Public/Uploads/images/5b8ce82eeb604.jpg
             * tourl : /index.php?m=Index&c=Index&a=record
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public Home2Bean() {
            }

            protected Home2Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<Home2Bean> CREATOR = new Creator<Home2Bean>() {
                @Override
                public Home2Bean createFromParcel(Parcel source) {
                    return new Home2Bean(source);
                }

                @Override
                public Home2Bean[] newArray(int size) {
                    return new Home2Bean[size];
                }
            };
        }

        public static class Home3Bean implements Parcelable {
            /**
             * id : 3
             * template_id : 1
             * name : 推荐位1
             * keyname : home3
             * icon : ["/Public/Uploads/images/5b86544a999ba.png","/Public/Uploads/images/5b86544fef9ec.png","/Public/Uploads/images/5b8654564972d.png"]
             * bgimg :
             * tourl : ["/index.php?a=courseInfo&type=course&id=935","/index.php?a=courseList&type=module&id=4","/index.php?a=courseInfo&type=course&id=950"]
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String bgimg;
            private List<String> icon;
            private List<String> tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public List<String> getIcon() {
                return icon;
            }

            public void setIcon(List<String> icon) {
                this.icon = icon;
            }

            public List<String> getTourl() {
                return tourl;
            }

            public void setTourl(List<String> tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.bgimg);
                dest.writeStringList(this.icon);
                dest.writeStringList(this.tourl);
            }

            public Home3Bean() {
            }

            protected Home3Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.bgimg = in.readString();
                this.icon = in.createStringArrayList();
                this.tourl = in.createStringArrayList();
            }

            public static final Creator<Home3Bean> CREATOR = new Creator<Home3Bean>() {
                @Override
                public Home3Bean createFromParcel(Parcel source) {
                    return new Home3Bean(source);
                }

                @Override
                public Home3Bean[] newArray(int size) {
                    return new Home3Bean[size];
                }
            };
        }

        public static class Home4Bean implements Parcelable {
            /**
             * id : 4
             * template_id : 1
             * name : 推荐位2
             * keyname : home4
             * icon : /Public/Uploads/images/5b97371a6bd00.png
             * bgimg : /Public/Uploads/images/5b9f28b53ef2b.png
             * tourl : /index.php?a=courseInfo&type=category&id=72
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public Home4Bean() {
            }

            protected Home4Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<Home4Bean> CREATOR = new Creator<Home4Bean>() {
                @Override
                public Home4Bean createFromParcel(Parcel source) {
                    return new Home4Bean(source);
                }

                @Override
                public Home4Bean[] newArray(int size) {
                    return new Home4Bean[size];
                }
            };
        }

        public static class Home5Bean implements Parcelable {
            /**
             * id : 5
             * template_id : 1
             * name : 推荐位3
             * keyname : home5
             * icon : /Public/Uploads/images/5b973722011f8.png
             * bgimg : /Public/Uploads/images/5b8debac8e878.png
             * tourl : /index.php?a=courseInfo&type=course&id=1008
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public Home5Bean() {
            }

            protected Home5Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<Home5Bean> CREATOR = new Creator<Home5Bean>() {
                @Override
                public Home5Bean createFromParcel(Parcel source) {
                    return new Home5Bean(source);
                }

                @Override
                public Home5Bean[] newArray(int size) {
                    return new Home5Bean[size];
                }
            };
        }

        public static class Home6Bean implements Parcelable {
            /**
             * id : 6
             * template_id : 1
             * name : 搜索
             * keyname : home6
             * icon : /Public/Uploads/images/5b85105137733.png
             * bgimg : /Public/Uploads/images/5b8ce146a29f4.png
             * tourl : /index.php?m=Index&c=pay&a=sreach
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public Home6Bean() {
            }

            protected Home6Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<Home6Bean> CREATOR = new Creator<Home6Bean>() {
                @Override
                public Home6Bean createFromParcel(Parcel source) {
                    return new Home6Bean(source);
                }

                @Override
                public Home6Bean[] newArray(int size) {
                    return new Home6Bean[size];
                }
            };
        }

        public static class Home7Bean implements Parcelable {
            /**
             * id : 7
             * template_id : 1
             * name : 我的收藏
             * keyname : home7
             * icon : /Public/Uploads/images/5b85105cedfcb.png
             * bgimg : /Public/Uploads/images/5b8ce89e37334.png
             * tourl : /index.php?m=Index&c=Index&a=collection
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public Home7Bean() {
            }

            protected Home7Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<Home7Bean> CREATOR = new Creator<Home7Bean>() {
                @Override
                public Home7Bean createFromParcel(Parcel source) {
                    return new Home7Bean(source);
                }

                @Override
                public Home7Bean[] newArray(int size) {
                    return new Home7Bean[size];
                }
            };
        }

        public static class Home8Bean implements Parcelable {
            /**
             * id : 8
             * template_id : 1
             * name : 专题1
             * keyname : home8
             * icon : /Public/Uploads/images/5b97369cf11d0.png
             * bgimg : /Public/Uploads/images/5b8df2517448c.png
             * tourl : /index.php?m=Index&c=index&a=pschool&is_akb=8
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public Home8Bean() {
            }

            protected Home8Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<Home8Bean> CREATOR = new Creator<Home8Bean>() {
                @Override
                public Home8Bean createFromParcel(Parcel source) {
                    return new Home8Bean(source);
                }

                @Override
                public Home8Bean[] newArray(int size) {
                    return new Home8Bean[size];
                }
            };
        }

        public static class Home9Bean implements Parcelable {
            /**
             * id : 9
             * template_id : 1
             * name : 专题2
             * keyname : home9
             * icon : /Public/Uploads/images/5b850ff70abbd.png
             * bgimg : /Public/Uploads/images/5b8df3d2c70c4.png
             * tourl : /index.php?a=courseList&type=module&id=4&is_akb=9
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public Home9Bean() {
            }

            protected Home9Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<Home9Bean> CREATOR = new Creator<Home9Bean>() {
                @Override
                public Home9Bean createFromParcel(Parcel source) {
                    return new Home9Bean(source);
                }

                @Override
                public Home9Bean[] newArray(int size) {
                    return new Home9Bean[size];
                }
            };
        }

        public static class Home10Bean implements Parcelable {
            /**
             * id : 10
             * template_id : 1
             * name : 专题3
             * keyname : home10
             * icon : /Public/Uploads/images/5b9736bb63830.png
             * bgimg : /Public/Uploads/images/5b8df3e580fac.jpg
             * tourl : /index.php?m=Index&c=index&a=getTopicDetail&topic_id=5
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public Home10Bean() {
            }

            protected Home10Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<Home10Bean> CREATOR = new Creator<Home10Bean>() {
                @Override
                public Home10Bean createFromParcel(Parcel source) {
                    return new Home10Bean(source);
                }

                @Override
                public Home10Bean[] newArray(int size) {
                    return new Home10Bean[size];
                }
            };
        }

        public static class Home11Bean implements Parcelable {
            /**
             * id : 11
             * template_id : 1
             * name : 专题4
             * keyname : home11
             * icon : /Public/Uploads/images/5b9736e253660.png
             * bgimg : /Public/Uploads/images/5b8df3ffb827c.jpg
             * tourl : /index.php?a=courseInfo&type=category&id=56
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public Home11Bean() {
            }

            protected Home11Bean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<Home11Bean> CREATOR = new Creator<Home11Bean>() {
                @Override
                public Home11Bean createFromParcel(Parcel source) {
                    return new Home11Bean(source);
                }

                @Override
                public Home11Bean[] newArray(int size) {
                    return new Home11Bean[size];
                }
            };
        }

        public static class LogoBean implements Parcelable {
            /**
             * id : 14
             * template_id : 1
             * name : logo
             * keyname : logo
             * icon : /Public/Uploads/images/5b866625ab8a4.png
             * bgimg :
             * tourl : 1
             */

            private String id;
            private String template_id;
            private String name;
            private String keyname;
            private String icon;
            private String bgimg;
            private String tourl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBgimg() {
                return bgimg;
            }

            public void setBgimg(String bgimg) {
                this.bgimg = bgimg;
            }

            public String getTourl() {
                return tourl;
            }

            public void setTourl(String tourl) {
                this.tourl = tourl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.template_id);
                dest.writeString(this.name);
                dest.writeString(this.keyname);
                dest.writeString(this.icon);
                dest.writeString(this.bgimg);
                dest.writeString(this.tourl);
            }

            public LogoBean() {
            }

            protected LogoBean(Parcel in) {
                this.id = in.readString();
                this.template_id = in.readString();
                this.name = in.readString();
                this.keyname = in.readString();
                this.icon = in.readString();
                this.bgimg = in.readString();
                this.tourl = in.readString();
            }

            public static final Creator<LogoBean> CREATOR = new Creator<LogoBean>() {
                @Override
                public LogoBean createFromParcel(Parcel source) {
                    return new LogoBean(source);
                }

                @Override
                public LogoBean[] newArray(int size) {
                    return new LogoBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.home1, flags);
            dest.writeParcelable(this.home2, flags);
            dest.writeParcelable(this.home3, flags);
            dest.writeParcelable(this.home4, flags);
            dest.writeParcelable(this.home5, flags);
            dest.writeParcelable(this.home6, flags);
            dest.writeParcelable(this.home7, flags);
            dest.writeParcelable(this.home8, flags);
            dest.writeParcelable(this.home9, flags);
            dest.writeParcelable(this.home10, flags);
            dest.writeParcelable(this.home11, flags);
            dest.writeParcelable(this.logo, flags);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.home1 = in.readParcelable(Home1Bean.class.getClassLoader());
            this.home2 = in.readParcelable(Home2Bean.class.getClassLoader());
            this.home3 = in.readParcelable(Home3Bean.class.getClassLoader());
            this.home4 = in.readParcelable(Home4Bean.class.getClassLoader());
            this.home5 = in.readParcelable(Home5Bean.class.getClassLoader());
            this.home6 = in.readParcelable(Home6Bean.class.getClassLoader());
            this.home7 = in.readParcelable(Home7Bean.class.getClassLoader());
            this.home8 = in.readParcelable(Home8Bean.class.getClassLoader());
            this.home9 = in.readParcelable(Home9Bean.class.getClassLoader());
            this.home10 = in.readParcelable(Home10Bean.class.getClassLoader());
            this.home11 = in.readParcelable(Home11Bean.class.getClassLoader());
            this.logo = in.readParcelable(LogoBean.class.getClassLoader());
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.public_skin_img);
    }

    protected MainBean(Parcel in) {
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.public_skin_img = in.readString();
    }

    public static final Parcelable.Creator<MainBean> CREATOR = new Parcelable.Creator<MainBean>() {
        @Override
        public MainBean createFromParcel(Parcel source) {
            return new MainBean(source);
        }

        @Override
        public MainBean[] newArray(int size) {
            return new MainBean[size];
        }
    };
}
