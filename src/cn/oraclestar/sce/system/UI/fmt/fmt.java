package cn.oraclestar.sce.system.UI.fmt;

/*
 ################################################
 #                                              #
 #        Another:xingguangcuican6666           #
 #        Date:2025-11-24                       #
 #        Copyright 2025-2026 OracleLoadStar    #
 #                                              #
 ################################################
 */

public class fmt {
    @Deprecated
    public static void print(String Controllor,String format,String text){
        // use print (no automatic newline) to avoid duplicate blank lines;
        // caller (post/display) decides when to print newline.
        System.out.print(Controllor + text);
        System.out.print("\033[0m");
    };

    @Deprecated
    public static String fg(rgb color){
        return "\033[38;2;"+String.valueOf(color.R)+";"+String.valueOf(color.G)+";"+String.valueOf(color.B)+"m";   
    }
    
    @Deprecated
    public static String bg(rgb color){
        return "\033[48;2;"+String.valueOf(color.R)+";"+String.valueOf(color.G)+";"+String.valueOf(color.B)+"m";   
    }
}
