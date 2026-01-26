package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.global;
import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;
import cn.oraclestar.sce.system.setManager.setManager;
import cn.oraclestar.sce.system.tools.write_umaCore;
import cn.oraclestar.sce.system.umaEdu.umaSCE_EDU;

public class calculate_v5 extends router_core {
    private boolean isPass = false;
    @Override
    protected void buildPage(){
        try
        {
            if ((boolean)setManager.getVaule("isCalcV5"))
                {
                _ui.display("评估中...",true,Color.YELLOW);
                _ui.display("------------", true, Color.DEFAULT);
                _ui.display("+ 正在计算V5... +", true, Color.GRAY);
                umaSCE_EDU edu = new umaSCE_EDU();
                if (!edu.run(global.uma1, (int)((Integer)(setManager.getVaule("defaultLoopV5")))))
                    {
                    _ui.insertHint("计算失败，这是什么鬼错误。", Color.RED);
                    _ui.insertInput(true);
                    return;
                }
                pages_global.v5ex = edu.pushExEpt();
                pages_global.v5highest = edu.pushHighestEpt();
                pages_global.v5lowest = edu.pushLowestEpt();
                
                _ui.insertNewLine();
                _ui.insertHint("V5计算成功！");
            }
            isPass = true;
            
            _ui.insertNewLine();
            _ui.insertHint("计算完成！");
            
            if ((boolean)setManager.getVaule("isAutoSaveCardData"))
                {
                //保存文件
                //创建映射 -> res.write(global.uma1,set1.getSetting<std::wstring>("cardDataSavePath"));
                write_umaCore.saveRes(global.uma1);
                _ui.insertNewLine();
                _ui.insertHint("支援卡保存成功！");
            }
            
            _ui.insertNewLine();
            _ui.insertInput(true);
        } catch ( Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected int handlePageInput(String input)
    {
        if (!isPass)
        {
            return pages.EVALUATE;
        }
        else
        {
            return pages.RESULT;
        }
    }
}
