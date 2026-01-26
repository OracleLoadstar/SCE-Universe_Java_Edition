package cn.oraclestar.sce.system.router;

import java.util.Scanner;

import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.UI.UI_core;

/*
################################################
#                                              #
#        Another:Lat-SKY                       #
#        Mofity by xingguangcuican6666         #
#        Date:2025-11-24                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

abstract public class pageheader implements AutoCloseable {
    protected static UI_core _ui = new UI_core();
    // @Deprecated
    // abstract void buildPage();
    protected String displayEditInfo(String editContent, int oldValue)
    {
        _ui.display("您正在修改: " + editContent, true);
        _ui.display("当前值: " + String.valueOf(oldValue), true);
        _ui.display("# ", false, Color.YELLOW);
        return _ui.getUserInput();
    }
    protected String displayEditInfo(String editContent, String oldValue)
    {
        _ui.display("您正在修改: " + editContent, true);
        _ui.display("当前值: " + oldValue, true);
        _ui.display("# ", false, Color.YELLOW);
        return _ui.getUserInput();
    }
    protected int toInt(String olaValue){
        Scanner scanner = new Scanner(olaValue);
        if(scanner.hasNextInt()){
            // scanner.close();
            return scanner.nextInt();
        }
        return 0;
    }
    @Deprecated
    protected int toNum(String olaValue){
        Scanner scanner = new Scanner(olaValue);
        if(scanner.hasNextInt()){
            return (int)scanner.nextInt();
        }
        return 0;
    }
    
    // abstract public ~umaSCE_Page
    @Override
    public void close(){};

    abstract public int run() throws Exception;
}
