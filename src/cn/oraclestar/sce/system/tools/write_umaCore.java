package cn.oraclestar.sce.system.tools;

import java.io.File;
import java.io.FileWriter;

import cn.oraclestar.sce.system.global;
import cn.oraclestar.sce.system.setManager.setManager;
import cn.oraclestar.sce.system.umaCore.umaCore_core;

/*
################################################
#                                              #
#        Another:xingguangcuican6666           #
#        Date:2025-12-01                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

public class write_umaCore {
    umaCore_core uma1 = global.uma1;

    public static boolean saveRes(umaCore_core runtime){
        // {"type":int,"name":""}
        String path = null;
        String json_write = "{" + "\"type\":" + String.valueOf(runtime.pushType()) + "," 
                            + "\"name\":\"" + runtime.pushName() + "\","
                            + "\"friendshipBonus\":" + String.valueOf((int)(runtime.pushRate("friendshipBonus"))) + ","
                            + "\"moodEffect\":" + String.valueOf((int)runtime.pushRate("moodEffect")) + ","
                            + "\"traningEffect\":" + String.valueOf((int)runtime.pushRate("traningEffect")) + ","
                            + "\"initalFriendship\":" + String.valueOf((int)runtime.pushRate("initalFriendship")) + ","
                            + "\"specialtyPriority\":" + String.valueOf((int)runtime.pushRate("specialtyPriority")) + ","
                            + "\"_friendshipBonus\":" + String.valueOf((int)runtime.pushUnique("friendshipBonus")) + ","
                            + "\"_moodEffect\":" + String.valueOf((int)runtime.pushUnique("moodEffect")) + ","
                            + "\"_traningEffect\":" + String.valueOf((int)runtime.pushUnique("traningEffect")) + ","
                            + "\"_initalFriendship\":" + String.valueOf((int)runtime.pushUnique("initalFriendship")) + ","
                            + "\"_specialtyPriority\":" + String.valueOf((int)runtime.pushUnique("specialtyPriority")) + ","
                            + "\"speed\":" + String.valueOf((int)runtime.pushBonus("speed")) + ","
                            + "\"stamina\":" + String.valueOf((int)runtime.pushBonus("stamina")) + ","
                            + "\"power\":" + String.valueOf((int)runtime.pushBonus("power")) + ","
                            + "\"guts\":" + String.valueOf((int)runtime.pushBonus("guts")) + ","
                            + "\"wit\":" + String.valueOf((int)runtime.pushBonus("wit")) + ","
                            + "\"sp\":" + String.valueOf((int)runtime.pushBonus("sp"))
                            + "}";
        FileWriter fw = null;
        try{
            if(((String)(setManager.getVaule("cardDataSavePath"))).equals("")){
                path = gui.selectorD();
            } else {
                path = setManager.getVaule("cardDataSavePath");
            }
            File file = new File(path + "\\" + runtime.pushName() + ".json");
            fw = new FileWriter(file);
            fw.write(json_write);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            if(fw != null){
                try{
                    fw.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
