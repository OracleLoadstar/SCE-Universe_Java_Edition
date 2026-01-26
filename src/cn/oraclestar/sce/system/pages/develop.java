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

public class develop extends cn.oraclestar.sce.system.router.router_core {
    @Override	
    protected void buildPage() 
    {
        try
        {
            _ui.insertTitle("(设置) 开发者");
            _ui.insertOptionWithoutNewLine("WebUI端口 { ",(int)1);
            _ui.insertSpan(String.valueOf((Integer)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("port")));
            _ui.insertText(" }");
            
            _ui.insertNewLine();
            _ui.insertOptionWithoutNewLine("返回",(int)0);
            _ui.insertNewLine();
            _ui.insertNewLine();
            _ui.insertInput();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected int handlePageInput(String input)
    {
        try{
            if (input.equals("1"))
            {
                cn.oraclestar.sce.system.setManager.setManager_core.changeVaule("port", (int)toNum(displayEditInfo("WebUI端口", String.valueOf((Integer)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("port"))))));
            }else if (input.equals("0")){
                return cn.oraclestar.sce.system.router.pages.SETTING;
            }
            return cn.oraclestar.sce.system.router.pages.CURRENT;
        } catch (Exception e){
            e.printStackTrace();
        }
        return cn.oraclestar.sce.system.router.pages.CURRENT;
    }
    
}
