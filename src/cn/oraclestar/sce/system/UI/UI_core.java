package cn.oraclestar.sce.system.UI;

import java.util.ListIterator;
import java.util.Scanner;

/*
################################################
#                                              #
#        Another:xingguangcuican6666           #
#        Date:2025-11-24                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

// import cn.oraclestar.sce.system.UI.fmt.rgb;
import cn.oraclestar.sce.system.UI.fmt.*;

public class UI_core extends ui_header {
    public UI_core(){
        super();
    }

    protected void post(String textContent,rgb color)
    {
        // print without newline; run() will handle newlines and NEWLINE tiles
        fmt.print(fmt.fg(color), "{}", textContent);
    };
    
    public boolean build(){
        int index =0;
        ensureArticle();
        if(!isBuild){
            ListIterator<Tiles> it = article.listIterator();
            if(buildLine !=0){
                buildLine++;
            }
            for (int i = 0; i < buildLine && it.hasNext(); i++) it.next();
            //规则：
            //1.在标题后插入与标题同等长度的--，作为标题的分割线
            //2.列表由a开始，逐级递增
            //3.1 错误提示(color::RED)： ×textContent×
            //3.2 警告提示(color::YELLOW)： !textContent!
            //3.3 消息提示(color::GREEN)： -textContent-
            //3.4 其他提示(其余颜色)： +textContent+
            while(it.hasNext()){
                Tiles t =it.next();
                try{
                    switch(t.type){
                        case type.TITLE:
                        it.add(new Tiles(type.NEWLINE,""));
                        Tiles prev = it.previous(); // 回到 NEWLINE 前的 TITLE
                        int len = prev.textContent.length();
                        it.next(); // 回到 NEWLINE 后
                        it.add(new Tiles(type.TEXT, "-".repeat(len)));
                        it.add(new Tiles(type.NEWLINE,""));
                        buildLine +=3;
                        break;
                        case type.LIST:
                        Tiles now = it.previous();
                        t.textContent = (char)(listPrefix++) + ". " + t.textContent;
                        it.next();
                        it.add(new Tiles(type.NEWLINE,""));
                        buildLine +=2;
                        break;
                        case type.HINT:
                        Tiles now_2 = it.next();
                        switch (now_2.color.R){
                            case 255:
                            Tiles change1 = it.previous();
                            t.textContent = "× " + t.textContent + " ×";
                            it.next();
                            it.add(new Tiles(type.NEWLINE,""));
                            break;
                            case 250:
                            Tiles change2 = it.previous();
                            t.textContent = "! " + t.textContent + " !";
                            it.next();
                            it.add(new Tiles(type.NEWLINE,""));
                            break;
                            case 83:
                            Tiles change3 = it.previous();
                            t.textContent = "- " + t.textContent + " -";
                            it.next();
                            it.add(new Tiles(type.NEWLINE,""));
                            break;
                            default:
                            Tiles change4 = it.previous();
                            t.textContent = "+ " + t.textContent + " +";
                            it.next();
                            it.add(new Tiles(type.NEWLINE,""));
                            break;
                            
                        }
                        buildLine+=2;
                        break;
                        case type.TEXT:
                        it.add(new Tiles(type.NEWLINE,""));
                        buildLine +=2;
                        break;
                        case type.OPTION:
                        it.add(new Tiles(type.NEWLINE,""));
                        buildLine +=2;
                        break;
                        case type.INPUT:
                        buildLine++;
                        break;
                        case type.SPAN:
                        buildLine++;
                        break;
                        case type.NEWLINE:
                        buildLine++;
                        break;
                        case type.OPTION_WITHOUT_NEWLINE:
                        buildLine++;
                        break;
                        default:
                        it.remove();
                        break;
                    }
                } catch(Exception e) {
                    System.out.println("UI.build ：未知错误");
                }
            }
        }
        isBuild =true;
        return true;
    }
    
    public void insertTitle(String textContent,rgb color){
        ensureArticle();
        article.add(new Tiles(type.TITLE,textContent,color));
        if(isBuild)isBuild = false;
    }
    public void insertTitle(String textContent){
        ensureArticle();
        article.add(new Tiles(type.TITLE,textContent,Color.YELLOW));
        if(isBuild)isBuild = false;
    }
    
    public void insertText(String textContent,rgb color){
        ensureArticle();
        article.add(new Tiles(type.TEXT,textContent,color));
        if(isBuild)isBuild = false;
    }
    public void insertText(String textContent){
        ensureArticle();
        article.add(new Tiles(type.TEXT,textContent,Color.DEFAULT));
        if(isBuild)isBuild = false;
    }
    
    public void insertSpan(String textContent,rgb color){
        ensureArticle();
        article.add(new Tiles(type.SPAN,textContent,color));
        if(isBuild)isBuild=false;
    }
    public void insertSpan(String textContent){
        ensureArticle();
        article.add(new Tiles(type.SPAN,textContent,Color.DEFAULT));
        if(isBuild)isBuild=false;
    }
    
    public void insertHint(String textContent,rgb color){
        ensureArticle();
        article.add(new Tiles(type.HINT,textContent,color));
        if(isBuild)isBuild=false;
    }
    public void insertHint(String textContent){
        ensureArticle();
        article.add(new Tiles(type.HINT,textContent,Color.GREEN));
        if(isBuild)isBuild=false;
    }
    
    public void insertInput(boolean isPause,rgb color){
        ensureArticle();
        if(!isPause){
            article.add(new Tiles(type.INPUT,"# ",color));
        }
        else {
            article.add(new Tiles(type.INPUT,"按下回车继续...",color));
        }
        if (isBuild)isBuild=false;
    }
    public void insertInput(boolean isPause){
        ensureArticle();
        if(!isPause){
            article.add(new Tiles(type.INPUT,"# ",Color.YELLOW));
        }
        else {
            article.add(new Tiles(type.INPUT,"按下回车继续...",Color.YELLOW));
        }
        if (isBuild)isBuild=false;
    }
    public void insertInput(){
        cn.oraclestar.sce.system.router.router_core.innermod();
        ensureArticle();
        article.add(new Tiles(type.INPUT,"# ",Color.YELLOW));
        if (isBuild)isBuild=false;
    }
    
    public void insertNewLine(){
        ensureArticle();
        article.add(new Tiles(type.NEWLINE,""));
        if(isBuild)isBuild=false;
    };
    
    public void insertOption(String textContent,int index,rgb color){
        ensureArticle();
        String fullOption = "[" + String.valueOf(index) + "]" + textContent;
        article.add(new Tiles(type.OPTION,fullOption,color));
        if(isBuild)isBuild= false;
    }
    public void insertOption(String textContent,int index){
        ensureArticle();
        String fullOption = "[" + String.valueOf(index) + "]" + textContent;
        article.add(new Tiles(type.OPTION,fullOption,Color.DEFAULT));
        if(isBuild)isBuild= false;
    }
    
    //color::DEFAULT
    public void insertOptionWithoutNewLine(String textContent, int index, rgb color){
        ensureArticle();
        String fullOption = "[" + String.valueOf(index) + "]" + textContent;
        article.add(new Tiles(type.OPTION_WITHOUT_NEWLINE,fullOption,color));
        if(isBuild)isBuild= false;
    }
    public void insertOptionWithoutNewLine(String textContent, int index){
        ensureArticle();
        String fullOption = "[" + String.valueOf(index) + "]" + textContent;
        article.add(new Tiles(type.OPTION_WITHOUT_NEWLINE,fullOption,Color.DEFAULT));
        if(isBuild)isBuild= false;
    }
    
    //default
    //false
    public void display(String textContent,boolean isNewLine,rgb color){
        fmt.print(fmt.fg(color),"{}",textContent);
        if(isNewLine){
            System.out.println();
        }
    }
    public void display(String textContent, boolean isNewLine){
        fmt.print(fmt.fg(Color.DEFAULT),"{}",textContent);
        if(isNewLine){
            System.out.println();
        }
    }
    public void display(String textContent){
        fmt.print(fmt.fg(Color.DEFAULT),"{}",textContent);
    }
    
    public void erase(int line) throws Exception{
        ensureArticle();
        if(line>=article.size()){
            throw(new Exception("Ui.erase ：行号超出范围"));
        }
        article.remove(line);
        if(listPrefix > 'a'){
            listPrefix--;
        } else {
            listPrefix = 'a';
        }
        if(isBuild)isBuild =false;
    }
    public void erase(int startLine, int endLine) throws Exception{
        ensureArticle();
        if (startLine >= article.size() || endLine >= article.size() || startLine > endLine)
        {
            throw(new Exception("ui.erase ：行号超出范围或起始行大于结束行"));
        }
        article.subList(startLine,endLine).clear();
        if(listPrefix > 'a'){
            listPrefix -= (endLine - startLine +1);
        } else
        {
            listPrefix = 'a';
        }
        if(isBuild)isBuild =false;
    }
    
    public void resetList()
    {   
        listPrefix = 'a';
        if (isBuild)isBuild = false;
    }
    
    
    public String getUserInput()
    {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    };
    
    public boolean run() throws Exception
    {
        ensureArticle();
        if (isBuild)
        {
            return true;
        }
        if (!build())
        {
            throw(new Exception("ui::run ：构建失败"));
        }
        // var it = article.iterator();
        for (var it : article)
        {
            switch (it.type)
            {
                case type.TITLE:
                    post(it.textContent,it.color);
                    break;
                case type.TEXT:
                    post(it.textContent, it.color);
                    break;
                case type.HINT:
                    post(it.textContent, it.color);
                    break;
                case type.LIST:
                    post(it.textContent, it.color);
                    break;
                case type.OPTION:
                    post(it.textContent, it.color);
                    break;
                case type.NEWLINE:
                    System.out.println();
                    break;
                case type.INPUT:
                    post(it.textContent, it.color);
                    break;
                case type.SPAN:
                    post(it.textContent, it.color);
                    break;
                case type.OPTION_WITHOUT_NEWLINE:
                    post(it.textContent, it.color);
                    break;
                default:
                    throw(new Exception("ui.run ：不存在的类型"));
            }
        }
        return true;
    }
    
    
}
