package cn.oraclestar.sce.system.tools;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Properties;

import cn.oraclestar.sce.system.global;
// import cn.oraclestar.sce.system.pages.start;
import cn.oraclestar.sce.system.umaCore.umaCore_core;;

/*
################################################
#                                              #
#        Another:xingguangcuican6666           #
#        Date:2025-12-01                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

public class read_umaCore {
    public umaCore_core uma1 = global.uma1;
    public static boolean readRes(umaCore_core runtime){
        FileReader fr = null;
        String temp_json = "";
        try{
            fr = new FileReader(new File(gui.selector()));
            int ch;
            while((ch = fr.read()) != -1){
                temp_json += (char)ch;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            try{
                fr.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        };
        // System.out.println(temp_json);
        // return false;
        Properties props = new Properties();
        String temp_json_ = temp_json.replaceAll("[{}]","").replaceAll(",","\n").replaceAll(":","=");
        // System.out.println(temp_json_);
        try {
            props.load(new StringReader(temp_json_));
            // umaCore_core uma1 = global.uma1;
            runtime.getName(props.getProperty("\"name\"").replace("\"",""));
            runtime.getType((int)Integer.parseInt(props.getProperty("\"type\"")));
            runtime.getRate("friendshipBonus", (int)Integer.parseInt(props.getProperty("\"friendshipBonus\"")));
            runtime.getRate("moodEffect", (int)Integer.parseInt(props.getProperty("\"moodEffect\"")));
            runtime.getRate("traningEffect", (int)Integer.parseInt(props.getProperty("\"traningEffect\"")));
            runtime.getRate("initalFriendship", (int)Integer.parseInt(props.getProperty("\"initalFriendship\"")));
            runtime.getRate("specialtyPriority", (int)Integer.parseInt(props.getProperty("\"specialtyPriority\"")));
            runtime.getUnique("friendshipBonus", (int)Integer.parseInt(props.getProperty("\"_friendshipBonus\"")));
            runtime.getUnique("moodEffect", (int)Integer.parseInt(props.getProperty("\"_moodEffect\"")));
            runtime.getUnique("traningEffect", (int)Integer.parseInt(props.getProperty("\"_traningEffect\"")));
            runtime.getUnique("initalFriendship", (int)Integer.parseInt(props.getProperty("\"_initalFriendship\"")));
            runtime.getUnique("specialtyPriority", (int)Integer.parseInt(props.getProperty("\"_specialtyPriority\"")));
            runtime.getBonus("speed", (int)Integer.parseInt(props.getProperty("\"speed\"")));
            runtime.getBonus("stamina", (int)Integer.parseInt(props.getProperty("\"stamina\"")));
            runtime.getBonus("power", (int)Integer.parseInt(props.getProperty("\"power\"")));
            runtime.getBonus("guts", (int)Integer.parseInt(props.getProperty("\"guts\"")));
            runtime.getBonus("wit", (int)Integer.parseInt(props.getProperty("\"wit\"")));
            runtime.getBonus("sp", (int)Integer.parseInt(props.getProperty("\"sp\"")));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fr != null){
                try{
                    fr.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
