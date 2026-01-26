package cn.oraclestar.sce.system.modloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class modlist {
    public static Map<String,Map<String,Map<String,String>>> modlist_List = new HashMap<>();
    public static Map<String,cn.oraclestar.sce.system.modloader.modloader_main> modlist_core = new HashMap<String,cn.oraclestar.sce.system.modloader.modloader_main>();
    public void loadmod(){
        File mod_folder = new File("plugins");
        System.err.println("[ModLoader] 正在扫描目录: " + mod_folder.getAbsolutePath());
        if (!mod_folder.exists()) {
            mod_folder.mkdirs();
        }
        File[] files = mod_folder.listFiles((dir, name) -> name.endsWith(".jar")); 
        if(files != null){
            for (File file :files){
                try{
                    URLClassLoader loader = new URLClassLoader(
                        new URL[]{file.toURI().toURL()},
                        this.getClass().getClassLoader() 
                    );
                    try(JarFile jarFile = new JarFile(file)){
                        Enumeration<JarEntry> entries = jarFile.entries();
                        
                        while(entries.hasMoreElements()){
                            JarEntry entry = entries.nextElement();
                            String entryName = entry.getName();
                            if (!entryName.endsWith(".class")) continue;
                            if (entryName.contains("module-info")) continue;
                            if (entryName.startsWith("META-INF/")) continue; // 忽略多版本类和元数据
                            
                            // 忽略常见的 UI 库和系统库包名
                            if (entryName.startsWith("com/formdev/") || // FlatLaf 
                            entryName.startsWith("org/apache/") || 
                            entryName.startsWith("com/google/") ||
                            entryName.startsWith("javax/")) {
                                continue;
                            }
                            if(entryName.endsWith(".class") && !entryName.contains("module-info")){
                                String className = entryName.replace("/",".").substring(0,entryName.length()-6);
                                Class<?> clazz = loader.loadClass(className);
                                if(cn.oraclestar.sce.system.modloader.modloader_main.class.isAssignableFrom(clazz) && !clazz.isInterface()){
                                    System.err.println("[ModLoader] 成功匹配到插件主类: " + className);
                                    cn.oraclestar.sce.system.modloader.modloader_main plugin = (cn.oraclestar.sce.system.modloader.modloader_main)clazz.getDeclaredConstructor().newInstance();
                                    plugin.onEnable();                                  
                                    target info = plugin.getClass().getAnnotation(target.class);
                                    Map<String,String> temp  = new HashMap<>();
                                    Map<String,Map<String,String>> temp2 = new HashMap<>();
                                    String name = "未知扩展",author="未知作者",version="未知版本";
                                    if(info !=null){
                                        if(!info.name().isEmpty()){
                                            temp.put(info.version(),info.Description());
                                            temp2.put(info.author(),temp);
                                            modlist_List.put(info.name(),temp2);
                                        }
                                    }
                                    modlist_core.put(info.name(),plugin);
                                    System.err.println("[ModLoader] Mod信息 "+info.name()+" "+info.author()+" "+info.version());
                                    // if(info != null)
                                    // {
                                    //     temp.put(info.author(),info.version());
                                    //     modlist_List.put(info.name(),temp);
                                    // }
                                    // else if (info.name())
                                    // else if (info == null)
                                    // {
                                    //     temp.put("未知作者","未知版本");
                                    //     modlist_List.put("未知名称",temp);
                                    // }
                                    // modlist_List.put(plugin.pluginName,plugin.version);
                                }
                            }
                        }
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    };
    public void _loadmod(){
        File mod_folder = new File("plugins/preload");
        System.err.println("[ModLoader] 正在扫描目录: " + mod_folder.getAbsolutePath());
        if (!mod_folder.exists()) {
            mod_folder.mkdirs();
        }
        File[] files = mod_folder.listFiles((dir, name) -> name.endsWith(".jar")); 
        if(files != null){
            for (File file :files){
                try{
                    URLClassLoader loader = new URLClassLoader(
                        new URL[]{file.toURI().toURL()},
                        this.getClass().getClassLoader() 
                    );
                    try(JarFile jarFile = new JarFile(file)){
                        Enumeration<JarEntry> entries = jarFile.entries();
                        
                        while(entries.hasMoreElements()){
                            JarEntry entry = entries.nextElement();
                            String entryName = entry.getName();
                            if (!entryName.endsWith(".class")) continue;
                            if (entryName.contains("module-info")) continue;
                            if (entryName.startsWith("META-INF/")) continue; // 忽略多版本类和元数据
                            
                            // 忽略常见的 UI 库和系统库包名
                            if (entryName.startsWith("com/formdev/") || // FlatLaf 
                            entryName.startsWith("org/apache/") || 
                            entryName.startsWith("com/google/") ||
                            entryName.startsWith("javax/")) {
                                continue;
                            }
                            if(entryName.endsWith(".class") && !entryName.contains("module-info")){
                                String className = entryName.replace("/",".").substring(0,entryName.length()-6);
                                Class<?> clazz = loader.loadClass(className);
                                if(cn.oraclestar.sce.system.modloader.modloader_main.class.isAssignableFrom(clazz) && !clazz.isInterface()){
                                    System.err.println("[ModLoader] 成功匹配到插件主类: " + className);
                                    cn.oraclestar.sce.system.modloader.modloader_main plugin = (cn.oraclestar.sce.system.modloader.modloader_main)clazz.getDeclaredConstructor().newInstance();
                                    plugin.onEnable();                                  
                                    target info = plugin.getClass().getAnnotation(target.class);
                                    Map<String,String> temp  = new HashMap<>();
                                    Map<String,Map<String,String>> temp2 = new HashMap<>();
                                    String name = "未知扩展",author="未知作者",version="未知版本";
                                    if(info !=null){
                                        if(!info.name().isEmpty()){
                                            temp.put(info.version(),info.Description());
                                            temp2.put(info.author(),temp);
                                            modlist_List.put(info.name(),temp2);
                                        }
                                    }
                                    modlist_core.put(info.name(),plugin);
                                    System.err.println("[ModLoader] Mod信息 "+info.name()+" "+info.author()+" "+info.version());
                                    // if(info != null)
                                    // {
                                    //     temp.put(info.author(),info.version());
                                    //     modlist_List.put(info.name(),temp);
                                    // }
                                    // else if (info.name())
                                    // else if (info == null)
                                    // {
                                    //     temp.put("未知作者","未知版本");
                                    //     modlist_List.put("未知名称",temp);
                                    // }
                                    // modlist_List.put(plugin.pluginName,plugin.version);
                                }
                            }
                        }
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    };


}
