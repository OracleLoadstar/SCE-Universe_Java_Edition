# SCE-Universe_Java_Edition

中文 | [English](https://github.com/OracleLoadstar/SCE-Universe_Java_Edition/blob/main/README_EN.md)

本分支是`SCE-Universe`项目的`Java`实现喵。用于测试最新的版本特性和未完成/未计划的更新喵。

**注意：本分支不代表SCE主分支正式版本的更新，本分支主要维护者为组织成员，不代表OLS官方发布更新**

## SCE-Universe_Java_Edition特性

### 用户篇

### 1.1 Modloader 

在本分支中，我们设计并添加了Modloader喵，目前，你可以在启动时添加`-mod`参数启用Modloader喵。你可以把mod文件放在`plugins`文件夹下，注意mod类型，如果是前置mod，请放在`plugins/preload`下喵。

**目前可能会出现mod冲突的问题，例如ID冲突，TUI判断冲突等等**

**未来的版本更新将以mod的形式发布，意味着不会再有架构更新**

### 2.1 日志系统更新

日志将通过std标准错误流输出，在使用时务必记得在末尾加上`2>log.log`喵。如果不加的话还是很影响TUI的使用体验的喵

### 开发者篇

### 1.1 Modloader

现在，你可以制作mod来扩展SCE的功能了喵，目前你可以直接把SCE当作项目依赖导入了喵。你需要在主类中继承`cn.oraclestar.sce.system.modloader.modloader_main`类，并实现`innerHandle`,`innerToPage`,`onDisable`,`onEnable`方法喵，同时，你还需要在主类上加上注解`target`，来注明`modid`，`作者`，`版本`和`描述`喵。

示例主类：
~~~java
package cn.oraclestar.sce.upgrade.a26_01; //你的mod类所在包名

import cn.oraclestar.sce.system.modloader.target;

@target(name = "SCE_JAVA_UPGRADE_26_01", author = "xingguangcuican", version = "26.01", Description = "SCE JAVA Offcial Upgrade on 26.01")
public class App implements cn.oraclestar.sce.system.modloader.modloader_main {
    // 在指定页面插入额外选项判断，arg0是当前pageid，arg1是用户输入，若不需要则保持return pages.CURRENT;，不清楚pageid有那些可以去cn.oraclestar.sce.system.router下找到所有内置页
    @Override
    public int innerHandle(String arg0, int arg1) {
        return pages.CURRENT;
    }
    // 在指定TUI界面渲染额外内容arg0是ui类的实例化对象，arg1是当前pageid，你可以通过pageid判断当前界面并调用ui实例化对象中的方法插入需额外渲染的内容
    @Override
    public void innerToPage(UI_core arg0, int arg1) {
        
    }
    // 在软件关闭时执行（一般没用，没有几个用户会安全关闭本软件）
    @Override
    public void onDisable() {
        return;
    }
    //在软件启动时执行，一般用来添加设置，注册页面等等
    @Override
    public void onEnable() {
        // ...
        return;
    }
}
~~~

### 1.1.2 常用的客制化接口

常用的客制化接口：

`setManager_core`：
- `setManager_core.changeVaule(String,Object)`：修改设置值，String为设置键，Object为修改后的值（自动判断）
- `setManager_core.getCore()`：返回setManager的唯一实例。
- `setManager_core.getVaule(String)`：获取设置值，String为设置键，返回T，需要强制转换为对应的类型。
- `setManager_core.toggleSetting(String)`：修改类型为Boolen的设置，使其值为`!value`，即true改为false，false改为true。
- `setManager_core.addSet(String,Object)`：String为键，Object为设置值，目前只支持String，Int，Boolen三种类型，带有自动判断

`router_core`：
- `router_core.addPage(Int,pageheader)`：添加页面，Int为页面id（建议为页面名称的hash值），pageheader为继承了pageheader类的页面类实例（虽然写新界面需要继承的是router_core，这是为了保持最大兼容性）。

`_ui`：太多了，不赘述了，基本看方法名就知道用来干嘛的了。关于TUI的其他方面，例如颜色可以用`rgb`类，调用`.toString()`即可转String，还有`fmt`类，仿C++的库，但目前功能不完善，还有`indicators`类，也是仿C++库，用于显示进度条，这些仿C++库按照C++那边的文档用就可以了，基本差不太多。

### 1.1.3 配置新页面

页面可以覆写，但也只可以覆写内置页面和位于`preload`的前置mod页面喵，同级mod不可以覆写喵，例如前置mod不可以覆写其他前置mod，非前置mod不可覆写非前置mod喵。

覆写逻辑我们做得非常简单，你只需要知道目标界面的pageid，直接用`addPage`重新注册为修改后的界面就好了喵。

编写新的页面非常简单喵，你只需要继承`router_core`类，重写`buildPage`和`handlePageInput`方法就好了喵

示例：
~~~java
package cn.oraclestar.sce.upgrade.a26_01.system.pages;

import cn.oraclestar.sce.system.UI.Color;
import cn.oraclestar.sce.system.router.pages;
import cn.oraclestar.sce.system.router.router_core;
import cn.oraclestar.sce.system.setManager.setManager_core;
import cn.oraclestar.sce.upgrade.a26_01.system.pages_;

public class online_after extends router_core {
    @Override
    protected void buildPage(){
        // 写TUI渲染逻辑，就像写HTML一样
        try{
            _ui.insertTitle("(开始) 联网搜索");
            _ui.insertSpan("APIKEY状态：",Color.DEFAULT);
            if(((String)setManager_core.getVaule("official_26_01:apiKey")).equals(" ")){
                _ui.insertSpan("未设置",Color.RED);
            }
            else {
                _ui.insertSpan("已设置",Color.GREEN);
            }
            _ui.insertNewLine();
                        _ui.insertSpan("同步状态：",Color.DEFAULT);
            if(!((Boolean)setManager_core.getVaule("official_26_01:is_sync"))){
                _ui.insertSpan("未同步",Color.RED);
            }
            else {
                _ui.insertSpan("已同步",Color.GREEN);
            }
            _ui.insertNewLine();
            _ui.insertNewLine();
            _ui.insertOption("选取支援卡",1);
            _ui.insertOption("设置/修改 APIKEY",2);
            _ui.insertOption("同步数据库",3);
            _ui.insertNewLine();
            _ui.insertOption("退出",0);
            _ui.insertNewLine();
            _ui.insertInput();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected int handlePageInput(String Input){
        // 写交互逻辑
        try{
            if(Input.equals("2")){
                setManager_core.changeVaule("official_26_01:apiKey",displayEditInfo("APIKEY",setManager_core.getVaule("official_26_01:apiKey")));
            } else if(Input.equals("0")){
                return pages.START;
            } else if(Input.equals("3")){
                setManager_core.toggleSetting("official_26_01:is_sync");
                return pages.ONLINE;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return pages.CURRENT;
    }
}
~~~

### 1.2.1 关于其他库引用

你当然可以引用其他库，但是请打包成“胖包”喵，也不要把SCE本体给打包了喵，因为每个mod之间存在隔离机制，所以不需要担心类名冲突，大胆用就好了喵，当然，如果你需要和其他mod通信，请自行解决listener的问题喵，我们暂时没有添加listener机制喵，或者，你可以使用设置来处理通讯问题喵。

### 1.2.2 关于编译

SCE是在JDK 17下编译的，mod的JDK版本不应低于JDK17，SCE的编译也很简单喵，为了防止mod和主程序类名冲突，我们**没有**引入任何第三方库，也**没有**使用任何构建系统，你可以执行`./build.sh`编译，也可以自行用自己的方式编译该原生java项目喵，当然，关于mod的编译就看你个人喜好了喵。

### 法律相关

本项目采用GNU GPL 3.0协议喵。