package com.laichushu.book.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.bean.alipay.PayResult;
import com.laichushu.book.bean.alipay.SignUtils;
import com.laichushu.book.bean.wechatpay.WxInfo;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 作者:wangtong
 * 时间:2016年09月2016/9/5日
 * 描述:支付操作类
 */
public class PayUtils {
    private Context mContext;
    // 商户PID
    public static final String PARTNER = ConstantValue.PARTNER;
    // 商户收款账号
    public static final String SELLER = ConstantValue.SELLER;
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = ConstantValue.RSA_PRIVATE;

    private static final int SDK_PAY_FLAG = 1;

    private IWXAPI mWxApi;
    private WxInfo mData; // 微信支付信息

    private static volatile PayUtils mPayUtils = null;

    private PayUtils(Context context) {
        this.mContext = context;
        mWxApi = WXAPIFactory.createWXAPI(mContext, ConstantValue.WECHAT_APPID);
        // 将该app注册到微信
        mWxApi.registerApp(ConstantValue.WECHAT_APPID);
    }

    public static PayUtils getInstance(Context context){
        if (mPayUtils == null){
            synchronized (PayUtils.class){
                if (mPayUtils == null) mPayUtils = new PayUtils(context);
            }
        }
        return mPayUtils;
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                        UIUtil.getMainThreadHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AppManager.getInstance().killAllTopActivity();
                            }
                        }, 1700);
                        // TODO  刷新我的订单
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(mContext, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 支付宝支付
     * 调用SDK支付
     */
    public void alipay(final Activity activity, String price, String tradeNo) {
        String orderInfo = getOrderInfo("购买商品", "该测试商品的详细描述", price, tradeNo);
        /** 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！ */
        String sign = sign(orderInfo);
        try {
            /** 仅需对sign 做URL编码  */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /** 完整的符合支付宝参数规范的订单信息  */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(activity);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /** get the sdk version. 获取SDK版本号 */
    public void getSDKVersion(Activity activity) {
        PayTask payTask = new PayTask(activity);
        String version = payTask.getVersion();
        Toast.makeText(activity, version, Toast.LENGTH_SHORT).show();
    }

    /** create the order info. 创建订单信息  */
    private String getOrderInfo(String subject, String body, String price, String tradeNo) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";
        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + tradeNo + "\"";
//		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";
        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + ConstantValue.ALIPAY_CALLBACK_URL + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";
        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";
        orderInfo += "&it_b_pay=\"30m\"";
        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";
        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /** get the sign type we use. 获取签名方式  */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * 微信支付操作
     * @param info
     */
    public void wechatPay(WxInfo info) {
        PayReq req = new PayReq();
        req.appId = ConstantValue.WECHAT_APPID;
        req.partnerId = info.getData().getPartnerId();
        req.prepayId = info.getData().getPrepayId();
        req.nonceStr = info.getData().getNonceStr();
        req.timeStamp = info.getData().getTimeStamp();
        req.packageValue = info.getData().getPackageName();
        req.sign = info.getData().getSign();
        mWxApi.sendReq(req);
    }
}
