package example.getlearn.tv.util;

/**
 * Created by zlw on 2018/11/9.
 */

public class NetworkInterfaceUtils {
    //图片地址域名（本地调试环境）
//    public static String InterFace_Imag = "http://hb.cn/";
//    public static String InterFace_Imag = "http://msyk.getlearn.cn/";
    //图片地址域名（湖北生产环境）
    public static String InterFace_Imag = "http://172.31.137.10/";

    //图片地址前缀
    public static String HTTP_Imag = "/Public/Uploads/";

    //BOSS接口请求格式
    public static String BOSS_HTTP_FORMAT = "http://172.31.252.206:80/sdpweb/integrate/integate!bossRequest";

    //数据请求域名（本地调试环境）
//    public static String HTTP_NAME = "http://hb.cn/index.php?";
//    public static String HTTP_NAME = "http://msyk.getlearn.cn/index.php?";
    //数据请求域名（湖北生产环境）
    public static String HTTP_NAME = "http://172.31.137.10/index.php?";

    //测试用keyNo（此变量只适用于测试调试）
    public static String Test_KeyNo = "8270102537330896";
    //当isTest = true时,请求数据中的KeyNo（智能卡号）的值将为Test_KeyNo中定义的测试值（此变量只适用于测试调试）
    public static boolean isTest = true;

    /*************************************************************************************/
    //首页     请求模式：post       参数： os=android     http://msyk.getlearn.cn/index.php=?m=Index&c=index&a=index
    public static String InterFace_Home = HTTP_NAME + "m=Index&c=index&a=index";

    //vip  &os=android         请求模式：post       参数： os=android         id=1
    public static String InterFace_Vip = HTTP_NAME + "m=Index&c=pay&a=index&act=1";

    //    获取观看记录页面数据接口  请求模式：post       参数： os=android      p=1      http://msyk.getlearn.cn/index.php?m=Index&c=Index&a=record
    public static String InterFace_VWatchTheRecord = HTTP_NAME + "m=Index&c=Index&a=record";
    //   记录观看记录
    public static String InteFace_SetWatchRecord = HTTP_NAME + "m=Index&c=Index&a=addPlayVideo";

    //    获取搜索页面数据接口    请求模式：post       参数： os=android      p=1
    public static String InterFace_SearchAc = HTTP_NAME + "m=Index&c=pay&a=sreach";

    //    获取我的收藏页面数据接口          请求模式：post       参数： os=android
    public static String InterFace_MyCollection = HTTP_NAME + "m=Index&c=Index&a=collection";

    //    搜索   请求模式：post       参数： os=android      p=1     title=BB
    public static String InterFace_SearchListData = HTTP_NAME + "c=pay&a=AjaxSpecial2";

    //    秒解教材   请求模式：post       参数： os=android    http://msyk.getlearn.cn/index.php?m=Index&c=index&a=pschool&is_akb=8
    public static String InterFace_SecondSolution = HTTP_NAME + "m=Index&c=index&a=pschool&is_akb=8";
    //    秒解教材_内容   请求模式：post       参数： os=android  cid=67     p=1  http://msyk.getlearn.cn/index.php?m=Index&c=index&a=pschool
    public static String InterFace_SecondSolution_listData = HTTP_NAME + "m=Index&c=index&a=pschool";

    //    小学课堂A   请求模式：post       参数： os=android     http://msyk.getlearn.cn/index.php?a=courseList&type=module&id=4&is_akb=9
    public static String InterFace_Home_09_A = HTTP_NAME + "a=courseList&type=module&id=";
    public static String InterFace_Home_09_A_x = "&is_akb=9";
    //    小学课堂B   请求模式：post       参数： os=android  nianji_id=1  xueke_id=1   http://msyk.getlearn.cn/index.php?m=&c=Index&a=ajaxGetCourse
    public static String InterFace_Home_09_B = HTTP_NAME + "m=&c=Index&a=ajaxGetCourse";

    //    三级页面  请求模式：post       参数： os=android  type=course  p=1    id=38   http://msyk.getlearn.cn/index.php?m=&c=Index&a=courseInfo
    public static String InterFace_Three_level = HTTP_NAME + "m=&c=Index&a=courseInfo";
    //    三级页面_点赞接口  请求模式：post       参数： os=android  id=38   http://msyk.getlearn.cn/index.php?m=&c=Index&a=praise
    public static String InterFace_Three_level_Fabulous = HTTP_NAME + "m=&c=Index&a=praise";
    //    三级页面_收藏接口  请求模式：post       参数： os=android  id=38   http://msyk.getlearn.cn/index.php?m=&c=Index&a=Collection_course
    public static String InterFace_Three_level_Hide = HTTP_NAME + "m=&c=Index&a=Collection_course";
    //    三级页面_上册or下册接口  请求模式：post       参数： os=android  xueke_id=1  type=course   p=1    id=38  http://msyk.getlearn.cn/index.php?m=&c=Index&a=courseInfo
    public static String InterFace_Three_level_book = HTTP_NAME + "m=&c=Index&a=courseInfo";


    //    状元课堂_imag数据  请求模式：post   参数： os=android  topic_id=1     http://msyk.getlearn.cn/index.php?m=Index&c=index&a=getTopicDetail
    public static String InterFace_Data_01_list_title = HTTP_NAME + "m=Index&c=index&a=getTopicDetail";
    //    状元课堂_列表  请求模式：post        参数： os=android  page=1  type=22   pageSize=1    lid=38  http://msyk.getlearn.cn/index.php?m=Index&c=Topic&a=index
    public static String InterFace_Data_01_list_imag= HTTP_NAME + "m=Index&c=Topic&a=index";

    //    收藏页面_全部删除按钮  请求模式：post        参数： 无     http://msyk.getlearn.cn/index.php?m=Index&c=index&a=del_course
    public static String InterFace_MyCollection_DELETE= HTTP_NAME + "m=Index&c=index&a=del_course";
    //  用户数据记录app开启时传入后台
    public static String InterFace_RecordUserInfo = HTTP_NAME + "m=Index&c=index&a=ajaxIndex";
    //  获取当前课程所有视频id
    public static String InterFace_GetVideoId = HTTP_NAME + "a=getCourseIds";

    //    判断参数
    public static String URL_SubstringParam = "type";

    //boss接口获取用户信息
    public static String InterFace_Param_GetCustomInfo = "QUE_USERINFO";
    //boss接口固定参数,版本编号 默认1
    public static String InterFace_Param_Version = "1";
    //boss接口固定参数,地市标识
    public static String InterFace_Param_Citycode = "WH";
    //boss接口固定参数,客户端编码
    public static String InterFace_Param_Clientcode = "1033";
    //boss接口固定参数,客户端密码
    public static String InterFace_Param_Clientpwd = "2t4c16e6f2bk13mp349f561gr6l4d31t";
    //boss接口参数，私钥
    public static String Private_Key = "GLJYM1d2c3";
    //自鉴权则为N
    public static String IsOrder = "N";
    //获取订单信息
    public static String InterFace_OrderInfo = HTTP_NAME + "m=Index&c=pay&a=order";
    //后台支付回调地址
    public static String Pay_BackCall_Address = HTTP_NAME + "c=Notice&a=orderServer";
    //鉴权
    public static String InterFace_Authentication = HTTP_NAME + "m=Index&c=pay&a=getVideoUrl";
    //vip订购模板id
    public static String Param_Template_Id = "1";
    //华为云播放地址AES密钥
    public static String Param_ScreteKey = "dZQb4y5VHvVHgi42";
/*************************************************************************************************************************************************************/

}
