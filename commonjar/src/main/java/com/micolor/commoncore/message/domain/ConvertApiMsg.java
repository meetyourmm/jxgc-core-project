package com.micolor.commoncore.message.domain;

import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.message.domain
 *
 * @Author: Evangoe
 * @Description:
 * @Date:4/7/17
 * @Modified:
 */
public class ConvertApiMsg {
    private ConvertApiMsg() {
    }

    public static List convertKeyToLowerCase4List(List<?> list){
        List returnlist = new ArrayList();

        for(int index = 0 ;index <list.size() ; index ++){
            Map map = null;
            Object object = list.get(index);
            if("java.util.HashMap".equals(object.getClass().getName())){
                map = (Map)object;
            }else{
                JSONObject jsonObject = JSONObject.fromObject(object);
                map = jsonObject;
            }
            returnlist.add(convertKeyToLowerCase4Map(map));
        }
        return returnlist;
    }

    public static Map convertKeyToLowerCase4Map(Map map){
        Map<String,Object> newMap = new HashMap();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            if(entry.getValue()!= null){
                if (entry.getValue().getClass().equals(String.class)
                        ||entry.getValue().getClass().equals(Timestamp.class)
                        ||entry.getValue().getClass().equals(BigDecimal.class)) {
                    newMap.put(entry.getKey().toLowerCase(),entry.getValue());
                } else {
                    newMap.put(entry.getKey(),entry.getValue());
                }
            }else{
                newMap.put(entry.getKey().toLowerCase(),"null");
            }

        }
        return newMap;
    }
}
