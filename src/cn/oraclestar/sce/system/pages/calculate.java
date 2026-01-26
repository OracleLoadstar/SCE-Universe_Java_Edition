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

public class calculate extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage()
    {
        try{
            _ui.insertTitle("(设置) 计算");
            _ui.insertOptionWithoutNewLine("计算总倍率 { ", (int)1);
            if ((Boolean)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isCalcV2"))
            {
                _ui.insertText("开 }");
                _ui.insertHint("开启后会计算包含属性加成的训练倍率", cn.oraclestar.sce.system.UI.Color.GRAY);
            }
            else
            {
                _ui.insertText("关 }");
            }
            
            _ui.insertNewLine();
            _ui.insertOptionWithoutNewLine("计算期望评分 { ", (int)2);
            if ((Boolean)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isCalcV3"))
            {
                _ui.insertText("开 }");
                _ui.insertHint("开启后会计算V3评分", cn.oraclestar.sce.system.UI.Color.GRAY);
            }
            else
            {
                _ui.insertText("关 }");
            }
            
            _ui.insertNewLine();
            _ui.insertOptionWithoutNewLine("计算期望+评分 { ",(int)3);
            if ((Boolean)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isCalcV4"))
            {
                _ui.insertText("开 }");
                _ui.insertHint("开启后会计算V4评分", cn.oraclestar.sce.system.UI.Color.GRAY);
            }
            else
            {
                _ui.insertText("关 }");
            }
            _ui.insertNewLine();
            _ui.insertOptionWithoutNewLine("计算模拟育成评分 { ", (int)4);
            if((Boolean)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isCalcV5"))
            {
                _ui.insertText("开 }");
                _ui.insertHint("开启后会计算V5评分", cn.oraclestar.sce.system.UI.Color.GRAY);
                _ui.insertNewLine();
                if ((Boolean)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isCalcV5"))
                {
                    _ui.insertOption("默认循环次数: { " + String.valueOf((Integer)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("defaultLoopV5"))) + " }", (int)5);
                    _ui.insertHint("最大循环次数: " + String.valueOf(2147483647), cn.oraclestar.sce.system.UI.Color.GRAY);
                    _ui.insertNewLine();
                    if((Boolean)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isV5rectify"))){
                        _ui.insertOption("校正 : { 已校正 }", (int)6);
                    } else 
                    {
                        _ui.insertOption("校正 : { 未校正 }", (int)6);
                    }
                }
            }
            else
            {
                _ui.insertText("关 }");
            }
            
            _ui.insertNewLine();
            _ui.insertOption("返回", (int)0);
            
            _ui.insertNewLine();
            _ui.insertInput();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected int handlePageInput(String input)
    { 
        try{
            if (input.equals("1"))
            {
                cn.oraclestar.sce.system.setManager.setManager_core.toggleSetting(("isCalcV2"));
                return cn.oraclestar.sce.system.router.pages.CURRENT;
            }
            else if (input.equals("2"))
            {
                cn.oraclestar.sce.system.setManager.setManager_core.toggleSetting(("isCalcV3"));
                return cn.oraclestar.sce.system.router.pages.CURRENT;
            }
            else if (input.equals("3"))
            {
                cn.oraclestar.sce.system.setManager.setManager_core.toggleSetting(("isCalcV4"));
                return cn.oraclestar.sce.system.router.pages.CURRENT;
            }
            else if (input.equals("4"))
            {
                cn.oraclestar.sce.system.setManager.setManager_core.toggleSetting(("isCalcV5"));
                return cn.oraclestar.sce.system.router.pages.CURRENT;
            }
            else if (input.equals("5"))
            {
                if ((Boolean)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isCalcV5")))
                {
                    cn.oraclestar.sce.system.setManager.setManager_core.changeVaule("defaultLoopV5", toInt(displayEditInfo("默认循环次数", String.valueOf((Integer)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("defaultLoopV5")))));
                }
                return cn.oraclestar.sce.system.router.pages.CURRENT;
                
            }
            else if (input.equals("6"))
            {
                if ((Boolean)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isCalcV5")))
                {
                    return cn.oraclestar.sce.system.router.pages.V5RECTIFY;
                }
            }
            else if (input.equals("0"))
            {
                return cn.oraclestar.sce.system.router.pages.SETTING;
            }
            else
            {
                return cn.oraclestar.sce.system.router.pages.CURRENT;
            }
        } catch (Exception e){
            
            e.printStackTrace();
            // System.exit(1);
        }
        return cn.oraclestar.sce.system.router.pages.CURRENT;
    }
}
