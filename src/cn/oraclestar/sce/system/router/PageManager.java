package cn.oraclestar.sce.system.router;

import java.util.HashMap;
import java.util.Map;

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

abstract public class PageManager extends pageheader {
    protected static Map<Integer,pageheader> _pages = new HashMap<>();
    protected static int _currentPage;
    public PageManager(){
        this._currentPage = pages.EXIT;
    };
    public static void addPage(int id,pageheader page){
        _pages.put(id,page);
    };
    abstract public void setInitialPage(int id);
    abstract public void start() throws Exception;

    //Overrive
    protected void buildPage() throws Exception{};
    protected int handlePageInput(String input){return 0;};
}
