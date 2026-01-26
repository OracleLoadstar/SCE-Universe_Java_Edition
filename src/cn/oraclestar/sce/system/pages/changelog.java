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

public class changelog extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage()
    {
        _ui.insertTitle("(关于) 更新日志");
        _ui.insertList(
        (new cn.oraclestar.sce.system.UI.listitem("重置 用户界面",cn.oraclestar.sce.system.UI.Color.DEFAULT)),
        (new cn.oraclestar.sce.system.UI.listitem("优化 用户交互",cn.oraclestar.sce.system.UI.Color.DEFAULT)),
        (new cn.oraclestar.sce.system.UI.listitem("优化 目录结构",cn.oraclestar.sce.system.UI.Color.DEFAULT)),
        (new cn.oraclestar.sce.system.UI.listitem("增加 V5算法",cn.oraclestar.sce.system.UI.Color.DEFAULT)),
        (new cn.oraclestar.sce.system.UI.listitem("增加 CES算法",cn.oraclestar.sce.system.UI.Color.DEFAULT)),
        (new cn.oraclestar.sce.system.UI.listitem("增加 支援卡对比",cn.oraclestar.sce.system.UI.Color.DEFAULT))
        );
        
        _ui.insertNewLine();
        _ui.insertInput(true);
    };
    @Override    
    protected int handlePageInput(String input)
    {
        return cn.oraclestar.sce.system.router.pages.ABOUT;
    }
    
}
