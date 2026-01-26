package cn.oraclestar.sce.system.pages;
import cn.oraclestar.sce.system.global;

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

public class start extends cn.oraclestar.sce.system.router.router_core {
    // 获取global下的uma1实例
    private global global_var = global.getcore();
    protected void buildPage()
    {
        _ui.insertTitle("(主界面) 开始");
        _ui.insertOption("创建新的支援卡", (int)1);
        _ui.insertOption("打开保存的支援卡", (int)2);
        _ui.insertOption("联机获取支援卡", (int)3);
        _ui.insertOption("对比支援卡", (int)4);
        _ui.insertOption("返回", (int)0);
        
        _ui.insertNewLine();
        _ui.insertInput();
    }
    protected int handlePageInput(String input) 
    {
        if (input.equals("1"))
        {
            global_var.uma1.destory();
            return cn.oraclestar.sce.system.router.pages.EVALUATE;
        }
        else if (input.equals("2"))
        {
            return cn.oraclestar.sce.system.router.pages.OPEN;
        }
        else if (input.equals("3"))
        {
            return cn.oraclestar.sce.system.router.pages.ONLINE;
        }
        else if (input.equals("4"))
        {
            return cn.oraclestar.sce.system.router.pages.COMPARE;
        }
        else if (input.equals("0"))
        {
            return cn.oraclestar.sce.system.router.pages.MAIN;
        }
        else
        {
            return cn.oraclestar.sce.system.router.pages.CURRENT; // 保持在当前页面
        }
    }
}
