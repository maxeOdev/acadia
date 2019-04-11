package com.hb.constant;

public enum Mode {

    CREATE("create"), DISPLAY_DETAIL("detail"), UPDATE("update"), DISPLAY_ALL_USERS("display-all"),
    DISPLAY_SEARCH_RESULT("display-research-result");

    private String name;

    private Mode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
