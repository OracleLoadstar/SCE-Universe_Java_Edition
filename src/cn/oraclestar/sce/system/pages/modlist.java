package cn.oraclestar.sce.system.pages;

import java.util.Map;

import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;

public class modlist extends router_core {
    @Override
    protected void buildPage(){
        _ui.insertTitle("插件列表");
        if(cn.oraclestar.sce.system.modloader.modlist.modlist_List.isEmpty()){
            _ui.insertText("已加载 0 个插件");
        } else {
            _ui.insertSpan("已加载 ");
            _ui.insertSpan(String.valueOf(cn.oraclestar.sce.system.modloader.modlist.modlist_List.size()));
            _ui.insertSpan(" 个插件");
        }
        _ui.insertNewLine();
        if(!cn.oraclestar.sce.system.modloader.modlist.modlist_List.isEmpty())
        {
            for(Map.Entry<String,Map<String,Map<String,String>>> modItem : cn.oraclestar.sce.system.modloader.modlist.modlist_List.entrySet()){
                Map<String,Map<String,String>> modTemp = modItem.getValue();
                for(Map.Entry<String,Map<String,String>> modItem2 : modTemp.entrySet())
                {
                    Map<String,String> modTemp2 = modItem2.getValue();
                    for(Map.Entry<String,String> modItem3 : modTemp2.entrySet()){
                        _ui.insertSpan(modItem.getKey()+"  "+modItem2.getKey()+"  "+modItem3.getKey()+"  "+modItem3.getValue());
                        // _ui.insertSpan();
                        _ui.insertNewLine();
                    }
                }
            }
        }
        else {
            _ui.insertText("无");
            _ui.insertNewLine();
        }
        _ui.insertNewLine();
        _ui.insertOption("返回",(int)0);
        _ui.insertNewLine();
        _ui.insertInput();
    } 
    @Override
    protected int handlePageInput(String input)
    {
        if (input.equals("0")){
            return pages.MAIN;
        }
        return pages.CURRENT;
    }
    
}
