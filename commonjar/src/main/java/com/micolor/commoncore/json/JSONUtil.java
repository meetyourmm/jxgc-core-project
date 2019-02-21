package com.micolor.commoncore.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.json
 *
 * @Author: Evangoe
 * @Description: JSON操作的一些公共方法
 * @Date:31/05/17
 * @Modified:
 */
public class JSONUtil {
    private JSONUtil(){}
    /**
     * 将form。
     * @param jsonInString
     * @return
     */
    public static JSONObject string2JSONObject(String jsonInString){
        jsonInString = jsonInString.replaceAll("\\\\","");
        jsonInString = jsonInString.substring(1,jsonInString.length());
        jsonInString = jsonInString.substring(0,jsonInString.length()-1);
        JSONObject jsonObject = JSONObject.fromObject(jsonInString);
        JSONObject object = jsonObject.getJSONObject("Entity");
        return object;
    }

    public static JSONObject string2JSONObjectNoEntity(String jsonInString){
        jsonInString = jsonInString.replaceAll("\\\\","");
        jsonInString = jsonInString.substring(1,jsonInString.length());
        jsonInString = jsonInString.substring(0,jsonInString.length()-1);
        JSONObject jsonObject = JSONObject.fromObject(jsonInString);
        return jsonObject;
    }

    /**
     * 从json获得json
     * @param jsonInString
     * @return
     */
    public static JSONArray string2JSONArrayNoArrayName(String jsonInString){
        jsonInString = jsonInString.replaceAll("\\\\","");
        jsonInString = jsonInString.substring(1,jsonInString.length());
        jsonInString = jsonInString.substring(0,jsonInString.length()-1);
        JSONArray obj = JSONArray.fromObject(jsonInString);
        return obj;
    }

    /**
     * 根据指定的json key获取JSONArray
     * @param jsonInString
     * @return
     */
    public static JSONArray string2JSONArray(String jsonInString,String arrayName){
        jsonInString = jsonInString.replaceAll("\\\\","");
        jsonInString = jsonInString.substring(1,jsonInString.length());
        jsonInString = jsonInString.substring(0,jsonInString.length()-1);
        JSONObject jsonObject = JSONObject.fromObject(jsonInString);
        JSONArray obj = jsonObject.getJSONArray(arrayName);
        return obj;
    }

    /**
     * 根据指定的json key获取的JSONObject
     * @param jsonInString
     * @return
     */
    public static JSONObject string2JSONObjectByKey(String jsonInString,String keyName){
        jsonInString = jsonInString.replaceAll("\\\\","");
        jsonInString = jsonInString.substring(1,jsonInString.length());
        jsonInString = jsonInString.substring(0,jsonInString.length()-1);
        JSONObject jsonObject = JSONObject.fromObject(jsonInString);
        JSONObject object = jsonObject.getJSONObject(keyName);
        return object;
    }
}
