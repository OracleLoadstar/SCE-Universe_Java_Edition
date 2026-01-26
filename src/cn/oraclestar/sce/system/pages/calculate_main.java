package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.global;
import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;
import cn.oraclestar.sce.system.setManager.setManager;

public class calculate_main extends router_core {
    private boolean isPass = false;
    @Override
    protected void buildPage(){
        try
        {
            isPass = false;
            _ui.insertTitle("评估中...");
            _ui.insertHint("正在检验数值...", Color.GRAY);
            if (!global.uma1.checked())
                {
                _ui.insertHint("数值检验未通过，请检查支援卡属性。", Color.RED);
                _ui.insertInput(true);
                return;
            }
            _ui.insertHint("检验成功！");
            if ((Boolean)setManager.getVaule("isCalcV2"))
                {
                _ui.insertHint("正在计算V2评分...", Color.GRAY);
                if (!global.uma1.evalV2())
                    {
                    _ui.insertHint("计算失败，请检查支援卡属性。", Color.RED);
                    _ui.insertInput(true);
                    return;
                }
                _ui.insertHint("计算成功！");
            }
            if ((Boolean)setManager.getVaule("isCalcV3"))
                {
                _ui.insertHint("正在计算V3评分...", Color.GRAY);
                if (!global.uma1.evalV3())
                    {
                    _ui.insertHint("计算失败，请检查支援卡属性。", Color.RED);
                    _ui.insertInput(true);
                    return;
                }
                _ui.insertHint("计算成功！");
            }
            if ((Boolean)setManager.getVaule("isCalcV4"))
                {
                _ui.insertHint("正在计算V4评分...", Color.GRAY);
                if (!global.uma1.evalV4())
                    {
                    _ui.insertHint("计算失败，请检查支援卡属性。", Color.RED);
                    _ui.insertInput(true);
                    return;
                }
                _ui.insertHint("计算成功！");
            }
            
            _ui.insertNewLine();
            _ui.insertInput(true);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected int handlePageInput(String input){
        if(!isPass){
            return pages.CALCULATE_V5;
        }
        else 
        {
            return pages.RESULT;
        }
    }
}
