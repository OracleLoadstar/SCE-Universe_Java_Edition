package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.setManager.setManager_core;

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

public class main extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage() throws Exception{
        _ui.insertTitle(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("homeTitle"));
        _ui.insertOption("开始", (int)1);
        _ui.insertOption("设置", (int)2);
        _ui.insertOption("关于", (int)3);
        if((Boolean)setManager_core.getVaule("mod")){
            _ui.insertOption("插件设置",(int)4);
        }
        _ui.insertNewLine();
        _ui.insertOption("退出", (int)0);
        
        _ui.insertNewLine();
        _ui.insertInput();
    };
    @Override
    protected int handlePageInput(String input)
    {
        if (input == null) {
            return cn.oraclestar.sce.system.router.pages.CURRENT;
        }
        input = input.trim();
        if (input.equals("1"))
        {
            return cn.oraclestar.sce.system.router.pages.START;
        }
        else if (input.equals("2"))
        {
            return cn.oraclestar.sce.system.router.pages.SETTING;
        }
        else if (input.equals("3"))
        {
            return cn.oraclestar.sce.system.router.pages.ABOUT;
        }
        else if (input.equals("4"))
        {
            try {
                if((Boolean)(setManager_core.getVaule("mod")))
                {
                    return cn.oraclestar.sce.system.router.pages.MODLIST;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return pages.CURRENT;
            }
        }
        else if (input.equals("0"))
        {
            return cn.oraclestar.sce.system.router.pages.EXIT;
        }
        else
        {
            return cn.oraclestar.sce.system.router.pages.CURRENT;
        }
        return cn.oraclestar.sce.system.router.pages.CURRENT;
    }
}
