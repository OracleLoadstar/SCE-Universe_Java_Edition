package cn.oraclestar.sce.system.UI.indicators;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import cn.oraclestar.sce.system.UI.fmt.rgb;

public class ProgressBar {
    public ProgressBar(int BarWidth,String Start,String Fill,String Lead,String Remainder,String End,rgb ForegroundColor,boolean ShowPercentage,boolean ShowElapsedTime,boolean ShowRemainingTime){
        this.BarWidth = BarWidth;
        this.Start = Start;
        this.Fill = Fill;
        this.Lead = Lead;
        this.Remainder = Remainder;
        this.End = End;
        this.ForegroundColor = ForegroundColor;
        this.ShowPercentage = ShowPercentage;
        this.ShowElapsedTime = ShowElapsedTime;
        this.ShowRemainingTime = ShowRemainingTime;
        will_show = 0;
    }
    
    int BarWidth;
    String Start;
    String Fill;
    String Lead;
    String Remainder;
    String End;
    rgb ForegroundColor;
    boolean ShowPercentage;
    boolean ShowElapsedTime;
    boolean ShowRemainingTime;
    boolean isbegin = false;
    int will_show;
    long BeginTime_spa;
    long now_Time;

    public void set_progress(float progress) {
        if(isbegin == false){
            isbegin = true;
            BeginTime_spa = LocalDateTime.of(LocalDate.now(),LocalTime.now()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
        now_Time = LocalDateTime.of(LocalDate.now(),LocalTime.now()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        will_show = (int)((progress/100.0f) * BarWidth);
        String temp_color="\033[38;2;"+String.valueOf(ForegroundColor.R)+";"+String.valueOf(ForegroundColor.G)+";"+String.valueOf(ForegroundColor.B)+"m";
        System.out.print("\r");
        System.out.print(temp_color);
        System.out.print(Start);
        for (int i = 0; i < will_show-1; i++){
            System.out.print(Fill);
        }
        System.out.print(Lead);
        for (int i = 0; i < BarWidth - will_show;i++){
            System.out.print(" ");
        }
        System.out.print(End+" ");
        System.out.print(String.valueOf((int)progress));
        System.out.print("% [");
        long runtime = now_Time - BeginTime_spa;
        long ASeconds = runtime / 1000;
        long minutes = ASeconds / 60;
        long seconds = ASeconds % 60;

        System.out.print(String.valueOf((int)minutes)+"m:"+String.valueOf((int)seconds) + "s<"+String.valueOf((int)(((100-progress)/(progress/ASeconds))/60))+"m:"+String.valueOf((int)(((100-progress)/(progress/ASeconds)) % 60))+ "s]");
        if(progress == 100){
            isbegin = false;
        }
        System.out.print("\033[0m");
        System.out.print("       ");
    };
}
