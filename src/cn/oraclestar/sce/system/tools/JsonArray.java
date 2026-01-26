package cn.oraclestar.sce.system.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonArray {
    
    // 将Map列表转换为JSON数组字符串 "[{},{},...]"
    public static String toJson(List<Map<String,String>> list) {
        String rawJson = "[";
        for (Map<String,String> map : list) {
            rawJson += Json.toJson(map) + ",";
        }
        if (list.size() > 0) {
            rawJson = rawJson.substring(0, rawJson.length() - 1);
        }
        return rawJson + "]";
    }
    
    // 将JSON数组字符串 "[{},{},...]" 转换为Map列表
    public static List<Map<String,String>> toList(String jsonArray) {
        List<Map<String,String>> resultList = new ArrayList<>();
        
        // 移除外层方括号
        if (jsonArray.startsWith("[") && jsonArray.endsWith("]")) {
            jsonArray = jsonArray.substring(1, jsonArray.length() - 1);
        }
        
        // 分割各个JSON对象
        String[] jsonObjects = splitJsonObjects(jsonArray);
        
        for (String jsonObject : jsonObjects) {
            if (jsonObject.trim().isEmpty()) continue;
            
            Map<String,String> map = Json.toMap(jsonObject);
            if (map != null) {
                resultList.add(map);
            }
        }
        
        return resultList;
    }
    
    // 辅助方法：分割JSON对象
    private static String[] splitJsonObjects(String jsonArray) {
        List<String> objects = new ArrayList<>();
        int braceCount = 0;
        int startIndex = 0;
        
        for (int i = 0; i < jsonArray.length(); i++) {
            char c = jsonArray.charAt(i);
            if (c == '{') {
                braceCount++;
            } else if (c == '}') {
                braceCount--;
            } else if (c == ',' && braceCount == 0) {
                objects.add(jsonArray.substring(startIndex, i).trim());
                startIndex = i + 1;
            }
        }
        
        // 添加最后一个对象
        if (startIndex < jsonArray.length()) {
            objects.add(jsonArray.substring(startIndex).trim());
        }
        
        return objects.toArray(new String[0]);
    }
    
    // 添加单个JSON对象到数组
    public static String addToJsonArray(String jsonArray, Map<String,String> newMap) {
        List<Map<String,String>> list = toList(jsonArray);
        list.add(newMap);
        return toJson(list);
    }
    
    // 从JSON数组中移除指定索引的对象
    public static String removeFromJsonArray(String jsonArray, int index) {
        List<Map<String,String>> list = toList(jsonArray);
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        }
        return toJson(list);
    }
    
    // 获取JSON数组的大小
    public static int getSize(String jsonArray) {
        return toList(jsonArray).size();
    }
}