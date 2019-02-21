package com.micolor.commoncore.communication.mail.service.impl;

import com.github.pagehelper.PageHelper;
import com.micolor.commoncore.commonmapper.communication.mail.MailSendLogRepository;
import com.micolor.commoncore.communication.mail.entity.MailSendLogEntity;
import com.micolor.commoncore.communication.mail.service.MailSendService;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.micolor.commoncore.communication.mail.service.impl
 *
 * @Author: Evangoe
 * @Description:
 * @Date:14/06/17
 * @Modified:
 */
@Service("mailSendService")
public class MailSendImpl implements MailSendService{
    @Autowired
    MailSendLogRepository mailSendLogRepository;
    private static Logger logger = LoggerFactory.getLogger(MailSendImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;  //自动注入


    /**
     * 保存邮件发送日志
     *
     * @param mailSendLogEntity
     * @return  @see com.micolor.commoncore.message.domain.MsgBean
     */
    @Override
    public MsgBean saveMailSendLog(MailSendLogEntity mailSendLogEntity) {
        MsgBean msgBean = new MsgBean();
        try{
            mailSendLogRepository.saveMailSendLog(mailSendLogEntity);
            msgBean.setContent(mailSendLogEntity);
            msgBean.setMsg(StringStatics.OPERATIONOK);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
        }catch (Exception e){
            logger.error("操作失败！原因 {}",e.getMessage());
            msgBean.setMsg("操作失败！原因："+e.getMessage());
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        }
        return msgBean;
    }


    /**
     * 使用邮件模版发送邮件
     *
     * @param map          邮件模版中定义的参数以及值信息
     * @param templateName 模版名称
     * @param targets      发送对象邮箱结合
     * @return
     */
    @Override
    public MsgBean sendMailByTemplate(Map map, String templateName, String[] targets,String title) {
        MsgBean msgBean = new MsgBean();
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("lihengchinese@sina.com");
            helper.setTo(targets);
            helper.setSubject(title);
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            helper.setText(html, true);

            MailSendLogEntity mailSendLogEntity = new MailSendLogEntity();
            mailSendLogEntity.setMailSendContent(html);
            mailSendLogEntity.setMailSendDate(new Date());
            mailSendLogEntity.setMailSendTarget(targets.toString());
            this.saveMailSendLog(mailSendLogEntity);

            msgBean.setContent(mailSendLogEntity);
            msgBean.setMsg(StringStatics.OPERATIONOK);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);

        } catch (Exception e) {
            logger.error("操作失败！原因 {}",e.getMessage());
            msgBean.setMsg("操作失败！原因："+e.getMessage());
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
        }
        mailSender.send(message);
        return msgBean;
    }

    /**
     * 发送简单的文字邮件
     *
     * @param content 文字内容
     * @param targets 发送对象邮箱结合
     * @return
     */
    @Override
    public MsgBean sendSimple(String content, String[] targets,String title) {
        return null;
    }

    /**
     * 获得邮件日志清单
     *
     * @param dataTables
     * @return
     */
    @Override
    public List<Map> getMailSendLog(DataTables dataTables) {
        String sql = "select * from mail_send_log where 1=1 ";
        int pageNum = dataTables.getcurrPage();
        int pageSize= dataTables.getLength();
        PageHelper.startPage(pageNum, pageSize);
        return mailSendLogRepository.getMailSendLog(sql,dataTables);
    }
}