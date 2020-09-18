package com.spencer.configuration;

import com.spencer.api.Appender;
import com.spencer.api.Configuration;
import com.spencer.api.LoggerConfig;
import com.spencer.plugins.PluginAttr;
import com.spencer.plugins.PluginFactory;
import com.spencer.plugins.PluginManager;
import com.spencer.plugins.PluginType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseConfiguration implements Configuration {
    private Map<String, LoggerConfig> loggerConfigMap = new ConcurrentHashMap<>();
    private Map<String, Appender> appenderMap = new ConcurrentHashMap<>();
    private LoggerConfig root = new LoggerConfig("root");
    protected PluginManager pluginManager = new PluginManager();
    protected ConfigNode rootNode = new ConfigNode();
    private String name;

    public BaseConfiguration(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void start(){
        pluginManager.collectPlugin();
        setup();
        doConfig();
    }

    public void setup(){

    }

    private void doConfig(){
        createConfiguration(rootNode);
    }

    private void createConfiguration(ConfigNode configNode) {
        if (configNode.hasPluginType()){
            createPluginObject(configNode);
        }
        for (ConfigNode childNode : configNode.getChildNodes()) {
            createConfiguration(childNode);
        }
    }

    private void createPluginObject(ConfigNode configNode) {
        PluginType pluginType = configNode.getPluginType();
        String elementType = pluginType.getElementType();
        if (elementType == null){
            return;
        }


        Class clazz = pluginType.getClazz();
        if (elementType.equals("appender")){
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(PluginFactory.class)) {
                    Parameter[] parameters = method.getParameters();
                    Object[] objects = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        if (parameters[i].isAnnotationPresent(PluginAttr.class)) {
                            PluginAttr attrPlugin = parameters[i].getAnnotation(PluginAttr.class);
                            String name = attrPlugin.value();
                            String value = configNode.getAttr(name);
                            objects[i] = value;
                        }
                    }
                    try {
                        Object invoke = method.invoke(null, objects);
                        configNode.setObject(invoke);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    public LoggerConfig getLoggerConfig(String name) {
        LoggerConfig config = loggerConfigMap.get(name);
        if (config != null) {
            return config;
        }

        return root;
    }

    @Override
    public void addAppender(Appender appender) {
        appenderMap.put(appender.getName(), appender);
    }

    public LoggerConfig getRoot(){
        return root;
    }


}
