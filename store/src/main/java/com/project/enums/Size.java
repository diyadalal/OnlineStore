package com.project.enums;

public enum Size {
    XXS, XS, S, M, L, XL, XXL;

    public static Size fromDb(String dbValue) {
        return Size.valueOf(dbValue);
    }

    public String toDb() {
        return this.name();
    }
}
