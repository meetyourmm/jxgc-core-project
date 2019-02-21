

/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.commonmapper.communication.websocket;

import com.micolor.commoncore.communication.websocket.entity.MessagesSocketsEntity;
import com.micolor.commoncore.database.provider.SqlProvider;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.statics.StringStatics;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.data.dao.messagessockets
 *
 * @Author:Ann
 * @Description:站内消息记录
 * @Date:2017-9-29
 * @Modified:
 */
@Mapper
@Repository
public interface MessagesSocketsRepository {

    @Select("select * from MESSAGES_SOCKETS where 1=1 ")
    List<Map> getAllMessagesSocketsList();

    @SelectProvider(type=SqlProvider.class,method = "selectListForDataTable")
    List<MessagesSocketsEntity> getMessagesSocketsPageData(@Param("sql") String sql, @Param("dataTables") DataTables dataTables);

    @Select("select * from MESSAGES_SOCKETS where msid =  #{Id}")
    MessagesSocketsEntity getMessagesSocketsInfo(String Id);

    @Select("select * from MESSAGES_SOCKETS where msid =  #{Id}")
    Map getMessagesSocketsInfo4Map(String Id);

    @Delete("delete from MESSAGES_SOCKETS   where msid = #{Id}")
    void deleteMessagesSockets(String Id);

    @Insert("insert into MESSAGES_SOCKETS (" +
                "Msid ,"+ //主键
                "Mscontent ,"+ //消息内容
                "Mstitle ,"+ //标题
                "Createtime ,"+ //创建时间
                "Userid ,"+ //用户编号
                "Delflag ,"+ //删除状态
                "Isshow ,"+ //是否已读 1未读 2已读
                "Msjson "+ //消息JSON
            ") values (" +
                "#{msid,jdbcType=VARCHAR},"+ //主键
                            "#{mscontent,jdbcType=VARCHAR},"+ //消息内容
                            "#{mstitle,jdbcType=VARCHAR},"+ //标题
                            "#{createtime,jdbcType=TIMESTAMP},"+ //创建时间
                            "#{userid,jdbcType=VARCHAR},"+ //用户编号
                            "#{delflag,jdbcType=CHAR},"+ //删除状态
                            "#{isshow,jdbcType=CHAR},"+ //是否已读 1未读 2已读
                            "#{msjson,jdbcType=VARCHAR}"+ //消息JSON
            ")")
    @SelectKey(keyProperty = "msid",resultType = String.class,before = true,statement = StringStatics.GENENRTEFROMDB)
    void saveMessagesSockets(MessagesSocketsEntity messagesSocketsEntity);

    @Update("update MESSAGES_SOCKETS set "+
            "Msid =#{msid,jdbcType=VARCHAR} ,"+ //主键
            "Mscontent =#{mscontent,jdbcType=VARCHAR} ,"+ //消息内容
            "Mstitle =#{mstitle,jdbcType=VARCHAR} ,"+ //标题
            "Createtime =#{createtime,jdbcType=TIMESTAMP} ,"+ //创建时间
            "Userid =#{userid,jdbcType=VARCHAR} ,"+ //用户编号
            "Delflag =#{delflag,jdbcType=CHAR} ,"+ //删除状态
            "Isshow =#{isshow,jdbcType=CHAR} ,"+ //是否已读 1未读 2已读
            "Msjson =#{msjson,jdbcType=VARCHAR} "+ //消息JSON
            " where  msid =#{msid,jdbcType=VARCHAR}" 
    )
    void updateMessagesSockets(MessagesSocketsEntity messagesSocketsEntity);


    @Select("SELECT * FROM (SELECT * FROM MESSAGES_SOCKETS WHERE DELFLAG='1' AND USERID = #{USERID} ORDER BY CREATETIME DESC) WHERE ROWNUM<6")
    List<MessagesSocketsEntity> getMessagesSocketsListByUserId(String userId);

    @Update("update MESSAGES_SOCKETS set "+
            "Isshow ='2' "+ //是否已读 1未读 2已读
            " where  msid =#{msId}"
    )
    void updateShowStatus(String msId);
}