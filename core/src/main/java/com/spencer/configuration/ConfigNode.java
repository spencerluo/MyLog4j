package com.spencer.configuration;

import com.spencer.plugins.PluginType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigNode {

    public ConfigNode(ConfigNode parent, String name, PluginType pluginType) {
        this.parent = parent;
        this.name = name;
        this.pluginType = pluginType;
    }

    private ConfigNode parent;
    private String name;
    private String value;
    private Map<String, String> attr = new HashMap<>();
    private List<ConfigNode> childNodes = new ArrayList<>();
    private PluginType pluginType;
    private Object object;

    public ConfigNode() {

    }

    public ConfigNode getParent() {
        return parent;
    }

    public void setParent(ConfigNode parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addChildNode(ConfigNode node) {
        childNodes.add(node);
    }

    public void addAttr(String key, String value) {
        attr.put(key, value);
    }

    public String getAttr(String key) {
        return attr.get(key);
    }

    public PluginType getPluginType() {
        return pluginType;
    }

    public boolean hasPluginType() {
        return pluginType != null;
    }

    public void setPluginType(PluginType pluginType) {
        this.pluginType = pluginType;
    }

    public List<ConfigNode> getChildNodes() {
        return childNodes;
    }

    public boolean hasChild() {
        return !childNodes.isEmpty();
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
