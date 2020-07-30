package example.getlearn.tv.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlw on 2018/11/9.
 */

public class VipBean implements Parcelable {


    /**
     * priceCate : [{"id":"1","first_cate":"任看包"},{"id":"2","first_cate":"幼儿智教"},{"id":"3","first_cate":"专题课堂"},{"id":"4","first_cate":"小学课堂"},{"id":"5","first_cate":"中学课堂"}]
     * priceRes : [{"module_id":"1","first_cate":"任看包","icon":"./Public/Uploads/images/2018-04-20/5ad96c7b910ec.png","price":"28.00","id":"SC100784"},{"module_id":"1","first_cate":"任看包","icon":"","price":"0.00","id":""},{"module_id":"1","first_cate":"任看包","icon":"./Public/Uploads/images/2018-04-19/5ad823b209e1d.png","price":"280.00","id":"gljy010cp@207"}]
     * top : 0
     * act : 1
     * id : 1
     * public_skin_img : ./Public/Uploads/images/2018-08-31/5b88f93d39210.jpg
     */

    private int top;
    private int act;
    private int id;
    private String public_skin_img;
    private String content;
    private List<PriceCateBean> priceCate;
    private List<PriceResBean> priceRes;

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getAct() {
        return act;
    }

    public void setAct(int act) {
        this.act = act;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublic_skin_img() {
        return public_skin_img;
    }

    public void setPublic_skin_img(String public_skin_img) {
        this.public_skin_img = public_skin_img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<PriceCateBean> getPriceCate() {
        return priceCate;
    }

    public void setPriceCate(List<PriceCateBean> priceCate) {
        this.priceCate = priceCate;
    }

    public List<PriceResBean> getPriceRes() {
        return priceRes;
    }

    public void setPriceRes(List<PriceResBean> priceRes) {
        this.priceRes = priceRes;
    }

    public static class PriceCateBean implements Parcelable {
        /**
         * id : 1
         * first_cate : 任看包
         */

        private String id;
        private String first_cate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirst_cate() {
            return first_cate;
        }

        public void setFirst_cate(String first_cate) {
            this.first_cate = first_cate;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.first_cate);
        }

        public PriceCateBean() {
        }

        protected PriceCateBean(Parcel in) {
            this.id = in.readString();
            this.first_cate = in.readString();
        }

        public static final Creator<PriceCateBean> CREATOR = new Creator<PriceCateBean>() {
            @Override
            public PriceCateBean createFromParcel(Parcel source) {
                return new PriceCateBean(source);
            }

            @Override
            public PriceCateBean[] newArray(int size) {
                return new PriceCateBean[size];
            }
        };
    }

    public static class PriceResBean implements Parcelable {
        /**
         * module_id : 1
         * first_cate : 任看包
         * icon : ./Public/Uploads/images/2018-04-20/5ad96c7b910ec.png
         * price : 28.00
         * id : SC100784
         */

        private String module_id;
        private String first_cate;
        private String icon;
        private String price;
        private String productid;
        private String type;
        private String card_isOrder;
        private String product_isOrder;
        private String order_time;

        public String getModule_id() {
            return module_id;
        }

        public void setModule_id(String module_id) {
            this.module_id = module_id;
        }

        public String getFirst_cate() {
            return first_cate;
        }

        public void setFirst_cate(String first_cate) {
            this.first_cate = first_cate;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getProductid() {
            return productid;
        }

        public void setProductid(String productid) {
            this.productid = productid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCard_isOrder() {
            return card_isOrder;
        }

        public void setCard_isOrder(String card_isOrder) {
            this.card_isOrder = card_isOrder;
        }

        public String getProduct_isOrder() {
            return product_isOrder;
        }

        public void setProduct_isOrder(String product_isOrder) {
            this.product_isOrder = product_isOrder;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.module_id);
            dest.writeString(this.first_cate);
            dest.writeString(this.icon);
            dest.writeString(this.price);
            dest.writeString(this.productid);
            dest.writeString(this.type);
            dest.writeString(this.card_isOrder);
            dest.writeString(this.product_isOrder);
            dest.writeString(this.order_time);
        }

        public PriceResBean() {
        }

        protected PriceResBean(Parcel in) {
            this.module_id = in.readString();
            this.first_cate = in.readString();
            this.icon = in.readString();
            this.price = in.readString();
            this.productid = in.readString();
            this.type = in.readString();
            this.card_isOrder = in.readString();
            this.product_isOrder = in.readString();
            this.order_time = in.readString();
        }

        public static final Creator<PriceResBean> CREATOR = new Creator<PriceResBean>() {
            @Override
            public PriceResBean createFromParcel(Parcel source) {
                return new PriceResBean(source);
            }

            @Override
            public PriceResBean[] newArray(int size) {
                return new PriceResBean[size];
            }
        };

        @Override
        public String toString() {
            return "PriceResBean{" +
                    "module_id='" + module_id + '\'' +
                    ", first_cate='" + first_cate + '\'' +
                    ", icon='" + icon + '\'' +
                    ", price='" + price + '\'' +
                    ", productid='" + productid + '\'' +
                    ", type='" + type + '\'' +
                    ", card_isOrder='" + card_isOrder + '\'' +
                    ", product_isOrder='" + product_isOrder + '\'' +
                    ", order_time='" + order_time + '\'' +
                    '}';
        }
    }

    public VipBean() {
        super();
    }

    @Override
    public String toString() {
        return "VipBean{" +
                "top=" + top +
                ", act=" + act +
                ", id=" + id +
                ", public_skin_img='" + public_skin_img + '\'' +
                ", content=" + content +
                ", priceCate=" + priceCate +
                ", priceRes=" + priceRes +
                '}';
    }

    public VipBean(int top, int act, int id, String public_skin_img, String content, List<PriceCateBean> priceCate, List<PriceResBean> priceRes) {
        this.top = top;
        this.act = act;
        this.id = id;
        this.public_skin_img = public_skin_img;
        this.content = content;
        this.priceCate = priceCate;
        this.priceRes = priceRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.top);
        dest.writeInt(this.act);
        dest.writeInt(this.id);
        dest.writeString(this.public_skin_img);
        dest.writeString(this.content);
        dest.writeList(this.priceCate);
        dest.writeList(this.priceRes);
    }

    protected VipBean(Parcel in) {
        this.top = in.readInt();
        this.act = in.readInt();
        this.id = in.readInt();
        this.public_skin_img = in.readString();
        this.content = in.readString();
        this.priceCate = new ArrayList<PriceCateBean>();
        in.readList(this.priceCate, PriceCateBean.class.getClassLoader());
        this.priceRes = new ArrayList<PriceResBean>();
        in.readList(this.priceRes, PriceResBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<VipBean> CREATOR = new Parcelable.Creator<VipBean>() {
        @Override
        public VipBean createFromParcel(Parcel source) {
            return new VipBean(source);
        }

        @Override
        public VipBean[] newArray(int size) {
            return new VipBean[size];
        }
    };
}
