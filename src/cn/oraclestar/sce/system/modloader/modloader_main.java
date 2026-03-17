package cn.oraclestar.sce.system.modloader;

/*
################################################
#                                              #
#        Another:xingguangcuican6666           #
#        Date:2025-11-24                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

public interface modloader_main {
    final String pluginName = "";
    final String version = "";
    void onEnable();
    void onDisable();
    void innerToPage(cn.oraclestar.sce.system.UI.UI_core ui_core,int now_page);
    int innerHandle(String Input,int now_page);
}
