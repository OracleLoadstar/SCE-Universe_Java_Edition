# SCE-Universe_Java_Edition

[中文](https://github.com/OracleLoadstar/SCE-Universe_Java_Edition/blob/main/README.md) | English

This branch is the Java implementation of the `SCE-Universe` project, meow. It’s for testing the latest features and unfinished / unplanned updates, meow.

**Note: This branch does not represent official updates of the main SCE branch. The primary maintainers are organization members, and it does not represent official OLS releases, meow.**

## SCE-Universe_Java_Edition Features

### For Users

### 1.1 Modloader

In this branch, we designed and added a Modloader, meow. Currently, you can enable the Modloader at startup with the `-mod` argument. You can place mod files in the `plugins` folder. Pay attention to the mod type: if it is a preload mod, put it under `plugins/preload`, meow.

**Mod conflicts may occur at the moment, such as ID conflicts and TUI decision conflicts, meow.**

**Future version updates will be released as mods, which means there will be no further architecture updates, meow.**

### 2.1 Logging System Update

Logs are output through the standard error stream, meow. When using it, be sure to append `2>log.log` at the end. If not, it will still significantly affect the TUI experience, meow.

### For Developers

### 1.1 Modloader

Now you can create mods to extend SCE, meow. Currently, you can directly add SCE as a project dependency. You need to inherit `cn.oraclestar.sce.system.modloader.modloader_main` in your main class and implement `innerHandle`, `innerToPage`, `onDisable`, and `onEnable`. Also, you need to add the `target` annotation on the main class to specify `modid`, `author`, `version`, and `description`, meow.

Example main class, meow:
~~~java
package cn.oraclestar.sce.upgrade.a26_01; // your mod package name

import cn.oraclestar.sce.system.modloader.target;

@target(name = "SCE_JAVA_UPGRADE_26_01", author = "xingguangcuican", version = "26.01", Description = "SCE JAVA Offcial Upgrade on 26.01")
public class App implements cn.oraclestar.sce.system.modloader.modloader_main {
    // Insert extra option handling for a specific page. arg0 is the current pageid, arg1 is user input.
    // If not needed, keep return pages.CURRENT; If you are not sure about pageid values, check cn.oraclestar.sce.system.router for all built-in pages, meow.
    @Override
    public int innerHandle(String arg0, int arg1) {
        return pages.CURRENT;
    }
    // Render extra content on a specific TUI screen. arg0 is the UI instance, arg1 is the current pageid.
    // You can check the pageid and call methods on the UI instance to insert additional content, meow.
    @Override
    public void innerToPage(UI_core arg0, int arg1) {
        
    }
    // Execute when the software closes (usually not useful; few users exit safely), meow.
    @Override
    public void onDisable() {
        return;
    }
    // Execute on startup, usually used to add settings, register pages, etc., meow.
    @Override
    public void onEnable() {
        // ...
        return;
    }
}
~~~

### 1.1.2 Common Customization APIs

Common customization APIs, meow:

`setManager_core`:
- `setManager_core.changeVaule(String,Object)`: change a setting value. String is the setting key, Object is the new value (auto-typed).
- `setManager_core.getCore()`: returns the singleton instance of setManager.
- `setManager_core.getVaule(String)`: get a setting value. String is the setting key, returns T and needs casting to the corresponding type.
- `setManager_core.toggleSetting(String)`: toggle a Boolean setting, making it `!value` (true to false, false to true).
- `setManager_core.addSet(String,Object)`: String is the key, Object is the setting value. Currently supports only String, Int, and Boolean types, with auto-typing.

`router_core`:
- `router_core.addPage(Int,pageheader)`: add a page. Int is the page id (recommended to use a hash of the page name). `pageheader` is an instance of a page class extending `pageheader` (while new pages should extend `router_core`, this is for maximum compatibility).

`_ui`: too many to list here, meow. Generally the method names are self-explanatory. For other TUI aspects, such as colors, you can use the `rgb` class and call `.toString()` to get a String. There is also the `fmt` class (a C++-like library, currently incomplete) and the `indicators` class (also C++-like) for displaying progress bars. These C++-like libraries can be used according to the C++ documentation and are mostly the same, meow.

### 1.1.3 Configure New Pages

Pages can be overridden, but only for built-in pages and preload mod pages located in `preload`, meow. Mods at the same level cannot override each other. For example, a preload mod cannot override another preload mod, and a non-preload mod cannot override a non-preload mod, meow.

The override logic is very simple: you just need to know the target pageid and re-register it with `addPage` as the modified page, meow.

Writing a new page is very simple: just extend `router_core` and override `buildPage` and `handlePageInput`, meow.

Example:
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
        // Write TUI rendering logic, like writing HTML, meow
        try{
            _ui.insertTitle("(Start) Online Search");
            _ui.insertSpan("APIKEY status: ",Color.DEFAULT);
            if(((String)setManager_core.getVaule("official_26_01:apiKey")).equals(" ")){
                _ui.insertSpan("Not set",Color.RED);
            }
            else {
                _ui.insertSpan("Set",Color.GREEN);
            }
            _ui.insertNewLine();
                        _ui.insertSpan("Sync status: ",Color.DEFAULT);
            if(!((Boolean)setManager_core.getVaule("official_26_01:is_sync"))){
                _ui.insertSpan("Not synced",Color.RED);
            }
            else {
                _ui.insertSpan("Synced",Color.GREEN);
            }
            _ui.insertNewLine();
            _ui.insertNewLine();
            _ui.insertOption("Select support card",1);
            _ui.insertOption("Set/Modify APIKEY",2);
            _ui.insertOption("Sync database",3);
            _ui.insertNewLine();
            _ui.insertOption("Exit",0);
            _ui.insertNewLine();
            _ui.insertInput();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected int handlePageInput(String Input){
        // Write interaction logic, meow
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

### 1.2.1 About Using Other Libraries

You can of course reference other libraries, but please package them as a “fat jar,” and do not package the SCE core itself, meow. Because there is an isolation mechanism between mods, you don’t need to worry about class name conflicts. Feel free to use them, meow. If you need to communicate with other mods, handle listener issues yourself. We currently have not added a listener mechanism, or you can use settings to handle communication, meow.

### 1.2.2 About Compilation

SCE is compiled with JDK 17, and a mod’s JDK version should not be lower than JDK 17, meow. Compiling SCE is also simple. To prevent class name conflicts between mods and the main program, we **do not** introduce any third-party libraries and **do not** use any build system. You can run `./build.sh` to compile, or compile this plain Java project in your own way. For mod compilation, it’s up to you, meow.

## Legal

This project uses the GNU GPL 3.0 license, meow.