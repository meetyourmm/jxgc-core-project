package com.micolor.commoncore.communication.gexin.util;

/**
 * Created by Ann on 2017/7/30.
 */

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Ann
 * @Description: 个推接口类
 * @date 2017年07月30日 上午15:41:11
 */
public class AppPush {
    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    public static String appId = "";
    public static String appKey = "";
    public static String masterSecret = "";
    public static String url = "http://sdk.open.api.igexin.com/apiex.htm";

    /**
     * 发送单个用户
     * @param template
     * @param Alias
     * @throws IOException
     */
    public  static void pushtoSingle(AbstractTemplate template, String Alias)throws IOException {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }
    /**
     * 发送列表
     * @param template
     * @param aliasStr
     * @throws IOException
     */
    public static void sendMsgList(AbstractTemplate template, String aliasStr) throws IOException {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        List targets = new ArrayList();
        // 配置推送目标
        int i;
        String[] cids = aliasStr.split(",");
        Target target;
        for (i = 0; i < cids.length; i++){
            if(cids[i] == null ||"".equals(cids[i])) continue;
            target = new Target();
            target.setAppId(appId);
            target.setAlias(cids[i].toString());
            targets.add(target);
        }
        //taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * 对指定应用群推消息
     * @param template
     */
    public static void pushToApp(AbstractTemplate template) throws IOException {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * 普通消息
     * @param title
     * @param content
     * @return
     */
    public static NotificationTemplate androidTemplate(String title, String content) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        Style1 style = new Style1();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
        //style.setLogo("icon.png");
        // 配置通知栏网络图标
        //style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);
        return template;
    }
    /**
     * 透传消息
     * @param title
     * @param content
     * @return
     */
    public static TransmissionTemplate transmissionTemplate(String title, String content,String jsonStr) {
        TransmissionTemplate template = new TransmissionTemplate ();
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        // 设置通知栏标题与内容
        template.setTransmissionContent(jsonStr);
        //仅支持iOS8.2以上版本
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        //payload.setContentAvailable(1);
        payload.setSound("default");
        //简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content));
        template.setAPNInfo(payload);
        return template;
    }
}