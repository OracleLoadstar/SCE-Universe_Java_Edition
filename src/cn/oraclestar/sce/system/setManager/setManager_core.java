package cn.oraclestar.sce.system.setManager;

/*
################################################
#                                              #
#        Another:xingguangcuican6666           #
#        Date:2025-11-24                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

public class setManager_core extends setManager {
    //init
    volatile static setManager_core rescore = null;

    public static cn.oraclestar.sce.system.setManager.setManager_core getCore() throws Exception{
        if(rescore != null){
            return rescore;
        }
        synchronized(cn.oraclestar.sce.system.setManager.setManager_core.class){
            if(rescore == null){
                rescore = new setManager_core();
            }
        }
        return rescore;
    }

    private setManager_core() throws Exception{
        super();
    };
}
