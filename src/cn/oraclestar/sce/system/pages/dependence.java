package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.UI.listitem;
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

public class dependence extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage()
		{
			_ui.insertTitle("(关于) 依赖项");
			_ui.insertList((new listitem("JVM(doge)",cn.oraclestar.sce.system.UI.Color.DEFAULT)),(new listitem("javax",cn.oraclestar.sce.system.UI.Color.DEFAULT)));

			_ui.insertNewLine();
			_ui.insertOption("返回", (int)0);
			
			_ui.insertNewLine();
			_ui.insertInput();
		}
    @Override
	protected int handlePageInput(String input)
		{
			if (input.equals("0"))
			{
				return pages.ABOUT;
			}
			return pages.CURRENT;
		}
}
