package cn.oraclestar.sce.system.setManager;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
################################################
#                                              #
#        Another:xingguangcuican6666           #
#        Date:2025-11-24                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

public class setManager implements list_Boolen_Map,list_Int_Map,list_String_Map {
    public static String ErrorMassger = null;
    protected static void inputSet(int type,Map<String,?> tempMap){
        if(type == cn.oraclestar.sce.system.setManager.type.string){
            cn.oraclestar.sce.system.setManager.list_String_Map.temp_maps.add((Map<String,String>)tempMap);
        } else if(type == cn.oraclestar.sce.system.setManager.type.Int){
            cn.oraclestar.sce.system.setManager.list_Int_Map.temp_maps.add((Map<String,Integer>)tempMap);
        } else if (type == cn.oraclestar.sce.system.setManager.type.bool){
            cn.oraclestar.sce.system.setManager.list_Boolen_Map.temp_maps.add((Map<String,Boolean>)tempMap);
        }
    }
    protected static String getStringSet(String key) throws Exception{
        for(Map<String,String> map : cn.oraclestar.sce.system.setManager.list_String_Map.temp_maps){
            if(map == null)continue;
            for (Map.Entry<String,String> e : map.entrySet()){
                if(e.getKey().toString().equals(key)){
                    return e.getValue();
                }
            };
        }
        throw(new Exception("set.setManager: Undefine Setting"));
    }
    protected static int getIntSet(String key) throws Exception{
        for(Map<String,Integer> map : cn.oraclestar.sce.system.setManager.list_Int_Map.temp_maps){
            if(map == null)continue;
            for (Map.Entry<String,Integer> e : map.entrySet()){
                if(e.getKey().toString().equals(key)){
                    return e.getValue();
                }
            }
        }
        throw(new Exception("set.setManager: Undefine Setting"));
    }
    protected static Boolean getBoolSet(String key) throws Exception{
        for(Map<String,Boolean> map : cn.oraclestar.sce.system.setManager.list_Boolen_Map.temp_maps){
            if(map == null)continue;
            for (Map.Entry<String,Boolean> e : map.entrySet()){
                if(e.getKey().toString().equals(key)){
                    return e.getValue();
                }
            }
        }
        throw(new Exception("set.setManager: Undefine Setting"));
    }
    
