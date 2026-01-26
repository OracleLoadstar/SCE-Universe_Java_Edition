package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.global;
import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;
import cn.oraclestar.sce.system.setManager.setManager;
import cn.oraclestar.sce.system.tools.Json;
import cn.oraclestar.sce.system.tools.write_umaCore;

//TODO Fix V5
//TODO Setiings save

public class reselt extends router_core {
    @Override
    protected void buildPage()
    {
        try
        {
            _ui.insertTitle("(预设) " + global.uma1.pushName());
            _ui.insertSpan("类型: ");
            switch (global.uma1.pushType())
            {
                case 0:
                _ui.insertText("速度");
                break;
                case 1:
                _ui.insertText("耐力");
                break;
                case 2:
                _ui.insertText("力量");
                break;
                case 3:
                _ui.insertText("根性");
                break;
                case 4:
                _ui.insertText("智力");
                break;
                default:
                _ui.insertText("未知");
                break;
            }
            
            _ui.insertNewLine();
            _ui.insertSpan("友情加成: ( ");
            _ui.insertText(String.valueOf(global.uma1.pushRate("friendshipBonus")) + " + " + String.valueOf(global.uma1.pushUnique("friendshipBonus")) + " )");
            _ui.insertSpan("训练加成: ( ");
            _ui.insertText(String.valueOf(global.uma1.pushRate("traningEffect")) + " + " + String.valueOf(global.uma1.pushUnique("traningEffect")) + " )");
            _ui.insertSpan("干劲加成: ( ");
            _ui.insertText(String.valueOf(global.uma1.pushRate("moodEffect")) + " + " + String.valueOf(global.uma1.pushUnique("moodEffect")) + " )");
            _ui.insertSpan("得 意 率: ( ");
            _ui.insertText(String.valueOf(global.uma1.pushRate("specialtyPriority")) + " + " + String.valueOf(global.uma1.pushUnique("specialtyPriority")) + " )");
            _ui.insertSpan("初期羁绊: ( ");
            _ui.insertText(String.valueOf(global.uma1.pushRate("initalFriendship")) + " + " + String.valueOf(global.uma1.pushUnique("initalFriendship")) + " )");
            
            _ui.insertNewLine();
            _ui.insertSpan("属性加成: { ");
            _ui.insertSpan(String.valueOf(global.uma1.pushBonus("speed")) + " ", Color.BLUE);
            _ui.insertSpan(String.valueOf(global.uma1.pushBonus("stamina")) + " ", Color.RED);
            _ui.insertSpan(String.valueOf(global.uma1.pushBonus("power")) + " ", Color.YELLOW);
            _ui.insertSpan(String.valueOf(global.uma1.pushBonus("guts")) + " ", Color.PINK);
            _ui.insertSpan(String.valueOf(global.uma1.pushBonus("wit")) + " ", Color.GREEN);
            _ui.insertSpan(String.valueOf(global.uma1.pushBonus("sp")) + " ", Color.DEFAULT);
            _ui.insertText("}");
            
            if ((Boolean)setManager.getVaule("isCalcV2"))
                {
                _ui.insertNewLine();
                _ui.insertSpan("友情训练倍率: ");
                _ui.insertText(String.valueOf(global.uma1.pushEpt("friendshipRate")));
                _ui.insertSpan("总友情训练倍率: ");
                _ui.insertText(String.valueOf(global.uma1.pushEpt("allFriendshipRate")));
                _ui.insertSpan("友情训练SP获取倍率: ");
                _ui.insertText(String.valueOf(global.uma1.pushEpt("specialtySpRate")));
                _ui.insertSpan("逛街训练倍率: ");
                _ui.insertText(String.valueOf(global.uma1.pushEpt("nonSpecialtyRate")));
                _ui.insertSpan("总逛街训练倍率: ");
                _ui.insertText(String.valueOf(global.uma1.pushEpt("allNonSpecialtyRate")));
                _ui.insertSpan("逛街SP获取倍率: ");
                _ui.insertText(String.valueOf(global.uma1.pushEpt("nonSpecialtySpRate")));
            }
            
            _ui.insertNewLine();
            _ui.insertSpan("真实得意训练率: { ");
            _ui.insertSpan(String.valueOf(global.uma1.pushEpt("specialtyRate")) + " ", Color.GREEN);
            _ui.insertSpan(String.valueOf(global.uma1.pushEpt("unspecialtyRate") * 4.0f) + " ", Color.YELLOW);
            _ui.insertSpan(String.valueOf(global.uma1.pushEpt("nonTrainingRate")) + " ", Color.RED);
            _ui.insertText("}");
            
            if ((Boolean)setManager.getVaule("isCalcV3"))
                {
                _ui.insertNewLine();
                _ui.insertSpan("V3评分: ");
                _ui.insertText(String.valueOf(global.uma1.pushIntEpt("v3")), Color.YELLOW);
            }
            if ((Boolean)setManager.getVaule("isCalcV4"))
                {
                _ui.insertNewLine();
                _ui.insertSpan("V4友情训练评分: ");
                _ui.insertText(String.valueOf(global.uma1.pushIntEpt("v4main")), Color.GREEN);
                _ui.insertSpan("V4逛街训练评分: ");
                _ui.insertText(String.valueOf(global.uma1.pushIntEpt("v4fold")), Color.BLUE);
                _ui.insertSpan("V4SP评分: ");
                _ui.insertText(String.valueOf(global.uma1.pushIntEpt("v4sp")), Color.PINK);
            }
            if ((Boolean)setManager.getVaule("isCalcV5"))
                {
                switch (global.uma1.pushType())
                {
                    case 0:
                    pages_global.v5recMain = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultSpeed")).get("main"));
                    pages_global.v5recFold = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultSpeed")).get("sub"));
                    pages_global.v5recSP = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultSpeed")).get("SP"));
                    break;
                    case 1:
                    pages_global.v5recMain = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultStamina")).get("main"));
                    pages_global.v5recFold = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultStamina")).get("sub"));
                    pages_global.v5recSP = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultStamina")).get("SP"));
                    break;
                    case 2:
                    pages_global.v5recMain = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultPower")).get("main"));
                    pages_global.v5recFold = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultPower")).get("sub"));
                    pages_global.v5recSP = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultPower")).get("SP"));
                    break;
                    case 3:
                    pages_global.v5recMain = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultGuts")).get("main"));
                    pages_global.v5recFold = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultGuts")).get("sub"));
                    pages_global.v5recSP = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultGuts")).get("SP"));
                    break;
                    case 4:
                    pages_global.v5recMain = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultWit")).get("main"));
                    pages_global.v5recFold = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultWit")).get("sub"));
                    pages_global.v5recSP = Integer.parseInt(Json.toMap((String)setManager.getVaule("v5DefaultWit")).get("SP"));
                    break;
                    default:
                    break;
                }
                _ui.insertNewLine();
                _ui.insertText("V5期望评分: { " + String.valueOf(pages_global.v5ex.v5main - pages_global.v5recMain) + "(主) " + String.valueOf(pages_global.v5ex.v5fold - pages_global.v5recFold) + "(副) " + String.valueOf(pages_global.v5ex.v5sp - pages_global.v5recSP) + "(SP) }");
                _ui.insertText("V5最高评分: { " + String.valueOf(pages_global.v5highest.v5main - pages_global.v5recMain) + "(主) " + String.valueOf(pages_global.v5highest.v5fold - pages_global.v5recFold) + "(副) " + String.valueOf(pages_global.v5highest.v5sp - pages_global.v5recSP) + "(SP) }");
                _ui.insertText("V5最低评分: { " + String.valueOf(pages_global.v5lowest.v5main - pages_global.v5recMain) + "(主) " + String.valueOf(pages_global.v5lowest.v5fold - pages_global.v5recFold) + "(副) " + String.valueOf(pages_global.v5lowest.v5sp - pages_global.v5recSP) + "(SP) }");
            }
            
            _ui.insertNewLine();
            _ui.insertOption("修改支援卡名称", (int)1);
            _ui.insertOption("修改属性", (int)2);
            _ui.insertOption("重新评估", (int)3);
            _ui.insertOption("保存预设", (int)4);
            _ui.insertOption("转到支援卡对比", (int)5);
            
            _ui.insertNewLine();
            _ui.insertOption("回到开始界面", (int)6);
            
            _ui.insertNewLine();
            _ui.insertOption("退出程序", (int)0);
            
            _ui.insertNewLine();
            _ui.insertInput();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected int handlePageInput(String input)
    {
        if (input.equals("1"))
            {
            _ui.display("请输入新的支援卡名称: ", false);
            String name = _ui.getUserInput();
            global.uma1.getName(name);
            return pages.RESULT;
        }
        else if (input.equals("2"))
            {
            return pages.EVALUATE;
        }
        else if (input.equals("3"))
            {
            return pages.CALCULATE_MAIN;
        }
        else if (input.equals("4"))
            {
            //保存
            write_umaCore.saveRes(global.uma1);
            return pages.CURRENT;
        }
        else if (input.equals("5"))
            {
            global.compareUma.add(global.uma1);
            return pages.COMPARE;
        }
        else if (input.equals("6"))
            {
            return pages.START;
        }
        else if (input.equals("0"))
            {
            return pages.EXIT;
        }
        else
            {
            return pages.CURRENT;
        }
    }
}
