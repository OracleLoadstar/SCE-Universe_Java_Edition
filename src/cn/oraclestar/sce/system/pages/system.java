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

public class system extends cn.oraclestar.sce.system.router.router_core{
    @Override
    protected void buildPage()
    {
        _ui.insertTitle("(设置) 系统");
        _ui.insertHint("暂无设置", cn.oraclestar.sce.system.UI.Color.GRAY);
        
        _ui.insertNewLine();
        _ui.insertOption("返回", (int)0);
        
        _ui.insertNewLine();
        _ui.insertInput();
    };
    @Override
    protected int handlePageInput(String input)
    {
        if (input.equals( "0"))
        {
            return cn.oraclestar.sce.system.router.pages.SETTING;
        }
        return cn.oraclestar.sce.system.router.pages.CURRENT;
    }
}
