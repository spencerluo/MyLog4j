package com.spencer.api;

public abstract class AppenderBase implements Appender{
    private String name;
    private Layout layout;

    public AppenderBase(String name, Layout layout){
        this.name = name;
        this.layout = layout;
    }

    @Override
    public Layout getLayout() {
        return layout;
    }

    @Override
    public String getName() {
        return name;
    }
}
