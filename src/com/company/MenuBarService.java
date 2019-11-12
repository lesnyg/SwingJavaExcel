package com.company;


import java.io.File;
import java.util.HashMap;
import java.util.List;

public class MenuBarService {
    public static List<HashMap<String, String>> fileLoader(File file) {
        ExcelManager manager = ExcelManager.getInstance();
        List<HashMap<String, String>> list = null;
        try {
            list = manager.getListExcel(file);
        } catch (Exception e) {
            e.printStackTrace();
        };
        return list;
    }
}
