package com.spencer.plugins;

public class PluginType {
    private String name;
    private Class clazz;
    private String elementType;

    public PluginType(String name, Class clazz, String elementType) {
        this.name = name;
        this.clazz = clazz;
        this.elementType = elementType;
    }

    public String getName() {
        return name;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getElementType() {
        return elementType;
    }
}
