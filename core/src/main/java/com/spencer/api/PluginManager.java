package com.spencer.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PluginManager {
    private final String classPath = ClassLoader.getSystemResource("").getPath();

    public static void collectPlugins() {
        String packageName = "com.spencer";


    }

    public static void main(String[] args) throws ClassNotFoundException {
        String packageName = "com/spencer";
        PluginManager manager = new PluginManager();
        List<String> classNames = new ArrayList<>();
        manager.getClassName("", packageName, classNames);
        System.out.println(classNames);

    }

    public void getClassName(String fileName, String packageName, List<String> classNames){
        String filePath = classPath + packageName + "/" + fileName;
        File file = new File(filePath);
        if (file.isDirectory()){
            if (!fileName.isEmpty()){
                packageName = packageName+"/"+fileName;
            }
            for (File f: file.listFiles()){
                getClassName(f.getName(), packageName, classNames);
            }
        }else {
            String className = packageName + "/" + fileName;
            className = className.replace("/", ".");
            className = className.substring(0, className.indexOf(".class"));
            classNames.add(className);
        }
    }
}
