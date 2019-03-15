package com.hb.acadia.constant;

public enum Mode {

    CREATE("create"), DISPLAY_DETAIL("detail"), UPDATE("update");

    private String name;

    private Mode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
