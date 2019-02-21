/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.commonmapper.communication.gexin;

import com.micolor.commoncore.communication.gexin.entity.GexinSendLogEntity;
import com.micolor.commoncore.database.provider.SqlProvider;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.statics.StringStatics;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.data.dao.gexinsendlog
 *
 * @Author:
 * @Description:
 * @Date:2017-7-30
 * @Modified:
 */
@Mapper
@Repository
public interface GexinSendLogRepository {

    /**
     * Gets all gexin send log list.
     *
     * @return the all gexin send log list
     */
    @Select("select * from GEXIN_SEND_LOG where 1=1 ")
    List<Map> getAllGexinSendLogList();

    /**
     * Gets gexin send log page data.
     *
     * @param sql        the sql
     * @param dataTables the data tables
     * @return the gexin send log page data
     */
    @SelectProvider(type=SqlProvider.class,method = "selectListForCustom")
    List<GexinSendLogEntity> getGexinSendLogPageData(@Param("sql") String sql, @Param("dataTables") DataTables dataTables);

    /**
     * Gets gexin send log info.
     *
     * @param Id the id
     * @return the gexin send log info
     */
    @Select("select * from GEXIN_SEND_LOG where gslid =  #{Id}")
    GexinSendLogEntity getGexinSendLogInfo(String Id);

    /**
     * Gets gexin send log info 4 map.
     *
     * @param Id the id
     * @return the gexin send log info 4 map
     */
    @Select("select * from GEXIN_SEND_LOG where gslid =  #{Id}")
    Map getGexinSendLogInfo4Map(String Id);

    /**
     * Delete gexin send log.
     *
     * @param Id the id
     */
    @Delete("delete from GEXIN_SEND_LOG   where gslid = #{Id}")
    void deleteGexinSendLog(String Id);

    /**
     * Save gexin send log.
     *
     * @param gexinSendLogEntity the gexin send log entity
     */
    @Insert("insert into GEXIN_SEND_LOG (" +
                "gslid ,"+ //推送记录编号
                "target ,"+ //发送对象
                "Gxdate ,"+ //发送时间
                "gxtitle ,"+ //发送状态（1成功2失败）
                "gxcontent ,"+ //发送内容
                "gxstatus "+ //发送状态（1成功2失败）
            ") values (" +
                "#{gslid,jdbcType=VARCHAR},"+ //推送记录编号
                            "#{target,jdbcType=VARCHAR},"+ //发送对象
            "sysdate," + //发送时间
                            "#{gxtitle,jdbcType=VARCHAR},"+ //发送标题
                            "#{gxcontent,jdbcType=VARCHAR},"+ //发送内容
                            "#{gxstatus,jdbcType=VARCHAR}"+ //发送状态（1成功2失败）
            ")")
    @SelectKey(keyProperty = "gslid",resultType = String.class,before = true,statement = StringStatics.GENENRTEFROMDB)
    void saveGexinSendLog(GexinSendLogEntity gexinSendLogEntity);

    /**
     * Update gexin send log.
     *
     * @param gexinSendLogEntity the gexin send log entity
     */
    @Update("update GEXIN_SEND_LOG set "+
            "Gslid =#{gslid,jdbcType=VARCHAR} ,"+ //推送记录编号
            "Target =#{target,jdbcType=VARCHAR} ,"+ //发送对象
            "Gxdate =now()," + //发送时间
            "gxcontent =#{gxtitle,jdbcType=VARCHAR} ,"+ //发送标题
            "Gxcontent =#{gxcontent,jdbcType=VARCHAR} ,"+ //发送内容
            "Gxstatus =#{gxstatus,jdbcType=VARCHAR} "+ //发送状态（1成功2失败）
            " where  gslid =#{gslid,jdbcType=VARCHAR}" 
    )
    void updateGexinSendLog(GexinSendLogEntity gexinSendLogEntity);

}