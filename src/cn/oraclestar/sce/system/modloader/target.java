package cn.oraclestar.sce.system.modloader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface target {
    String name();
    String author() default "未知";
    String version() default "1.0.0";
    String Description() default "None";
}
