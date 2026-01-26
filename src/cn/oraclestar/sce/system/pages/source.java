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

public class source extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage()
    {
        _ui.insertTitle("(关于) 源代码");
        _ui.insertOption("SCE-UNIVERSE", (int)1, Color.GREEN);
        _ui.insertOption("SCE-CREATION", (int)2);
        _ui.insertOption("SCE-TRACK", (int)3);
        _ui.insertOption("SCE-NEXT", (int)4, Color.RED);
        
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
        else if (input.equals("1"))
        {
            cn.oraclestar.sce.system.pages.license.openWeb("");
        }
        else if (input.equals("2"))
        {
            cn.oraclestar.sce.system.pages.license.openWeb("https://github.com/OracleLoadstar/SupportCard_Evaluator_Creation_Release");
        }
        else if (input.equals("3"))
        {
            cn.oraclestar.sce.system.pages.license.openWeb("https://github.com/OracleLoadstar/SupportCard_Evaluator_Track_Release");
        }
        else if (input.equals("4"))
        {
            cn.oraclestar.sce.system.pages.license.openWeb("");
        }
        return pages.CURRENT;
    }
}
