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

public class contribution extends cn.oraclestar.sce.system.router.router_core{
    @Override
    protected void buildPage()
    {
        _ui.insertTitle("(关于) 贡献指南");
        _ui.insertHint("SCE项目组由衷的感谢每一个贡献者");
        
        _ui.insertNewLine();
        _ui.insertHint("排名不分先后", Color.GRAY);
        _ui.insertNewLine();
        _ui.insertText("开发者:");
        _ui.insertOption("xingguangcuican6666", (int)100);
        _ui.insertOption("LaT-SKY", (int)101);
        
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
        else if (input.equals("100"))
        {
            if(!(cn.oraclestar.sce.system.pages.license.openWeb("https://github.com/xingguangcuican6666"))){
                _ui.display("× 当前设备无法直接打开! ×", true, Color.RED);
			    _ui.display("https://github.com/xingguangcuican6666", true);
			    _ui.display("按下回车键继续");
			    _ui.getUserInput();
            }
        }
        else if (input.equals("101"))
        {
            if(!(cn.oraclestar.sce.system.pages.license.openWeb("https://github.com/LaT-SKY"))){
                _ui.display("× 当前设备无法直接打开! ×", true, Color.RED);
			    _ui.display("https://github.com/LaT-SKY", true);
			    _ui.display("按下回车键继续");
			    _ui.getUserInput();
            }
        }
        return pages.CURRENT;
    }
}
