package cn.oraclestar.sce.App;

import cn.oraclestar.sce.system.global;
import cn.oraclestar.sce.system.modloader.modlist;
import cn.oraclestar.sce.system.pages.errorloader;
import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;
import cn.oraclestar.sce.system.setManager.setManager_core;

/*
################################################
#                                              #
#        Another:Lat-SKY                       #
#                xingguangcuican               #
#        Date:2025-11-24                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/


public class App {
    public static cn.oraclestar.sce.system.router.router_core router = new router_core();
    public static void main(String[] args) throws Exception {
        modlist mod = new modlist();
        
        global.getcore();
       
        // 注册
        register();
        // write_umaCore.saveRes(global.uma1);
        // read_umaCore.readRes(global.uma1);
        // Map<String,String> temp = new HashMap<String,String>();
        // temp.put("a","b");
        // temp.put("c","d");
        // System.out.println(json.toJson(temp));
        // System.exit(0);
        if(args.length > 0)
        {
            if(args[0].equals("-mod")){
                cn.oraclestar.sce.system.setManager.setManager_core.addSet("mod",true);
                mod._loadmod();
                mod.loadmod();
            } 
            else if(args[0].equals("-h")||args[0].equals("--help")){
                System.out.printf("参数：\n");
                System.out.printf("     -mod    启用插件功能\n");
                System.exit(1);
            }
            else {
                cn.oraclestar.sce.system.setManager.setManager_core.addSet("mod",false);
            }
        }else {
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("mod",false);
        }
        //设置首页
        router.setInitialPage(cn.oraclestar.sce.system.router.pages.WELCOME);
        System.err.printf("[Main]：加载设置\n");
        if(!setManager_core.LoadSettings()){
            router_core.addPage(pages.ERRORLOADER,new errorloader());
            router.setInitialPage(pages.ERRORLOADER);
        }
        System.err.println("[Main] 准备启动路由...");
        router.start();
        // System.out.println("[Main] 路由已关闭。");

    };

    private static void register(){
        try{
            // 添加设置
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("homeTitle","SCE-UNIVERSE");
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isShowFriendshipRate",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isShowAllFriendshipRate",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isShowNonSpecialtyRate",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isShowAllNonSpecialtyRate",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isShowNonSpecialtySpRate",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isShowTrueSpecialtyRate",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isShowSpecialtySpRate",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isCalcV2",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isCalcV3",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isCalcV4",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isCalcV5",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isV5rectify",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("defaultLoopV5",1);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isAutoSaveCardData",true);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("cardDataSavePath","");
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("isCalcDI",false);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("port",8080);
            cn.oraclestar.sce.system.setManager.setManager_core.addSet("settingDataSavePath","");
            setManager_core.addSet("v5DefaultSpeed","{\"main\":\"0\",\"sub\":\"0\",\"SP\":\"0\"}");
            setManager_core.addSet("v5DefaultPower","{\"main\":\"0\",\"sub\":\"0\",\"SP\":\"0\"}");
            setManager_core.addSet("v5DefaultStamina","{\"main\":\"0\",\"sub\":\"0\",\"SP\":\"0\"}");
            setManager_core.addSet("v5DefaultGuts","{\"main\":\"0\",\"sub\":\"0\",\"SP\":\"0\"}");
            setManager_core.addSet("v5DefaultWit","{\"main\":\"0\",\"sub\":\"0\",\"SP\":\"0\"}");
            setManager_core.addSet("isV5Speedrectify",false);
            setManager_core.addSet("isV5Staminarectify",false);
            setManager_core.addSet("isV5Powerrectify",false);
            setManager_core.addSet("isV5Gutsrectify",false);
            setManager_core.addSet("isV5Witrectify",false);
            // 注册页面
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.ABOUT,new cn.oraclestar.sce.system.pages.about());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.MAIN,new cn.oraclestar.sce.system.pages.main());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.CHANGELOG,new cn.oraclestar.sce.system.pages.changelog());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.WELCOME,new cn.oraclestar.sce.system.pages.welcome());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.CONTACT,new cn.oraclestar.sce.system.pages.contact());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.CONTRIBUTION,new cn.oraclestar.sce.system.pages.contribution());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.DEPENDENCY,new cn.oraclestar.sce.system.pages.dependence());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.DESCRIPTION,new cn.oraclestar.sce.system.pages.description());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.LICENSE,new cn.oraclestar.sce.system.pages.license());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.SOURCE,new cn.oraclestar.sce.system.pages.source());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.START,new cn.oraclestar.sce.system.pages.start());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.SETTING,new cn.oraclestar.sce.system.pages.settings());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.DISPLAY,new cn.oraclestar.sce.system.pages.display());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.CALCULATE,new cn.oraclestar.sce.system.pages.calculate());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.FILE,new cn.oraclestar.sce.system.pages.file());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.PREVIEW,new cn.oraclestar.sce.system.pages.preview());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.SYSTEM,new cn.oraclestar.sce.system.pages.system());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.DEVELOPER,new cn.oraclestar.sce.system.pages.develop());
            cn.oraclestar.sce.system.router.router_core.addPage(cn.oraclestar.sce.system.router.pages.EVALUATE,new cn.oraclestar.sce.system.pages.evaluate());
            router_core.addPage(pages.ONLINE,new cn.oraclestar.sce.system.pages.online());
            router_core.addPage(pages.COMPARE,new cn.oraclestar.sce.system.pages.compare());
            router_core.addPage(pages.OPEN,new cn.oraclestar.sce.system.pages.open());
            router_core.addPage(pages.CALCULATE_MAIN,new cn.oraclestar.sce.system.pages.calculate_main());
            router_core.addPage(pages.CALCULATE_V5,new cn.oraclestar.sce.system.pages.calculate_v5());
            router_core.addPage(pages.RESULT,new cn.oraclestar.sce.system.pages.reselt());
            router_core.addPage(pages.V5RECTIFY,new cn.oraclestar.sce.system.pages.v5rectify());
            router_core.addPage(pages.MODLIST,new cn.oraclestar.sce.system.pages.modlist());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
