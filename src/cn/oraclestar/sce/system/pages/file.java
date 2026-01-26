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

public class file extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage()
    {
        try{
            _ui.insertTitle("(设置) 文件");
            _ui.insertOptionWithoutNewLine("自动保存支援卡 { ", (int)1);
            if ((Boolean) cn.oraclestar.sce.system.setManager.setManager_core.getVaule("isAutoSaveCardData"))
            {
                _ui.insertText("开 }");
                _ui.insertHint("评估结束后立即保存支援卡文件",cn.oraclestar.sce.system.UI.Color.GRAY);
            }
            else
            {
                _ui.insertText("关 }");
            }
            
            _ui.insertNewLine();
            _ui.insertOption("支援卡默认保存路径", (int)2);
            _ui.insertHint("影响自动保存时的路径", cn.oraclestar.sce.system.UI.Color.GRAY);
            _ui.insertText("{ " + (String)(cn.oraclestar.sce.system.setManager.setManager_core.getVaule("cardDataSavePath")) + " }");
            
            //_ui.insertNewLine();
            //_ui.insertOption("支援卡默认打开路径", 3);
            //_ui.insertHint("影响打开支援卡时的路径", cn.oraclestar.sce.system.UI.Color.GRAY);
            //_ui.insertText("{ " + ws2s(set1.getSetting<wstring>("cardDataOpenPath")) + " }");
            
            _ui.insertNewLine();
            _ui.insertOption("配置文件导出路径", (int)3);
            _ui.insertHint("影响导出配置文件时的路径", cn.oraclestar.sce.system.UI.Color.GRAY);
            _ui.insertText("{ " + (String) cn.oraclestar.sce.system.setManager.setManager_core.getVaule("settingDataSavePath") + " }");
            
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
        try{
            if (input.equals("1"))
            {
                cn.oraclestar.sce.system.setManager.setManager_core.toggleSetting("isAutoSaveCardData");
            }
            else if (input.equals("2"))
            {
                try{
                    cn.oraclestar.sce.system.setManager.setManager_core.changeVaule("cardDataSavePath",cn.oraclestar.sce.system.tools.gui.selectorD());
                }catch(Exception e){
                    //兜底方案
                    _ui.display("无法打开gui",true,cn.oraclestar.sce.system.UI.Color.RED);
                    cn.oraclestar.sce.system.setManager.setManager_core.changeVaule("cardDataSavePath",displayEditInfo("支援卡保存路径",(String)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("cardDataSavePath")));
                }
            }
            //else if (input.equals("3"))
            //{
            //	set1.setSetting<std::wstring>("cardDataOpenPath", selectFolder());
            //}
            else if (input.equals("3"))
            {
                try{
                    cn.oraclestar.sce.system.setManager.setManager_core.changeVaule("settingDataSavePath", cn.oraclestar.sce.system.tools.gui.selectorD());
                }catch(Exception e){
                    _ui.display("无法打开gui",true,cn.oraclestar.sce.system.UI.Color.RED);
                    cn.oraclestar.sce.system.setManager.setManager_core.changeVaule("settingDataSavePath",displayEditInfo("配置文件导出路径",(String)cn.oraclestar.sce.system.setManager.setManager_core.getVaule("settingDataSavePath")));
                }
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
}
