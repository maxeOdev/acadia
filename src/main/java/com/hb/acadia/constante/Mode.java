package com.hb.acadia.constante;

public enum Mode {

    DISPLAY_DETAIL("detail"), UPDATE("update");

    private String name;

    private Mode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
