# SCE-Universe_Java_Edition

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

    @Override
    public void onDisable() {
        
    }

    @Override
    public void onEnable() {
    }
}
~~~


