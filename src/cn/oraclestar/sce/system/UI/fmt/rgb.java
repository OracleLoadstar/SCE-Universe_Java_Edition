package cn.oraclestar.sce.system.UI.fmt;

/*
 ################################################
 #                                              #
 #        Another:xingguangcuican6666           #
 #        Date:2025-11-24                       #
 #        Copyright 2025-2026 OracleLoadStar    #
 #                                              #
 ################################################
 */

public class rgb {
    public int R=0;
    public int G=0;
    public int B=0;
    public rgb(int R,int G, int B){
        this.R = R;
        this.G = G;
        this.B = B;
        // this.A = A;
    };
    @Override
    public String toString(){
        return "\033[38;2;"+String.valueOf(R)+";"+String.valueOf(G)+";"+String.valueOf(B)+"m";   
    }
}
