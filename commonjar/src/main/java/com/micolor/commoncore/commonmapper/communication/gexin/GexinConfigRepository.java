/*
 * 上海骊蘅网络科技有限公司
 * Web Site: http://www.lihenginfo.com/
 */
package com.micolor.commoncore.commonmapper.communication.gexin;

import com.micolor.commoncore.communication.gexin.entity.GexinConfigEntity;
import com.micolor.commoncore.database.provider.SqlProvider;
import com.micolor.commoncore.datatables.domain.DataTables;
import com.micolor.commoncore.statics.StringStatics;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.business.data.dao.gexinconfig
 *
 * @Author:
 * @Description:
 * @Date:2017-7-30
 * @Modified:
 */
@Mapper
@Repository
public interface GexinConfigRepository {

    /**
     * Gets all gexin config list.
     *
     * @return the all gexin config list
     */
    @Select("select * from GEXIN_CONFIG where 1=1 ")
    List<Map> getAllGexinConfigList();

    /**
     * Gets gexin config page data.
     *
     * @param sql        the sql
     * @param dataTables the data tables
     * @return the gexin config page data
     */
    @SelectProvider(type=SqlProvider.class,method = "selectListForCustom")
    List<GexinConfigEntity> getGexinConfigPageData(@Param("sql") String sql, @Param("dataTables") DataTables dataTables);

    /**
     * Gets gexin config info.获取配置信息
     *
     * @param Id the id
     * @return the gexin config info
     */
    @Select("select * from GEXIN_CONFIG where gcid =  #{Id}")
    GexinConfigEntity getGexinConfigInfo(String Id);

    /**
     * Gets gexin config info 4 map.
     *
     * @param Id the id
     * @return the gexin config info 4 map
     */
    @Select("select * from GEXIN_CONFIG where gcid =  #{Id}")
    Map getGexinConfigInfo4Map(String Id);

    /**
     * Delete gexin config.
     *
     * @param Id the id
     */
    @Delete("delete from GEXIN_CONFIG   where gcid = #{Id}")
    void deleteGexinConfig(String Id);

    /**
     * Save gexin config.
     *
     * @param gexinConfigEntity the gexin config entity
     */
    @Insert("insert into GEXIN_CONFIG (" +
                "Appsecret ,"+ //APPSECRET
                "Appurl ,"+ //APPURL
                "Appkey ,"+ //AppKey
                "Mastersecret ,"+ //MasterSecret
                "Appid ,"+ //应用标识id
                "Gcid "+ //主键
            ") values (" +
                "#{appsecret,jdbcType=VARCHAR},"+ //APPSECRET
                            "#{appurl,jdbcType=VARCHAR},"+ //APPURL
                            "#{appkey,jdbcType=VARCHAR},"+ //AppKey
                            "#{mastersecret,jdbcType=VARCHAR},"+ //MasterSecret
                            "#{appid,jdbcType=VARCHAR},"+ //应用标识id
                            "#{gcid,jdbcType=VARCHAR}"+ //主键
            ")")
    @SelectKey(keyProperty = "gcid",resultType = String.class,before = true,statement = StringStatics.GENENRTEFROMDB)
    void saveGexinConfig(GexinConfigEntity gexinConfigEntity);

    /**
     * Update gexin config.
     *
     * @param gexinConfigEntity the gexin config entity
     */
    @Update("update GEXIN_CONFIG set "+
            "Appsecret =#{appsecret,jdbcType=VARCHAR} ,"+ //APPSECRET
            "Appurl =#{appurl,jdbcType=VARCHAR} ,"+ //APPURL
            "Appkey =#{appkey,jdbcType=VARCHAR} ,"+ //AppKey
            "Mastersecret =#{mastersecret,jdbcType=VARCHAR} ,"+ //MasterSecret
            "Appid =#{appid,jdbcType=VARCHAR} ,"+ //应用标识id
            "Gcid =#{gcid,jdbcType=VARCHAR} "+ //主键
            " where  gcid =#{gcid,jdbcType=VARCHAR}" 
    )
    void updateGexinConfig(GexinConfigEntity gexinConfigEntity);

}