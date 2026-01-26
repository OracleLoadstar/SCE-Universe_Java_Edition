package cn.oraclestar.sce.system;

import java.util.ArrayList;
import java.util.List;

import cn.oraclestar.sce.system.umaCore.umaCore_core;

/*
################################################
#                                              #
#        Another:xingguangcuican6666           #
#        Date:2025-11-24                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

public class global {
    volatile private static global core = null;
    volatile public static umaCore_core uma1 = null;
    volatile public static umaCore_core uma0 = null;
    volatile public static List<umaCore_core> compareUma = null;
    private global() throws Exception{
        // 获取设置实例
        cn.oraclestar.sce.system.setManager.setManager_core.getCore();
        umaCore_core uma1 = new umaCore_core();
        umaCore_core uma0 = new umaCore_core();
        List<umaCore_core> compareUma = new ArrayList<>();
    }
    public static global getcore(){
        if(core !=null || uma1 != null || uma0 !=null || compareUma != null) return core;
        synchronized(global.class){
            if(core == null || uma1 == null || uma0 == null || compareUma == null){
                try {
                    uma1 = new umaCore_core();
                    core = new global();
                    uma0 = new umaCore_core();
                    compareUma = new ArrayList<>();
                    return core;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return core;
    }
}
