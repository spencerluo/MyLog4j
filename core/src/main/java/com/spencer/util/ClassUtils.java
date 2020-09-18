package com.spencer.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassUtils {

    public static Set<Class<?>> getClassList(String packageName){
        String classPath = ClassLoader.getSystemResource("").getPath();
        packageName = packageName.replace(".", File.separator);
        String packagePath = classPath + packageName;
        File file = new File(packagePath);
        List<String> classList = new ArrayList<>();
        for (File f: file.listFiles()){
            getClassName(f, classList);
        }

        Set<Class<?>> classSet = new HashSet<>();
        for (String className : classList) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(className);
                classSet.add(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classSet;
    }

    private static void getClassName(File file, List<String> classList){
        if (file.isDirectory()){
            for (File f: file.listFiles()){
                getClassName(f, classList);
            }
        }else {
            String filePath = file.getAbsolutePath();
            if (filePath.contains("$")) {
                return;
            }

            String className = filePath.substring(filePath.lastIndexOf("classes") + 8);
            className = className.replace(File.separator, ".");
            className = className.substring(0, className.indexOf(".class"));
            classList.add(className);
        }
    }
}
