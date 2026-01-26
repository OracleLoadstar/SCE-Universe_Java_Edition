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

public class preview extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage()
    {
        try{
        _ui.insertTitle("(设置) 预览");
        _ui.insertOptionWithoutNewLine("Declincrease评估 { ", (int)10);
        if ((Boolean)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isCalcDI"))
        {
            _ui.insertText("开 }");
        }
        else
        {
            _ui.insertText("关 }");
        }
        
        _ui.insertNewLine();
        _ui.insertOptionWithoutNewLine("模拟评分(V5) { ", (int)11);
        if ((Boolean)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isCalcV5"))
        {
            _ui.insertText("开 }");
        }
        else
        {
            _ui.insertText("关 }");
        }
        
        _ui.insertNewLine();
        _ui.insertOption("返回", (int)0);
        
        _ui.insertNewLine();
        _ui.insertInput();

} catch (Exception e){
    e.printStackTrace();
}
    }
    @Override
    protected int handlePageInput(String input)
    {
        if (input.equals("0"))
        {
            return cn.oraclestar.sce.system.router.pages.SETTING;
        }
        else if (input.equals("10"))
        {
            cn.oraclestar.sce.system.setManager.setManager_core.toggleSetting("isCalcDI");
        }
        else if (input.equals("11"))
        {
            cn.oraclestar.sce.system.setManager.setManager_core.toggleSetting("isCalcV5");
        }
        return cn.oraclestar.sce.system.router.pages.CURRENT;
    }
}
