package cn.oraclestar.sce.system.UI;

import java.util.List;

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

abstract public class ui_header {
    protected static List<Tiles> article;
    protected static boolean isBuild = false;
    // protected static Tiles article = new Tiles();
    protected static char listPrefix = 'a';
    protected static int buildLine = 0;
    abstract protected void post(String textContent, rgb color);
    abstract protected boolean build();
    
    protected static void ensureArticle(){
        if (article == null){
            article = new java.util.ArrayList<>();
        }
    }
    
    public static listitem listItem = new listitem("",Color.DEFAULT);
    //color::YELLOW
    abstract public void insertTitle(String textContent, rgb color);
    abstract public void insertTitle(String textContent);
    
    //color::DEFAULT
    abstract public void insertText(String textContent, rgb color);
    abstract public void insertText(String textContent);
    
    //color::DEFAULT
    abstract public void insertSpan(String textContent, rgb color);
    abstract public void insertSpan(String textContent);
    
    //color::GREEN
    abstract public void insertHint(String textContent, rgb color);
    abstract public void insertHint(String textContent);
    
    //color::YELLOW
    //false
    abstract public void insertInput(boolean isPause, rgb color);
    abstract public void insertInput(boolean isPause);
    abstract public void insertInput();
    
    abstract public void insertNewLine();
    
    //color::DEFAULT
    abstract public void insertOption(String textContent, int index, rgb color);
    abstract public void insertOption(String textContent, int index);
    
    //color::DEFAULT
    abstract public void insertOptionWithoutNewLine(String textContent, int index, rgb color);
    abstract public void insertOptionWithoutNewLine(String textContent, int index);
    
    public static void insertList(listitem...listitems){
        ensureArticle();
        for (listitem item : listitems){
            Tiles temp = new Tiles((int)0,"");
            temp.type = type.LIST;
            temp.textContent = item.textContent;
            temp.color = item.color;
            article.add(temp);
        }
    };
    
    //color::DEFAULT
    //false
    abstract public void display(String textContent, boolean isNewLine, rgb color);
    abstract public void display(String textContent, boolean isNewLine);
    abstract public void display(String textContent);
    
    public static void reset()
    {
        ensureArticle();
        article.clear();
        isBuild = false;
        listPrefix = 'a';
        buildLine = 0;
    };
    
    abstract public void erase(int line) throws Exception;
    abstract public void erase(int startLine, int endLine) throws Exception;
    abstract public void resetList();
    public static void cls(){
        try{
            String os = System.getProperty("os.name").toLowerCase();
            if(os.contains("win")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } 
        }catch (Exception e){
            e.printStackTrace();
        };
    };
    abstract public String getUserInput();
    abstract public boolean run() throws Exception;
}

