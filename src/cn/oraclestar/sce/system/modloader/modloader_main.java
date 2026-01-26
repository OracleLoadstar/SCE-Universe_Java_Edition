package cn.oraclestar.sce.system.modloader;

public interface modloader_main {
    final String pluginName = "";
    final String version = "";
    void onEnable();
    void onDisable();
    void innerToPage(cn.oraclestar.sce.system.UI.UI_core ui_core,int now_page);
    int innerHandle(String Input,int now_page);
}
