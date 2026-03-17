package cn.oraclestar.sce.system.tools;

/*
################################################
#                                              #
#        Another:xingguangcuican6666           #
#        Date:2025-11-24                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

public class hash {
    public static int hashCode(String string){
        if(string.hashCode() <= 100){
            return string.hashCode()+100;
        } else {
            return string.hashCode();
        }
    }
}
