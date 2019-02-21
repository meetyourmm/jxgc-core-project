package com.micolor.commoncore.communication.sms.service;

import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.method
 *
 * @Author: Evangoe
 * @Description: 用于发送短信的类
 * @Date:09/06/17
 * @Modified:
 *
 *
 *
 */
public interface SmsSendService {
    /**
     * 发送验证码
     * @param target
     * @param content
     * @return
     * @throws Exception
     */
    MsgBean sendVC(String target, String content,String company,String type);
    /**
     * 发送短消息
     * @param targets
     * @param content
     * @return
     * @throws Exception
     */
    MsgBean sendMsg(StringBuilder targets, String content,String company,String type);

    /**
     * 获得短信发送激励
     * @param dataTables
     * @return
     */
    List<Map> getSmsSendLog(DataTables dataTables);
}
