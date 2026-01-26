package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.UI.fmt.rgb;
import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;
import cn.oraclestar.sce.system.setManager.setManager;

public class errorloader extends router_core {
    @Override
    protected void buildPage() throws Exception
    {
        _ui.insertTitle("错误界面");
        _ui.insertHint("错误：无法正确加载设置文件",new rgb(255,0,0));
        _ui.insertNewLine();
        _ui.insertText(setManager.ErrorMassger);
        _ui.insertNewLine();
        _ui.insertOption("继续",1);
        _ui.insertOption("退出",0);
        _ui.insertNewLine();
        _ui.insertInput();
    }
    @Override
    protected int handlePageInput(String input) 
    {
        if(input.equals("1")){
            return pages.WELCOME;
        } 
        else if(input.equals("0")){
            return pages.EXIT;
        }
        return pages.EXIT;
    }

}
