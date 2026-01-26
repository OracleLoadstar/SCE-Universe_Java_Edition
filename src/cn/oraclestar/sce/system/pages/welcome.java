package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.UI.listitem;
import cn.oraclestar.sce.system.router.router_core;
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

public class welcome extends router_core {
    @Override
    protected void buildPage(){
        _ui.insertText("欢迎来到SCE-UNIVERSE！");
        _ui.insertHint("这是一个基于SCE-TRACK V5.0B1版本制成的下一代SCE控制台应用", Color.GRAY);
		_ui.insertNewLine();
		_ui.insertText("我们带来了以下更新：");
        _ui.insertList(
            (new listitem("重制 SCE内核",Color.GRAY)),
            (new listitem("重置 用户界面",Color.GRAY)),
            (new listitem("优化 用户交互",Color.GRAY)),
            (new listitem("优化 目录结构",Color.GRAY)),
            (new listitem("完成 5.0B1未完成的功能",Color.GRAY)),
            (new listitem("修复 5.0B1的已知问题",Color.GRAY))
        );
        _ui.insertHint("更多更新请查看更新日志...", Color.GREEN);

        _ui.insertNewLine();
		_ui.insertHint("UNIVERSE-1.0B1", Color.GRAY);

		_ui.insertNewLine();
		_ui.insertInput(true);
    };
    @Override
    protected int handlePageInput(String input){
        return pages.MAIN;
    }
}
