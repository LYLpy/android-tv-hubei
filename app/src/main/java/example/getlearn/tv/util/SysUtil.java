package example.getlearn.tv.util;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Project GeLingTV_GD
 * Created by OCWVAR
 * On 17-9-22 下午1:30
 * File Location utils
 */
public class SysUtil {

    /**
     * 湖北广电机顶盒智能卡号
     */
    public static final String SYSKEY_HB_SMART_CARD = "persist.sys.mmcp.smartcard";

    /**
     * 获取系统属性（String类型）
     *
     * @return 如果不存在该key则返回空字符串
     * @throws IllegalArgumentException 如果key超过32个字符则抛出该异常
     */
    public static String getSystemProperties(Context context, String key){
        try {
            final ClassLoader cl = context.getClassLoader();

            @SuppressWarnings("rawtypes")
            final Class SystemProperties = cl.loadClass("android.os.SystemProperties");

            @SuppressWarnings("rawtypes")
            final Class[] paramTypes = new Class[1];
            paramTypes[0] = String.class;

            final Method get = SystemProperties.getMethod("get", paramTypes);

            final Object[] params = new Object[1];
            params[0] = key;

            return (String) get.invoke(SystemProperties, params);
        }catch (Exception e) {
            return null;
        }
    }

    public static String getCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }


    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }


    /**
     * 得到有限网关的IP地址
     * @return
     */
    public static String getLocalIp() {
        try {
            // 获取本地设备的所有网络接口
            Enumeration<NetworkInterface> enumerationNi = NetworkInterface.getNetworkInterfaces();
            while (enumerationNi.hasMoreElements()) {
                NetworkInterface networkInterface = enumerationNi.nextElement();
                String interfaceName = networkInterface.getDisplayName();
//                Log.i("tag-netName", "网络名字：" + interfaceName);
                // 如果是有限网卡
                if (interfaceName.equals("eth0")) {
                    Enumeration<InetAddress> enumIpAddr = networkInterface.getInetAddresses();
                    while (enumIpAddr.hasMoreElements()) {
                        // 返回枚举集合中的下一个IP地址信息
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        // 不是回环地址，并且是ipv4的地址
                        if (!inetAddress.isLoopbackAddress()
                                && inetAddress instanceof Inet4Address) {
//                            Log.i("tag-ipAddr", "ip地址："+inetAddress.getHostAddress());
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    //获取mac地址
    public static String getMacAddress(){
        String strMacAddr = null;
        try {
            if(getLocalIp().isEmpty()){
                return "";
            }
            InetAddress ip = InetAddress.getByName(getLocalIp());

            byte[] b = NetworkInterface.getByInetAddress(ip)
                    .getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i]&0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Log.i("tag-mac", "getMacAddress: "+strMacAddr);

        return strMacAddr;
    }

}
