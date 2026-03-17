package cn.oraclestar.sce.system.setManager;

import java.util.HashMap;
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

// TODO setRW
public class setRW {
    public static void write_save(){
        Map<String,String> temp = new HashMap<>();
        for(var a : list_Boolen_Map.temp_maps){
            for(Map.Entry<String,Boolean> b : a.entrySet()){
                if(b.getValue()){
                    temp.put(b.getKey(),"true");
                } else {
                    temp.put(b.getKey(),"false");
                }
            }
        }
        for (var a : list_String_Map.temp_maps){
            for(Map.Entry<String,String> b : a.entrySet()){
                temp.put(b.getKey(),b.getValue());
            }
        }
    }
}
