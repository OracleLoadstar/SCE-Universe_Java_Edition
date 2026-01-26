package cn.oraclestar.sce.system.pages;

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

public class about extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage() 
    {
        _ui.insertTitle("(主界面) 关于");
        _ui.insertHint("V1.0B1", cn.oraclestar.sce.system.UI.Color.GREEN);
        
        _ui.insertNewLine();
        _ui.insertOption("软件介绍", (int)1);
        _ui.insertOption("更新日志", (int)2);
        _ui.insertOption("开源许可证", (int)3);
        _ui.insertOption("贡献指南", (int)4);
        _ui.insertOption("源代码", (int)5);
        _ui.insertOption("依赖项", (int)6);
        _ui.insertOption("联系我们", (int)7);
        
        _ui.insertNewLine();
        _ui.insertOption("返回", (int)0);
        
        _ui.insertNewLine();
        _ui.insertInput();
    }
    @Override
    protected int handlePageInput(String input)
    {
        if (input.equals("1"))
        {
            return cn.oraclestar.sce.system.router.pages.DESCRIPTION;
        }
        else if (input.equals("2"))
        {
            return cn.oraclestar.sce.system.router.pages.CHANGELOG;
        }
        else if (input.equals("3"))
        {
            return cn.oraclestar.sce.system.router.pages.LICENSE;
        }
        else if (input.equals("4"))
        {
            return cn.oraclestar.sce.system.router.pages.CONTRIBUTION;
        }
        else if (input.equals("5"))
        {
            return cn.oraclestar.sce.system.router.pages.SOURCE;
        }
        else if (input.equals("6"))
        {
            return cn.oraclestar.sce.system.router.pages.DEPENDENCY;
        }
        else if (input.equals("7"))
        {
            return cn.oraclestar.sce.system.router.pages.CONTACT;
        }
        else if (input.equals("0"))
        {
            return cn.oraclestar.sce.system.router.pages.MAIN;
        }
        else
        {
            return cn.oraclestar.sce.system.router.pages.CURRENT;
        }
    }
};