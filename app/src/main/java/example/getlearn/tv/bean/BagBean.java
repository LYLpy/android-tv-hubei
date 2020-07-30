package example.getlearn.tv.bean;

/**
 * Created by zlw on 2018/11/29.
 */

public class BagBean {
    private String bag_imag_url_home1;

    public BagBean() {
        super();
    }

    @Override
    public String toString() {
        return "BagBean{" +
                "bag_imag_url_home1='" + bag_imag_url_home1 + '\'' +
                '}';
    }

    public String getBag_imag_url_home1() {
        return bag_imag_url_home1;
    }

    public void setBag_imag_url_home1(String bag_imag_url_home1) {
        this.bag_imag_url_home1 = bag_imag_url_home1;
    }

    public BagBean(String bag_imag_url_home1) {
        this.bag_imag_url_home1 = bag_imag_url_home1;
    }
}
