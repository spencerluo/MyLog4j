package com.spencer.plugins;

import com.spencer.util.ClassUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PluginManager {
    private Map<String, PluginType> plugins = new ConcurrentHashMap<>();
    private final String LOG4J_PACKAGES = "com.spencer";

    public void collectPlugin(String packages) {
        Set<Class<?>> classes = ClassUtils.getClassList(packages);
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Plugin.class)) {
                Plugin p = clazz.getAnnotation(Plugin.class);
                plugins.put(p.name().toLowerCase(), new PluginType(p.name(), clazz, p.elementType()));
                Class<?>[] classArray = clazz.getClasses();
                for (Class<?> clz : classArray) {
                    if (clz.isAnnotationPresent(Plugin.class)) {
                        Plugin p1 = clz.getAnnotation(Plugin.class);
                        plugins.put(p1.name().toLowerCase(), new PluginType(p1.name(), clz, p1.elementType()));
                    }
                }
            }
        }
    }

    public void collectPlugin() {
        collectPlugin(LOG4J_PACKAGES);
    }

    public PluginType getPlugin(String name) {
        return plugins.get(name.toLowerCase());
    }

    public static void main(String[] args) {
        PluginManager manager = new PluginManager();
        manager.collectPlugin();
        System.out.println(manager.getPlugin("root"));
    }
}
