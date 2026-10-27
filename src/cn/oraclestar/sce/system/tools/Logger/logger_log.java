package cn.oraclestar.sce.system.tools.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cn.oraclestar.sce.system.global;
public class logger_log {
    public logger_log(String vaule) throws IOException{
        if(global.debug == true){
            System.err.println(vaule);
        }
        FileWriter writer = null;
        try{
            File file = new File("./logs/log.log");
            File dir = new File("./logs/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()){
                file.createNewFile();
            }
            writer = new FileWriter(file,true);
            writer.append(vaule);
            writer.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }finally{
            if(null != writer){writer.close();};
        }
    }
    
}
