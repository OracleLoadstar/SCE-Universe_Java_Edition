package cn.oraclestar.sce.system.UI;

import cn.oraclestar.sce.system.UI.fmt.rgb;

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

public class Tiles {
    public Tiles(int type,String text,rgb colorRgb){
        this.type =type;
        this.textContent = text;
        this.color =colorRgb;
    }
    @Deprecated
    public Tiles(int type,String text){
        this.type =type;
        this.textContent = text;
    }
    /*
    * 0-文本
    * 1-提示
    * 2-标题
    * 3-链表
    * 4-换行
    * 5-选项
    * 6-输入框
    * 7-Span
    * 8-无换行选项
    */
    public int type = 0;
    public String textContent = "";
    /*
    * 0-无颜色
    * 1-红色
    * 2-绿色
    * 3-蓝色
    * 4-黄色
    * 5-紫色
    * 6-青色
    * 7-灰色
    */
    public rgb color = Color.DEFAULT;
}
