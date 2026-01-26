package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.global;
import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;
import cn.oraclestar.sce.system.setManager.setManager;
import cn.oraclestar.sce.system.tools.Json;
import cn.oraclestar.sce.system.umaCore.umaCore_core;
import cn.oraclestar.sce.system.umaEdu.umaSCE_EDU;
import cn.oraclestar.sce.system.umaEdu.umaSCE_EDU;

/*
################################################
#                                              #
#        Another:Lat-SKY                       #
#        Mofity by xingguangcuican6666         #
#        Date:2025-12-01                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

public class v5rectify extends router_core {
    private int rectifyNum = 100000;
    @Override
    protected void buildPage()
    {
        try{
            _ui.insertTitle("(V5) 校正");
            if((boolean)setManager.getVaule("isV5rectify")){  
                _ui.insertHint("当前状态: { 已校正 }", Color.GREEN);
            } else {
                _ui.insertHint("当前状态: { 未校正 }", Color.RED);
            }
            _ui.insertNewLine();
            _ui.insertOption("速度", (int)100);
            _ui.insertText("{ " + Json.toMap((String)setManager.getVaule("v5DefaultSpeed")).get("main").toString() + "(主) " +
            Json.toMap((String)setManager.getVaule("v5DefaultSpeed")).get("sub").toString() + "(副) " +
            Json.toMap((String)setManager.getVaule("v5DefaultSpeed")).get("SP").toString() + "(SP) }");
            
            _ui.insertNewLine();
            _ui.insertOption("耐力", (int)101);
            _ui.insertText("{ " + Json.toMap((String)setManager.getVaule("v5DefaultStamina")).get("main").toString() + "(主) " +
            Json.toMap((String)setManager.getVaule("v5DefaultStamina")).get("sub").toString() + "(副) " +
            Json.toMap((String)setManager.getVaule("v5DefaultStamina")).get("SP").toString() + "(SP) }");
            
            _ui.insertNewLine();
            _ui.insertOption("力量", (int)102);
            _ui.insertText("{ " + Json.toMap((String)setManager.getVaule("v5DefaultPower")).get("main").toString() + "(主) " +
            Json.toMap((String)setManager.getVaule("v5DefaultPower")).get("sub").toString() + "(副) " +
            Json.toMap((String)setManager.getVaule("v5DefaultPower")).get("SP").toString() + "(SP) }");
            
            _ui.insertNewLine();
            _ui.insertOption("根性", (int)103);
            _ui.insertText("{ " + Json.toMap((String)setManager.getVaule("v5DefaultGuts")).get("main").toString() + "(主) " +
            Json.toMap((String)setManager.getVaule("v5DefaultGuts")).get("sub").toString() + "(副) " +
            Json.toMap((String)setManager.getVaule("v5DefaultGuts")).get("SP").toString() + "(SP) }");
            
            _ui.insertNewLine();
            _ui.insertOption("智力", (int)104);
            _ui.insertText("{ " + Json.toMap((String)setManager.getVaule("v5DefaultWit")).get("main").toString() + "(主) " +
            Json.toMap((String)setManager.getVaule("v5DefaultWit")).get("sub").toString() + "(副) " +
            Json.toMap((String)setManager.getVaule("v5DefaultWit")).get("SP").toString() + "(SP) }");
            
            _ui.insertNewLine();
            _ui.insertOption("校正全部", (int)1);
            _ui.insertOptionWithoutNewLine("校正次数 : { ", (int)2);
            _ui.insertText(String.valueOf(rectifyNum) + " }");
            
            _ui.insertNewLine();
            _ui.insertOption("返回", (int)0);
            
            _ui.insertInput();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
    @Override
    protected int handlePageInput(String input)
    {
        try
        {
            umaCore_core uma0 = global.uma0;
            
            uma0.destory();
            if (input.equals("100"))
                {
                uma0.getType(0);
                umaSCE_EDU edu = new umaSCE_EDU();
                edu.run(uma0, rectifyNum);
                var ept = edu.pushExEpt();
                // ept.v5main /= rectifyNum;
                // ept.v5fold /= rectifyNum;
                // ept.v5sp /= rectifyNum;
                // ept.toString();
                // set1.setSetting<std::vector<int>>("v5DefaultSpeed", { ept.v5main,ept.v5fold,ept.v5sp });
                setManager.changeVaule("v5DefaultSpeed",ept.toString());
            }
            else if (input.equals("101"))
                {
                uma0.getType(1);
                umaSCE_EDU edu = new umaSCE_EDU();
                edu.run(uma0, rectifyNum);
                var ept = edu.pushExEpt();
                // ept.v5main /= rectifyNum;
                // ept.v5fold /= rectifyNum;
                // ept.v5sp /= rectifyNum;
                // set1.setSetting<std::vector<int>>("v5DefaultStamina", { ept.v5main,ept.v5fold,ept.v5sp });
                setManager.changeVaule("v5DefaultStamina",ept.toString());
            }
            else if (input.equals("102"))
                {
                uma0.getType(2);
                umaSCE_EDU edu = new umaSCE_EDU();
                edu.run(uma0, rectifyNum);
                var ept = edu.pushExEpt();
                // ept.v5main /= rectifyNum;
                // ept.v5fold /= rectifyNum;
                // ept.v5sp /= rectifyNum;
                setManager.changeVaule("v5DefaultPower", ept.toString());
            }
            else if (input.equals("103"))
                {
                uma0.getType(3);
                umaSCE_EDU edu = new umaSCE_EDU();
                edu.run(uma0, rectifyNum);
                var ept = edu.pushExEpt();
                // ept.v5main /= rectifyNum;
                // ept.v5fold /= rectifyNum;
                // ept.v5sp /= rectifyNum;
                setManager.changeVaule("v5DefaultGuts", ept.toString());
            }
            else if (input.equals("104"))
                {
                uma0.getType(4);
                umaSCE_EDU edu = new umaSCE_EDU();
                edu.run(uma0, rectifyNum);
                var ept = edu.pushExEpt();
                // ept.v5main /= rectifyNum;
                // ept.v5fold /= rectifyNum;
                // ept.v5sp /= rectifyNum;
                setManager.changeVaule("v5DefaultWit", ept.toString());
            }
            else if (input.equals("1"))
                {
                uma0.getType(0);
                umaSCE_EDU edu = new umaSCE_EDU();
                edu.run(uma0, rectifyNum);
                var ept = edu.pushExEpt();
                // ept.v5main /= rectifyNum;
                // ept.v5fold /= rectifyNum;
                // ept.v5sp /= rectifyNum;
                setManager.changeVaule("v5DefaultSpeed", ept.toString());
                setManager.changeVaule("isV5Speedrectify",true);
                
                uma0.getType(1);
                edu.run(uma0, rectifyNum);
                ept = edu.pushExEpt();
                // ept.v5main /= rectifyNum;
                // ept.v5fold /= rectifyNum;
                // ept.v5sp /= rectifyNum;
                setManager.changeVaule("v5DefaultStamina", ept.toString());
                setManager.changeVaule("isV5Staminarectify", true);
                
                uma0.getType(2);
                edu.run(uma0, rectifyNum);
                ept = edu.pushExEpt();
                // ept.v5main /= rectifyNum;
                // ept.v5fold /= rectifyNum;
                // ept.v5sp /= rectifyNum;
                setManager.changeVaule("v5DefaultPower", ept.toString());
                setManager.changeVaule("isV5Powerrectify", true);
                
                uma0.getType(3);
                edu.run(uma0, rectifyNum);
                ept = edu.pushExEpt();
                // ept.v5main /= rectifyNum;
                // ept.v5fold /= rectifyNum;
                // ept.v5sp /= rectifyNum;
                setManager.changeVaule("v5DefaultGuts", ept.toString());
                setManager.changeVaule("isV5Gutsrectify", true);
                
                uma0.getType(4);
                edu.run(uma0, rectifyNum);
                ept = edu.pushExEpt();
                // ept.v5main /= rectifyNum;
                // ept.v5fold /= rectifyNum;
                // ept.v5sp /= rectifyNum;
                setManager.changeVaule("v5DefaultWit", ept.toString());
                setManager.changeVaule("isV5Witrectify", true);
            }
            else if (input.equals("2"))
                {
                rectifyNum = toInt(displayEditInfo("校正次数", rectifyNum));
            }
            else if (input.equals("0"))
                {
                return pages.CALCULATE;
            }
            
            if ((boolean)setManager.getVaule("isV5Speedrectify"))
                {
                if ((boolean)setManager.getVaule("isV5Staminarectify"))
                    {
                    if ((boolean)setManager.getVaule("isV5Powerrectify"))
                        {
                        if ((boolean)setManager.getVaule("isV5Gutsrectify"))
                            {
                            if ((boolean)setManager.getVaule("isV5Witrectify"))
                                {
                                setManager.changeVaule("isV5rectify", true);
                            }
                        }
                    }
                }
            }
            return pages.CURRENT;
        } catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return pages.CURRENT;
    }
}
