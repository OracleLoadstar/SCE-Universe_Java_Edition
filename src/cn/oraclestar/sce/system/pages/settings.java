package cn.oraclestar.sce.system.pages;

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

public class settings extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage()
    {
        _ui.insertTitle("(主界面) 设置");
        _ui.insertOption("显示", (int)1);
        _ui.insertOption("计算", (int)2);
        _ui.insertOption("文件", (int)3);
        _ui.insertOption("系统", (int)4);
        _ui.insertOption("预览", (int)5);
        _ui.insertOption("开发者", (int)6);
        
        _ui.insertNewLine();
        _ui.insertOption("返回", (int)0);
        
        _ui.insertNewLine();
        _ui.insertInput();
    }
    @Override
    protected int handlePageInput(String input) 
    {
        if (input.equals("1"))
        {
            return cn.oraclestar.sce.system.router.pages.DISPLAY;
        }
        else if (input.equals("2"))
        {
            return cn.oraclestar.sce.system.router.pages.CALCULATE;
        }
        else if (input.equals("3"))
        {
            return cn.oraclestar.sce.system.router.pages.FILE;
        }
        else if (input.equals("4"))
        {
            return cn.oraclestar.sce.system.router.pages.SYSTEM;
        }
        else if (input.equals("5"))
        {
            return cn.oraclestar.sce.system.router.pages.PREVIEW;
        }
        else if (input.equals("6"))
        {
            return cn.oraclestar.sce.system.router.pages.DEVELOPER;
        }
        else if (input.equals("0"))
        {
            return cn.oraclestar.sce.system.router.pages.MAIN;
        }
        else
        {
            return cn.oraclestar.sce.system.router.pages.CURRENT;
        }
    }
};
