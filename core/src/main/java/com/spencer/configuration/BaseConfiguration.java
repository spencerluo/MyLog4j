package com.spencer.configuration;

import com.spencer.api.Appender;
import com.spencer.api.Configuration;
import com.spencer.api.LoggerConfig;
import com.spencer.exception.LoggerException;
import com.spencer.plugins.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
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

    public BaseConfiguration() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void start() {
        pluginManager.collectPlugin();
        setup();
        doConfig();
    }

    public void setup() {

    }

    private void doConfig() {
        createConfiguration(rootNode);
    }

    private void createConfiguration(ConfigNode configNode) {
//        if (configNode.getObject() == null) {
//            createPluginObject(configNode);
//        }
        for (ConfigNode childNode : configNode.getChildNodes()) {
            createConfiguration(childNode);
        }
        PluginType type = configNode.getPluginType();
        if (type != null) {
            configNode.setObject(createPluginObject(type, configNode));
        }
    }

    private Object createPluginObject(PluginType type, ConfigNode configNode) {
        List<ConfigNode> childNodes = configNode.getChildNodes();


        Class clazz = type.getClazz();
        Method method = getFactoryMethod(clazz);
        Object[] params = getMethodParameters(type, configNode, method);

        return null;
//        for (Method method : methods) {
//            if (method.isAnnotationPresent(PluginFactory.class)) {
//                Parameter[] parameters = method.getParameters();
//                Object[] objects = new Object[parameters.length];
//                for (int i = 0; i < parameters.length; i++) {
//                    Parameter parameter = parameters[i];
//                    if (parameter.isAnnotationPresent(PluginAttr.class)) {
//                        PluginAttr attrPlugin = parameter.getAnnotation(PluginAttr.class);
//                        String parameterName = attrPlugin.value();
//                        String value = configNode.getAttr(parameterName);
//                        objects[i] = value;
//                    } else if (parameter.isAnnotationPresent(PluginElement.class)) {
//                        PluginElement pluginElement = parameter.getAnnotation(PluginElement.class);
//                        String parameterName = pluginElement.value();
//
//                        for (ConfigNode node : childNodes) {
//                            String eleType = node.getPluginType().getElementType();
//                            if (eleType.equals(parameterName)) {
//                                createPluginObject(node);
//                                Object value = node.getObject();
//                                objects[i] = value;
//                            }
//                        }
//                    }
//                }
//                try {
//                    Object invoke = method.invoke(null, objects);
//                    configNode.setObject(invoke);
//                } catch (IllegalAccessException | InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
    }

    private Method getFactoryMethod(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(PluginFactory.class)) {
                return method;
            }
        }
        return null;
    }

    private Object[] getMethodParameters(PluginType type, ConfigNode configNode, Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Parameter[] parameters = method.getParameters();

        Object[] objects = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class paramType = parameterTypes[i];
            Parameter parameter = parameters[i];

            if (paramType.isArray()){
                List<Object> objectList = new ArrayList<>();
                List<ConfigNode> childNodes = configNode.getChildNodes();

                for (ConfigNode child : childNodes) {
                    PluginType childType = child.getPluginType();
                    if (paramType.isAssignableFrom(childType.getClazz())) {
                        Object childObject = child.getObject();
                        if (childObject == null) {
                            System.out.println("Null object returned for " + child.getName());
                        }else {
                            objectList.add(childObject);
                        }
                    }
                }
                objects[i] = objectList.toArray();

            }

            else if (parameter.isAnnotationPresent(PluginAttr.class) ) {
                PluginAttr pluginAttr = parameter.getAnnotation(PluginAttr.class);
                String name = pluginAttr.value();
                String value = configNode.getAttr(name);
                objects[i] = value;
            }

        }
        return null;
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

    public LoggerConfig getRoot() {
        return root;
    }


}
