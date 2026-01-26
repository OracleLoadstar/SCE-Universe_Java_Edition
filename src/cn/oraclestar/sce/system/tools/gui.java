package cn.oraclestar.sce.system.tools;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/*
################################################
#                                              #
#        Another:xingguangcuican6666           #
#        Date:2025-11-24                       #
#        Copyright 2025-2026 OracleLoadStar    #
#                                              #
################################################
*/

public class gui {
    public static String selectorD(){
        JFileChooser filechooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filechooser.showOpenDialog(null);
        return filechooser.getSelectedFile().getAbsolutePath();
    };
    public static String selector(){
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(null);
        return fileChooser.getSelectedFile().getAbsolutePath();
    }
}
