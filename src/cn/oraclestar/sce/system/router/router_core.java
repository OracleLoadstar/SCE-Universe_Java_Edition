package cn.oraclestar.sce.system.router;

import java.util.Map;

import cn.oraclestar.sce.system.modloader.modlist;
import cn.oraclestar.sce.system.modloader.modloader_main;
import cn.oraclestar.sce.system.setManager.setManager_core;

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

public class router_core extends PageManager {
    public static void innermod(){
        for(Map.Entry<String,cn.oraclestar.sce.system.modloader.modloader_main> temp : cn.oraclestar.sce.system.modloader.modlist.modlist_core.entrySet()){
            temp.getValue().innerToPage(_ui,_currentPage);
        }
    }
    public int run() throws Exception{
        int nextPage = pages.CURRENT;
        while (nextPage == pages.CURRENT){
            _ui.cls();
            _ui.reset();
            buildPage();
            System.err.printf("[Settings]: 写入设置\n");
            setManager_core.SaveSettings();
            _ui.run();
            System.err.printf("[Main] 当前页面ID："+String.valueOf(_currentPage)+"\n");
            String input = _ui.getUserInput();
            nextPage = handlePageInput(input);
            if(nextPage != pages.CURRENT)return nextPage;
            for(Map.Entry<String,cn.oraclestar.sce.system.modloader.modloader_main> temp : cn.oraclestar.sce.system.modloader.modlist.modlist_core.entrySet()){
                nextPage = temp.getValue().innerHandle(input,_currentPage);
                if(nextPage != pages.CURRENT)break;
            }
        }
        return nextPage;
    }
    
    public void setInitialPage(int id){
        if (_pages.containsKey(id)){
            _currentPage = id;
        }
        else 
        {
            // 如果指定的页面不存在，则设置为退出状态
            _currentPage = pages.EXIT;
        }
    }
    public void start() throws Exception{
        if(_currentPage == pages.EXIT){
            return;
        }
        while (_currentPage != pages.EXIT){
            while (true) {
                pageheader page = _pages.get(_currentPage); // 等同于 _pages.find(...)
                if (page == null) {                       // 等同于 it == _pages.end()
                    break;
                }
                int nextPage = page.run();              // 调用页面的 run()
                _currentPage = nextPage;
            }
        }
    }
}
