package cn.oraclestar.sce.system.tools;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Json {
    // must be a Map<String,String>
    public static String toJson(Map<String,String> a){
        String rawJson = "{";
        for (Map.Entry<String,String> temp : a.entrySet()){
            rawJson += ("\"" + temp.getKey().toString() + "\":" + "\"" + temp.getValue() + "\",");
        }
        rawJson = rawJson.substring(0,rawJson.length() - 1);
        return rawJson + "}";
    }
    public static Map<String,String> toMap(String json){
        json = json.replaceAll("[{}]","");
        json = json.replaceAll(",","\n");
        json = json.replaceAll(":","=");
        json = json.replaceAll("\"","");
        Properties props = new Properties();
        StringReader sr = new StringReader(json);
        try{
            props.load(sr);
            Map<String,String> temp_map = new HashMap<String,String>();
            for(Map.Entry<Object,Object> e : props.entrySet()){
                temp_map.put((String)e.getKey(),(String)e.getValue());
            }
            return temp_map;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(sr != null){
                sr.close();
            }
        }
        Map<String,String> aMap = null;
        return aMap;
    }
}
