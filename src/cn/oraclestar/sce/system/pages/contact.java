package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.router.pages;

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

public class contact extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage()
    {
        _ui.insertTitle("(关于) 联系我们");
        _ui.insertSpan("邮箱 { ");
        _ui.insertSpan("oracleloadstar@oraclestar.cn", Color.BLUE);
        _ui.insertText(" }");
        
        _ui.insertNewLine();
        _ui.insertOption("返回", (int)0);
        
        _ui.insertNewLine();
        _ui.insertInput();
    }
    @Override
    protected int handlePageInput(String input)
    {
        if (input.equals("0"))
        {
            return pages.ABOUT;
        }
        return pages.CURRENT;
    }
}
