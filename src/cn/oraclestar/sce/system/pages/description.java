package cn.oraclestar.sce.system.pages;

import cn.oraclestar.sce.system.UI.Color;
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

public class description extends cn.oraclestar.sce.system.router.router_core {
    @Override
    protected void buildPage()
    {
        _ui.insertTitle("(关于) 软件介绍");
        _ui.insertText("支援卡评价器（SupportCard Evaluator, SCE）是一款基于命令行界面（CLI）的游戏辅助工具，专为《赛马娘》玩家设计，旨在通过量化分析模型，为支援卡的强度提供客观、多维度的评估。本工具的核心价值在于将复杂的游戏机制简化为可计算的参数，从而为玩家的育成策略提供数据驱动的参考。");
        _ui.insertNewLine();
        _ui.insertText("核心功能:", Color.GREEN);
        _ui.insertText("多模型量化评估:", Color.YELLOW);
        _ui.insertText("本软件内嵌了多种独立的评估算法，这些模型分别计算支援卡的各项关键性能指标。评估指标包括但不限于：基础训练倍率、综合训练倍率、真实得意训练率、失效率等。这些模型的存在允许从不同的侧重点对支援卡进行综合评价。");
        _ui.insertText("交互式界面与可读性优化:", Color.YELLOW);
        _ui.insertText("尽管采用命令行接口，但本工具通过 fmt 库实现了彩色文本输出和结构化排版。这种设计旨在提高信息的层次感和用户的可读性，降低使用门槛。");
        
        _ui.insertNewLine();
        _ui.insertText("尽管SCE旨在提供严谨的评估，但其模型本身存在以下不足和预估不准确性，使用者应加以注意:", Color.RED);
        _ui.insertText("依赖静态假设:", Color.YELLOW);
        _ui.insertText("本工具的计算模型基于对游戏机制的静态假设，例如固定的事件触发概率等。这些参数可能因游戏版本更新而改变，但软件本身并未实现动态数据更新机制，因此其评估结果可能存在滞后性和不准确性。");
        _ui.insertText("缺乏情境化评估:", Color.YELLOW);
        _ui.insertText("本工具主要基于单一支援卡或支援卡组合的静态数值进行评估，未能充分考虑游戏内情境因素对支援卡强度的影响。例如，在特定育成剧本、马娘固有加成或特定比赛要求下，支援卡的相对强度会发生显著变化，而这部分复杂性难以在当前框架下完全量化。");
        
        _ui.insertNewLine();
        _ui.insertText("综上所述，支援卡评价器（SCE）作为一款量化分析工具，为玩家提供了有价值的参考，但其评估结果并非绝对真理。使用者应将其视为辅助决策的工具，而非唯一的强度判断标准，并结合实际游戏体验和版本更新进行综合考量。");
        
        _ui.insertNewLine();
        _ui.insertInput(true);
    }
    @Override
    protected int handlePageInput(String input)
    {
        return pages.ABOUT;
    }
}
