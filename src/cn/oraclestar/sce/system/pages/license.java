package cn.oraclestar.sce.system.pages;

import java.io.IOException;

import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.router.pages;

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

public class license extends cn.oraclestar.sce.system.router.router_core {
    public static boolean openWeb(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "start", "\"\"", url).start();
            } else if (os.contains("mac")) {
                new ProcessBuilder("open", url).start();
            } else {
                try {
                    new ProcessBuilder("xdg-open", url).start();
                } catch (IOException e) {
                    String[] browsers = {"google-chrome", "chrome", "chromium", "firefox", "brave", "opera"};
                    boolean launched = false;
                    for (String b : browsers) {
                        try {
                            new ProcessBuilder(b, url).start();
                            launched = true;
                            break;
                        } catch (IOException ignored) { }
                    }
                    if (!launched) return false;
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    @Override
    protected void buildPage()
    {
        _ui.insertTitle("(关于) 开源许可证");
        _ui.insertHint("本软件基于GNU GPLv3协议开发", Color.GRAY);
        _ui.insertNewLine();
        _ui.insertText("SCE-UNIVERSE  Copyright (C) 2025  OracleLoadstar");
        _ui.insertNewLine();
        _ui.insertText("This program comes with ABSOLUTELY NO WARRANTY;");
        
        //_ui.insertText("This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.");
        //_ui.insertNewLine();
        //_ui.insertText("under certain conditions; type `show c' for details.");
        
        _ui.insertNewLine();
        _ui.insertOption("查看完整协议", (int)1);
        _ui.insertOption("返回", (int)0);
        _ui.insertInput();
    }
    @Override
    protected int handlePageInput(String input)
    {
        if (input.equals("1")) 
        {
            if(!openWeb("https://www.gnu.org/licenses/")){
                _ui.display("× 当前设备无法直接打开! ×", true, Color.RED);
			    _ui.display("https://www.gnu.org/licenses/", true);
			    _ui.display("按下回车键继续");
			    _ui.getUserInput();
            }
        }
        else if (input.equals("0")) 
        {
            return  pages.ABOUT;
        }
        return pages.CURRENT;
        
    }
}