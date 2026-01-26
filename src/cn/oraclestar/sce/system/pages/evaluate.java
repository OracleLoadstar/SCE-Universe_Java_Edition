package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.global;
import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;
import cn.oraclestar.sce.system.setManager.setManager_core;

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

//TODO add some settings
//TODO fix bug for set settings
public class evaluate extends router_core {
    private global global_var = global.getcore();
    private boolean isUnique = false;
    @Override
    protected void buildPage(){
        try{
            _ui.insertTitle("(开始) " + global_var.uma1.pushName() + "预设");
            if (isUnique) _ui.insertOption("切换至基础属性", (int)200, Color.GRAY); else _ui.insertOption("切换至固有属性", (int)200, Color.GRAY);
            _ui.insertOptionWithoutNewLine("类型: ", (int)100);
            switch (global_var.uma1.pushType())
            {
                case 0:
                _ui.insertText("速度", Color.BLUE);
                break;
                case 1:
                _ui.insertText("耐力", Color.RED);
                break;
                case 2:
                _ui.insertText("力量", Color.YELLOW);
                break;
                case 3:
                _ui.insertText("根性", Color.PINK);
                break;
                case 4:
                _ui.insertText("智力", Color.GREEN);
                break;
                default:
                _ui.insertText("未知", Color.GRAY);
                break;
            }
            _ui.insertNewLine();
            if (!isUnique)
                {
                _ui.insertOptionWithoutNewLine("友情加成: ", (int)101);
                _ui.insertText(String.valueOf(global_var.uma1.pushRate("friendshipBonus")));
                _ui.insertOptionWithoutNewLine("训练加成: ", (int)102);
                _ui.insertText(String.valueOf(global_var.uma1.pushRate("traningEffect")));
                _ui.insertOptionWithoutNewLine("干劲加成: ", (int)103);
                _ui.insertText(String.valueOf(global_var.uma1.pushRate("moodEffect")));
                if ((Boolean)setManager_core.getVaule("isShowFriendshipRate"))
                    {
                    global_var.uma1.evalV2();
                    _ui.insertHint("友情训练倍率 { " + String.valueOf(global_var.uma1.pushEpt("friendshipRate")) + " }", Color.GRAY);
                    _ui.insertNewLine();
                }
                _ui.insertOptionWithoutNewLine("得 意 率: ", (int)104);
                _ui.insertText(String.valueOf(global_var.uma1.pushRate("specialtyPriority")));
                if ((Boolean)setManager_core.getVaule("isShowTrueSpecialtyRate"))
                    {
                    global_var.uma1.evalV3();
                    _ui.insertHint("{ " +
                    String.valueOf(global_var.uma1.pushEpt("specialtyRate")) + " " +
                    String.valueOf(global_var.uma1.pushEpt("unspecialtyRate") * 4) + " " +
                    String.valueOf(global_var.uma1.pushEpt("nonTrainingRate")) + " }",Color.GRAY);
                    _ui.insertNewLine();
                }
                _ui.insertOptionWithoutNewLine("初期羁绊: ", (int)105);
                _ui.insertText(String.valueOf(global_var.uma1.pushRate("initalFriendship")));
            }
            else
                {
                _ui.insertOptionWithoutNewLine("友情加成: ", (int)101);
                _ui.insertText(String.valueOf(global_var.uma1.pushUnique("friendshipBonus")));
                _ui.insertNewLine();
                _ui.insertOptionWithoutNewLine("训练加成: ", (int)102);
                _ui.insertText(String.valueOf(global_var.uma1.pushUnique("traningEffect")));
                _ui.insertOptionWithoutNewLine("干劲加成: ", (int)103);
                _ui.insertText(String.valueOf(global_var.uma1.pushUnique("moodEffect")));
                if ((Boolean)setManager_core.getVaule("isShowFriendshipRate"))
                    {
                    global_var.uma1.evalV2();
                    _ui.insertHint("友情训练倍率 { " + String.valueOf(global_var.uma1.pushEpt("friendshipRate")) + " }", Color.GRAY);
                    _ui.insertNewLine();
                }
                _ui.insertOptionWithoutNewLine("得 意 率: ", (int)104);
                _ui.insertText(String.valueOf(global_var.uma1.pushUnique("specialtyPriority")));
                if ((Boolean)setManager_core.getVaule("isShowTrueSpecialtyRate"))
                    {
                    global_var.uma1.evalV3();
                    _ui.insertHint("{ " +
                    String.valueOf(global_var.uma1.pushEpt("specialtyRate")) + " " +
                    String.valueOf(global_var.uma1.pushEpt("unspecialtyRate") * 4) + " " +
                    String.valueOf(global_var.uma1.pushEpt("nonTrainingRate")) + " }", Color.GRAY);
                    _ui.insertNewLine();
                }
                _ui.insertOptionWithoutNewLine("初期羁绊: ", (int)105);
                _ui.insertText(String.valueOf(global_var.uma1.pushUnique("initalFriendship")));
            }
            _ui.insertNewLine();
            _ui.insertOptionWithoutNewLine("速度加成: ", (int)111);
            _ui.insertText(String.valueOf(global_var.uma1.pushBonus("speed")));
            _ui.insertOptionWithoutNewLine("耐力加成: ", (int)112);
            _ui.insertText(String.valueOf(global_var.uma1.pushBonus("stamina")));
            _ui.insertOptionWithoutNewLine("力量加成: ", (int)113);
            _ui.insertText(String.valueOf(global_var.uma1.pushBonus("power")));
            _ui.insertOptionWithoutNewLine("根性加成: ", (int)114);
            _ui.insertText(String.valueOf(global_var.uma1.pushBonus("guts")));
            _ui.insertOptionWithoutNewLine("智力加成: ", (int)115);
            _ui.insertText(String.valueOf(global_var.uma1.pushBonus("wit")));
            _ui.insertOptionWithoutNewLine("S P 加成: ", (int)116);
            _ui.insertText(String.valueOf(global_var.uma1.pushBonus("sp")));
            if ((Boolean)setManager_core.getVaule("isShowAllFriendshipRate"))
                {
                _ui.insertHint("总友情加成 { " + String.valueOf(global_var.uma1.pushEpt("allFriendshipRate")) + " }",Color.GRAY);
            }
            _ui.insertNewLine();
            
            _ui.insertOption("修改支援卡名称",(int)1);
            _ui.insertOption("清空预设", (int)2);
            _ui.insertOption("开始评估", (int)3,Color.GREEN);
            
            if ((Boolean)setManager_core.getVaule("isCalcV5"))
                {
                _ui.insertNewLine();
                _ui.insertOptionWithoutNewLine("循环次数: { ", (int)4, Color.GRAY);
                _ui.insertSpan(String.valueOf((int)(setManager_core.getVaule("defaultLoopV5"))), Color.GRAY);
                _ui.insertText(" }", Color.GRAY);
            }
            
            _ui.insertNewLine();
            _ui.insertOption("返回", (int)0);
            
            _ui.insertNewLine();
            _ui.insertInput();
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    @Override
    protected int handlePageInput(String input)
    {
        try{
            if (input.equals("200"))
                {
                isUnique = !isUnique;
                return pages.CURRENT;
            }
            else if (input.equals("100"))
                {
                String inp = displayEditInfo("类型", global_var.uma1.pushType());
                global_var.uma1.getType(toNum(inp));
                return pages.CURRENT;
            }
            else if (input.equals("101"))
                {
                if (isUnique)
                    {
                    String inp = displayEditInfo("友情加成", global_var.uma1.pushUnique("friendshipBonus"));
                    global_var.uma1.getUnique("friendshipBonus", toNum(inp));
                }
                else
                    {
                    String inp = displayEditInfo("友情加成", global_var.uma1.pushRate("friendshipBonus"));
                    global_var.uma1.getRate("friendshipBonus", toNum(inp));
                }
                return pages.CURRENT;
            }
            else if (input.equals("102")) 
                {
                if (isUnique)
                    {
                    String inp = displayEditInfo("训练加成", global_var.uma1.pushUnique("traningEffect"));
                    global_var.uma1.getUnique("traningEffect", toNum(inp));
                }
                else
                    {
                    String inp = displayEditInfo("训练加成", global_var.uma1.pushRate("traningEffect"));
                    global_var.uma1.getRate("traningEffect", toNum(inp));
                }
                return pages.CURRENT;
            }
            else if (input.equals("103"))
                {
                if (isUnique)
                    {
                    String inp = displayEditInfo("干劲加成", global_var.uma1.pushUnique("moodEffect"));
                    global_var.uma1.getUnique("moodEffect", toNum(inp));
                }
                else
                    {
                    String inp = displayEditInfo("干劲加成", global_var.uma1.pushRate("moodEffect"));
                    global_var.uma1.getRate("moodEffect", toNum(inp));
                }
                return pages.CURRENT;
            }
            else if (input.equals("104"))
                {
                if (isUnique)
                    {
                    String inp = displayEditInfo("得意率", global_var.uma1.pushUnique("specialtyPriority"));
                    global_var.uma1.getUnique("specialtyPriority", toNum(inp));
                }
                else
                    {
                    String inp = displayEditInfo("得意率", global_var.uma1.pushRate("specialtyPriority"));
                    global_var.uma1.getRate("specialtyPriority", toNum(inp));
                }
                return pages.CURRENT;
            }
            else if (input.equals("105"))
                {
                if (isUnique)
                    {
                    String inp = displayEditInfo("初期羁绊", global_var.uma1.pushUnique("initalFriendship"));
                    global_var.uma1.getUnique("initalFriendship", toNum(inp));
                }
                else
                    {
                    String inp = displayEditInfo("初期羁绊", global_var.uma1.pushRate("initalFriendship"));
                    global_var.uma1.getRate("initalFriendship", toNum(inp));
                }
                return pages.CURRENT;
            }
            else if (input.equals("111"))
                {
                String inp = displayEditInfo("速度加成", global_var.uma1.pushBonus("speed"));
                global_var.uma1.getBonus("speed", toNum(inp));
                return pages.CURRENT;
            }
            else if (input.equals("112"))
                {
                String inp = displayEditInfo("耐力加成", global_var.uma1.pushBonus("stamina"));
                global_var.uma1.getBonus("stamina", toNum(inp));
                return pages.CURRENT;
            }
            else if (input.equals("113"))
                {
                String inp = displayEditInfo("力量加成", global_var.uma1.pushBonus("power"));
                global_var.uma1.getBonus("power", toNum(inp));
                return pages.CURRENT;
            }
            else if (input.equals("114"))
                {
                String inp = displayEditInfo("根性加成", global_var.uma1.pushBonus("guts"));
                global_var.uma1.getBonus("guts", toNum(inp));
                return pages.CURRENT;
            }
            else if (input.equals("115"))
                {
                String inp = displayEditInfo("智力加成", global_var.uma1.pushBonus("wit"));
                global_var.uma1.getBonus("wit", toNum(inp));
                return pages.CURRENT;
            }
            else if (input.equals("116"))
                {
                String inp = displayEditInfo("S P 加成", global_var.uma1.pushBonus("sp"));
                global_var.uma1.getBonus("sp", toNum(inp));
                return pages.CURRENT;
            }
            else if (input.equals("1"))            
                {
                String name = displayEditInfo("名称",global_var.uma1.pushName());
                global_var.uma1.getName(name);
                return pages.CURRENT;
            }
            else if (input.equals("2"))
                {
                global_var.uma1.destory();
                return pages.CURRENT;
            }
            else if (input.equals("3"))
                {
                global_var.uma1.evalV1();
                if (!(Boolean)(setManager_core.getVaule("isCalcV5")) || (Boolean)setManager_core.getVaule("isV5rectify"))
                    {
                    return pages.CALCULATE_MAIN;
                }
                if ((Boolean)setManager_core.getVaule("isCalcV5") && !(Boolean)setManager_core.getVaule("isV5rectify"))
                    {
                    return pages.V5RECTIFY;
                }
                return pages.ERR;
            }
            else if (input.equals("4"))
                {
                setManager_core.changeVaule("defaultLoopV5", toInt(displayEditInfo("循环次数", (int)setManager_core.getVaule("defaultLoopV5"))));
                return pages.CURRENT;
            }
            else if (input.equals("0"))
                {
                return pages.START;
            }
            else
                {
                return pages.CURRENT; // 保持在当前页面
            }
        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return pages.CURRENT;
    }
    
}
