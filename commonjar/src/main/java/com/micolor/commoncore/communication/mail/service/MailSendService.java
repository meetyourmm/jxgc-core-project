package com.micolor.commoncore.communication.mail.service;

import com.micolor.commoncore.communication.mail.entity.MailSendLogEntity;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.mail.service
 *
 * @Author: Evangoe
 * @Description: 邮件发送日志接口
 * @Date:14/06/17
 * @Modified:
 */
public interface MailSendService {

    /**
     * 使用邮件模版发送邮件
     * @param map 邮件模版中定义的参数以及值信息
     * @param templateName 模版名称
     * @param targets 发送对象邮箱结合
     * @return
     */
    MsgBean sendMailByTemplate(Map map, String templateName, String[] targets, String title);

    /**
     * 发送简单的文字邮件
     * @param content 文字内容
     * @param targets 发送对象邮箱结合
     * @return
     */
    MsgBean sendSimple(String content, String[] targets, String title);
    /**
     * 保存邮件发送日志
     * @param mailSendLogEntity
     * @return
     */
    MsgBean saveMailSendLog(MailSendLogEntity mailSendLogEntity);

    /**
     * 获得邮件日志清单
     * @param dataTables
     * @return
     */
    List<Map>  getMailSendLog(DataTables dataTables);
}
