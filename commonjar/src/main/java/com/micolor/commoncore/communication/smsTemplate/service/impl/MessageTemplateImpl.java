/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.communication.smsTemplate.service.impl;

import com.github.pagehelper.PageHelper;
import com.micolor.commoncore.commonmapper.communication.smsTemplate.MessageTemplateRepository;
import com.micolor.commoncore.communication.smsTemplate.entity.MessageTemplateEntity;
import com.micolor.commoncore.communication.smsTemplate.service.MessageTemplateService;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.message.domain.MsgBean;
import com.micolor.commoncore.statics.EnumUtil;
import com.micolor.commoncore.statics.StringStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.bo.impl.messagetemplate
 *
 * @Author:Ann
 * @Description:
 * @Date:2017-9-29
 * @Modified:
 */
@Service("messageTemplateService")
public class MessageTemplateImpl implements MessageTemplateService {

    private static Logger logger = LoggerFactory.getLogger(MessageTemplateImpl.class);

    @Autowired
    private MessageTemplateRepository messageTemplateRepository;
    @Autowired
    private MessageTemplateService messageTemplateService;

    @Override
    public List<Map> getAllMessageTemplateList() {
        return messageTemplateRepository.getAllMessageTemplateList();
    }

    @Override
    public List<MessageTemplateEntity> getMessageTemplateList(DataTables dataTables) {
        String sql = "select * from MESSAGE_TEMPLATE where 1=1";
        int pageNum = dataTables.getcurrPage();
        int pageSize = dataTables.getLength();
        PageHelper.startPage(pageNum, pageSize);
        return messageTemplateRepository.getMessageTemplatePageData(sql, dataTables);
    }

    @Override
    public MessageTemplateEntity getMessageTemplate(String Id) {
        return messageTemplateRepository.getMessageTemplateInfo(Id);
    }

    @Override
    public String getMessageTemplateCon(String Id, Map param) {
        Map tMap = messageTemplateRepository.getMessageTemplateInfo4Map(Id);
        String con = tMap.get("MTEMPLATE").toString();
        return messageTemplateService.replaceMsg(con, param);
    }

    public MsgBean deleteMessageTemplate(MessageTemplateEntity[] messageTemplateEntities) {
        MsgBean msgBean = new MsgBean();
        try {
            for (MessageTemplateEntity messageTemplateEntity : messageTemplateEntities) {
                messageTemplateRepository.deleteMessageTemplate(messageTemplateEntity.getMtid());
            }
            msgBean.setMsg(StringStatics.OPERATIONOK);
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
        } catch (Exception e) {
            msgBean.setMsg(StringStatics.OPERATIONERROR + ",原因：" + e.getMessage());
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            logger.error(StringStatics.INNERERROR, e);
        }
        return msgBean;
    }

    @Override
    public MsgBean saveMessageTemplate(MessageTemplateEntity messageTemplateEntity) {
        MsgBean msgBean = new MsgBean();
        try {
            messageTemplateRepository.saveMessageTemplate(messageTemplateEntity);
            msgBean.setContent(messageTemplateEntity);
            msgBean.setMsg("操作成功！");
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
        } catch (Exception e) {
            msgBean.setMsg(StringStatics.OPERATIONERROR + ",原因：" + e.getMessage());
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            logger.error("内部异常：{}", e.getMessage());
        }
        return msgBean;
    }

    @Override
    public MsgBean updateMessageTemplate(MessageTemplateEntity messageTemplateEntity) {
        MsgBean msgBean = new MsgBean();
        try {
            messageTemplateRepository.updateMessageTemplate(messageTemplateEntity);
            msgBean.setContent(messageTemplateEntity);
            msgBean.setMsg("操作成功！");
            msgBean.setStatus(EnumUtil.MessageStatus.OK);
        } catch (Exception e) {
            msgBean.setMsg(StringStatics.OPERATIONERROR + ",原因：" + e.getMessage());
            msgBean.setStatus(EnumUtil.MessageStatus.ERROR);
            logger.error("内部异常：{}", e.getMessage());
        }
        return msgBean;
    }

    @Override
    public String replaceMsg(String con, Map param) {
        String rcon = "";
        String[] str1 = con.split("\\{param}");
        if ((str1.length - 1) == param.size()) {
            for (int i = 0; i < param.size(); i++) {
                con = con.replaceFirst("\\{param}", param.get(i).toString());
            }
        }
        rcon = con;
        return rcon;
    }
}