    public static void RemoveSet(String key){
        // for(Map<String,Integer> map : cn.oraclestar.sce.system.setManager.list_Int_Map.temp_maps){
        //     if(map == null) continue;
        //     map.remove(key);
        // }
        // for(Map<String,String> map : cn.oraclestar.sce.system.setManager.list_String_Map.temp_maps){
        //     if(map == null) continue;
        //     map.remove(key);
        // }
        // for(Map<String,Boolean> map : cn.oraclestar.sce.system.setManager.list_Boolen_Map.temp_maps){
        //     if(map == null) continue;
        //     map.remove(key);
        // }
        cn.oraclestar.sce.system.setManager.list_Int_Map.temp_maps.removeIf(map -> map != null && map.containsKey(key));
        cn.oraclestar.sce.system.setManager.list_String_Map.temp_maps.removeIf(map -> map != null && map.containsKey(key));
        cn.oraclestar.sce.system.setManager.list_Boolen_Map.temp_maps.removeIf(map -> map != null && map.containsKey(key));
    }
    public static void changeVaule(String key,Object vaule) throws Exception{
        // cn.oraclestar.sce.system.setManager.setManager_core.RemoveSet(key);
        // if(vaule instanceof Integer){
        //     Map<String,Integer> temp_Map = new HashMap<>();
        //     temp_Map.put(key,(Integer)vaule);
        //     cn.oraclestar.sce.system.setManager.setManager_core.inputSet(cn.oraclestar.sce.system.setManager.type.Int,temp_Map);
        //     return;
        // } else if (vaule instanceof String){
        //     Map<String,String> temp_Map = new HashMap<>();
        //     temp_Map.put(key,(String)vaule);
        //     cn.oraclestar.sce.system.setManager.setManager_core.inputSet(cn.oraclestar.sce.system.setManager.type.string,temp_Map);
        //     return;
        // } else if (vaule instanceof Boolean){
        //     Map<String,Boolean> temp_Map = new HashMap<>();
        //     temp_Map.put(key,(Boolean)vaule);
        //     cn.oraclestar.sce.system.setManager.setManager_core.inputSet(cn.oraclestar.sce.system.setManager.type.bool,temp_Map);
        //     return;
        // }
        // throw(new Exception("resManager.setManager: No any key found of " + key));
        boolean updated = false;
        if (vaule instanceof Integer) {
            for (Map<String, Integer> map : cn.oraclestar.sce.system.setManager.list_Int_Map.temp_maps) {
                if (map.containsKey(key)) { map.put(key, (Integer) vaule); updated = true; break; }
            }
        } else if (vaule instanceof String) {
            for (Map<String, String> map : cn.oraclestar.sce.system.setManager.list_String_Map.temp_maps) {
                if (map.containsKey(key)) { map.put(key, (String) vaule); updated = true; break; }
            }
        } else if (vaule instanceof Boolean) {
            for (Map<String, Boolean> map : cn.oraclestar.sce.system.setManager.list_Boolen_Map.temp_maps) {
                if (map.containsKey(key)) { map.put(key, (Boolean) vaule); updated = true; break; }
            }
        }
        if (!updated) {
            addSet(key, vaule);
        }
    };
    public static void addSet(String key,Object vaule) throws Exception{
        // check
        for (Map<String,String> maps : cn.oraclestar.sce.system.setManager.list_String_Map.temp_maps){
            if(maps == null)continue;
            for (Map.Entry<String,String> e : maps.entrySet()){
                if(e.getKey().toString().equals(key)){
                    throw(new Exception("resManager.setManager: 设置重定义"));
                }
            }
        }
        for (Map<String,Boolean> maps : cn.oraclestar.sce.system.setManager.list_Boolen_Map.temp_maps){
            if(maps == null)continue;
            for (Map.Entry<String,Boolean> e : maps.entrySet()){
                if(e.getKey().toString().equals(key)){
                    throw(new Exception("resManager.setManager: 设置重定义"));
                }
            }
        }
        for (Map<String,Integer> maps : cn.oraclestar.sce.system.setManager.list_Int_Map.temp_maps){
            if(maps == null)continue;
            for (Map.Entry<String,Integer> e : maps.entrySet()){
                if(e.getKey().toString().equals(key)){
                    throw(new Exception("resManager.setManager: 设置重定义"));
                }
            }
        }


        if(vaule instanceof String){
            Map<String,String> temp_map = new HashMap<>();
            temp_map.put(key,(String)vaule);
            inputSet(cn.oraclestar.sce.system.setManager.type.string,temp_map);
            return;
        }
        if(vaule instanceof Boolean){
            Map<String,Boolean> temp_map = new HashMap<>();
            temp_map.put(key,(Boolean)vaule);
            inputSet(cn.oraclestar.sce.system.setManager.type.bool,temp_map);
            return;
        }
        if(vaule instanceof Integer){
            Map<String,Integer> temp_map = new HashMap<>();
            temp_map.put(key,(Integer)vaule);
            inputSet(cn.oraclestar.sce.system.setManager.type.Int,temp_map);
            return;
        }


    }
    public static <T> T getVaule(String key) throws Exception{
        Boolean testS = false;
        Boolean testB = false;
        Boolean testI = false;
        try{
            getStringSet(key);
            testS = true;
        } catch(Exception e){
            //No AnyThing
        };
        try{
            getIntSet(key);
            testI = true;
        } catch(Exception e) {
            //No Anything
        }
        try{
            getBoolSet(key);
            testB = true;
        } catch(Exception e){
            //No Anything
        }
        try{
            if(testB == true){
                return (T)getBoolSet(key);
            } else if (testS == true){
                return (T)getStringSet(key);
            } else if (testI == true){
                return (T)((Integer)getIntSet(key));
            }
        } catch (Exception e){
            //No Anything
            throw(new Exception("Unknow Error"));
        }
        throw(new Exception("ResManager.setManager: 未定义的设置 - " + key));
    };

    public static void toggleSetting(String key){
        for(Map<String,Boolean> maps : cn.oraclestar.sce.system.setManager.list_Boolen_Map.temp_maps){
            if(maps == null) continue;
            for(Map.Entry<String,Boolean> e : maps.entrySet()){
                if(e.getKey().toString().equals(key)){
                    if(e.getValue()){
                        maps.put(key,false);
                    } else {
                        maps.put(key,true);
                    }
                }
            }
        }
    }
    public static void SaveSettings(){
        try(XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("settings.xml")))){
            encoder.writeObject(list_String_Map.temp_maps);
            encoder.writeObject(list_Int_Map.temp_maps);
            encoder.writeObject(list_Boolen_Map.temp_maps);
            encoder.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Boolean LoadSettings() throws Exception{
        File file = new File("settings.xml");
        if (!file.exists()) return false;
        try(XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("settings.xml")))){
            Object sData = decoder.readObject();
            if(sData instanceof List){
                mergeMaps(list_String_Map.temp_maps,(List<Map<String,String>>) sData);
            }

            Object iData = decoder.readObject();
            if (iData instanceof List) {
                mergeMaps(list_Int_Map.temp_maps, (List<Map<String, Integer>>) iData);
            }

            Object bData = decoder.readObject();
            if (bData instanceof List) {
                mergeMaps(list_Boolen_Map.temp_maps, (List<Map<String, Boolean>>) bData);
            }
            
            System.err.println("[Settings] 成功从文件合并配置数据");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            ErrorMassger = e.getMessage();
            System.err.println("[Settings] 加载失败或文件格式损坏，将维持当前内存状态");
            return false;
        }
    }
    private static <T> void mergeMaps(List<Map<String, T>> currenList, List<Map<String, T>> loadedList) {
        if (loadedList == null) return;
        int size = Math.min(currenList.size(), loadedList.size());
        for (int i = 0; i < size; i++) {
            Map<String, T> currentMap = currenList.get(i);
            Map<String, T> loadedMap = loadedList.get(i);
            if (currentMap != null && loadedMap != null) {
                currentMap.putAll(loadedMap);
            }
        }
    }
}
