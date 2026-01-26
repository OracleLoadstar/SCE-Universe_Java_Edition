package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.UI.Color;

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

public class display extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage() throws Exception
    {
        _ui.insertTitle("(设置) 显示");
        
        _ui.insertOptionWithoutNewLine("展示友情倍率 ", (int)1);
        if ((Boolean)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isShowFriendshipRate")))
        {
            _ui.insertText("{ 开 }");
            _ui.insertHint("在预设界面会展示支援卡的友情训练倍率", Color.GRAY);
            _ui.insertNewLine();
            _ui.insertOptionWithoutNewLine("展示总友情倍率 ", (int)5);
            if ((Boolean)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isShowAllFriendshipRate")))
            {
                _ui.insertText("{ 开 }");
                _ui.insertHint("在预设界面会展示包含属性加成的支援卡友情训练倍率", Color.GRAY);
            }
            else
            {
                _ui.insertText("{ 关 }");
            }
        }
        else
        {
            _ui.insertText("{ 关 }");
        }
        
        _ui.insertNewLine();
        _ui.insertOptionWithoutNewLine("展示真实得意率 ", (int)2);
        if ((Boolean)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isShowTrueSpecialtyRate")))
        {
            _ui.insertText("{ 开 }");
            _ui.insertHint("在预设界面会展示支援卡的真实得意训练概率", Color.GRAY);
        }
        else
        {
            _ui.insertText("{ 关 }");
        }
        
        _ui.insertNewLine();
        // _ui.insertOption("控制台高度 { " + std::to_string(set1.getSetting<unsigned int>("height")) + " }", 3);
        // _ui.insertOption("控制台宽度 { " + std::to_string(set1.getSetting<unsigned int>("width")) + " }", 4);
        // _ui.insertHint("高度不得低于 500 宽度不得低于 450", Color.GRAY);
        // _ui.insertHint("重启生效", Color.GRAY);
        
        _ui.insertNewLine();
        _ui.insertOption("主界面标题 { " + (String)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("homeTitle")) + " }", (int)6);
        
        _ui.insertNewLine();
        _ui.insertOption("返回", (int)0);
        _ui.insertNewLine();
        _ui.insertInput();
    }
    @Override
    protected int handlePageInput(String input) 
    {   
        try{
            if (input.equals("1"))
            {
                if((Boolean)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isShowFriendshipRate")))
                {cn.oraclestar.sce.system.setManager.setManager_core.changeVaule("isShowFriendshipRate",false);}
                else {cn.oraclestar.sce.system.setManager.setManager_core.changeVaule("isShowFriendshipRate",true);}
            }
            else if (input.equals("5"))
            {
                
                cn.oraclestar.sce.system.setManager.setManager_core.toggleSetting("isShowAllFriendshipRate");
                
            }
            else if (input.equals("2"))
            {
                cn.oraclestar.sce.system.setManager.setManager_core.toggleSetting("isShowTrueSpecialtyRate");
            }
            else if (input.equals("6"))
            {
                cn.oraclestar.sce.system.setManager.setManager_core.changeVaule("homeTitle", displayEditInfo("主界面标题", cn.oraclestar.sce.system.setManager.setManager_core.getVaule("homeTitle")));
            }
            else if (input.equals("0"))
            {
                return cn.oraclestar.sce.system.router.pages.SETTING;
            }
            return cn.oraclestar.sce.system.router.pages.CURRENT;
        } catch (Exception e){
            e.printStackTrace();
        }
        return cn.oraclestar.sce.system.router.pages.CURRENT;
    }
};
