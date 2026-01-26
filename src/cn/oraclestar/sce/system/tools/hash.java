package cn.oraclestar.sce.system.tools;

public class hash {
    public static int hashCode(String string){
        if(string.hashCode() <= 100){
            return string.hashCode()+100;
        } else {
            return string.hashCode();
        }
    }
}
