package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.global;
import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;
import cn.oraclestar.sce.system.tools.read_umaCore;

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

public class open extends router_core {
    @Override
    protected void buildPage()
    {
        _ui.insertTitle("(开始) 打开支援卡");
        _ui.insertOption("选择支援卡文件", (int)1);
        _ui.insertOption("返回", (int)0);
        
        _ui.insertNewLine();
        _ui.insertInput();
    };
    @Override
    protected int handlePageInput(String input)
    {
        if (input .equals( "1")){
            if(read_umaCore.readRes(global.uma1))
            {
                return pages.EVALUATE;
            }
            else
                {
                _ui.display("未选择文件或选择了无效的文件。", true, Color.RED);
                _ui.display("按下回车键返回...",true ,Color.GRAY);
                _ui.getUserInput();
                return pages.CURRENT;
            }
        }
        else
        {
            return pages.START;
        }
    }
}
