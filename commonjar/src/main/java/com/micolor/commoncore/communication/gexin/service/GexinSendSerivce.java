package com.micolor.commoncore.communication.gexin.service;

import com.micolor.commoncore.message.domain.MsgBean;

/**
 * Created by Ann on 2017/7/30.
 */
public interface GexinSendSerivce {
    /**
     * 推送消息给指定对象列表
     * @param title 标题
     * @param content 内容
     * @param aliasStr(别名多个用逗号隔开)
     * @return
     */
    MsgBean sendMsgList(String title, String content, String aliasStr, String jsonStr);

    /**
     * 推送消息给单个对象
     * @param title 标题
     * @param content 内容
     * @param alias 别名
     * @param jsonStr 透传数据
     * @param appSign app类型（1外派版 2内派版）
     * @return
     */
    MsgBean pushtoSingle(String title, String content, String alias, String jsonStr, String appSign);

    /**
     * 群推消息
     * @param title 标题
     * @param content 内容
     * @param jsonStr 透传数据
     * @return
     */
    MsgBean pushToApp(String title, String content, String jsonStr);

    /**
     * 根据别名获取cid列表
      * @param alias
     * @return
     */
    MsgBean getClientIdByAlias(String alias);

    /**
     * 根据cid获取别名
     * @param cid
     * @return
     */
    MsgBean getAliasByClientId(String cid);

    /**
     * 根据cid获取标签
     * @param cid
     * @return
     */
    MsgBean getUserTagsByClientId(String cid);
}
