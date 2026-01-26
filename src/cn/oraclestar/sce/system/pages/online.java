package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;

// TODO online
/*
################################################
#                                              #
#        Another:Lat-SKY                       #
#        Mofity by xingguangcuican6666         #
#        Date:2025-12-01                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/


public class online extends router_core {
    @Override
    protected void buildPage()
    {
        _ui.insertTitle("(开始) 联网搜索");
        _ui.insertNewLine();
        
        _ui.insertNewLine();
        _ui.insertInput();
    }
    @Override
    protected int handlePageInput(String input)
    {
        return pages.START;
    }
};
