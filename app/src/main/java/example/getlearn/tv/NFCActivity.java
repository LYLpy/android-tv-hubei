package example.getlearn.tv;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.maywide.gdpay.GDApi;
import com.maywide.gdpay.PayContent;
import com.maywide.gdpay.PayReq;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.getlearn.tv.util.MD5Utils;
import example.getlearn.tv.util.SysUtil;

public class NFCActivity extends AppCompatActivity {
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private PayContent.OrderInfo orderInfo;
    private PayContent.CustInfo custInfo;
    private PayContent.OrderInfo.Product product;
    private PayContent payContent;
    @BindView(R.id.ac_nfc)
    TextView acNfc;

    public static void starActivity(Activity activity) {
        Intent intent = new Intent(activity, NFCActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        ButterKnife.bind(this);
        acNfc.setText(SysUtil.getSystemProperties(NFCActivity.this,SysUtil.SYSKEY_HB_SMART_CARD));
        Toast.makeText(this, SysUtil.getSystemProperties(NFCActivity.this,SysUtil.SYSKEY_HB_SMART_CARD), Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent();
//        intent.setAction("com.hrtn.action.smatcard.id.update");
////        intent.putExtra("smcartid", cardInfo.getCardOutID());
//        intent.setFlags(Intent.FLAG_RECEIVER_FOREGROUND);
//        sendBroadcast(intent);
        acNfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                custInfo = new PayContent.CustInfo();
                custInfo.setCardNo(SysUtil.getSystemProperties(NFCActivity.this,SysUtil.SYSKEY_HB_SMART_CARD));
                custInfo.setCity("WH");

                product = new PayContent.OrderInfo.Product();
                product.setFee(0.01);
                product.setKeyno(SysUtil.getSystemProperties(NFCActivity.this,SysUtil.SYSKEY_HB_SMART_CARD));
                product.setProductName("testPro3");
                product.setUnit("1");
                product.setUnit_price(0.01);
                product.setCount("3");

                orderInfo = new PayContent.OrderInfo();
                orderInfo.setOrderNo("111111999");
                List<PayContent.OrderInfo.Product> list = new ArrayList<>();
                list.add(product);
                orderInfo.setProductList(list);

                payContent = new PayContent();
                payContent.setIsOrder("N");
                payContent.setDataSign(MD5Utils.encode("10332018121109999999"+"GLJYM1d2c3").toUpperCase());
                payContent.setNoticeAction("http://youth.getlearn.net/");
                payContent.setTotalFee(0.01);
                payContent.setCustInfo(custInfo);
                payContent.setOrderInfo(orderInfo);

                PayReq payReq = new PayReq();
                payReq.setCitycode("WH");
                payReq.setClientcode("1033");
                payReq.setClientpwd("2t4c16e6f2bk13mp349f561gr6l4d31t");
                payReq.setRequestid("10332018121109999999");
                payReq.setPayContent(payContent);
                GDApi.sendReq(NFCActivity.this,payReq);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.i("tag-content:","YES");
        if (requestCode == GDApi.REQUESTCODE &&
                resultCode == GDApi.RESULTCODE) {
            Bundle bundle = data.getExtras();
//拿到订单号
            String orderNo = bundle.getString("orderNo");
//拿到支付结果  0代表支付成功,1代表支付失败，2代表取消订单
            int payResultCode = bundle.getInt("resultCode",-1);
//拿到页面通知地址
            String redirectUrl = bundle.getString("redirectUrl");
//拿到信息描述
            String payInfo = bundle.getString("payInfo");

//            Log.i("orderNo:",orderNo);
//            Log.i("payResultCode:", String.valueOf(payResultCode));
//            Log.i("redirectUrl:",redirectUrl);
//            Log.i("payInfo:",payInfo);
        }
    }
}

