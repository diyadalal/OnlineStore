package com.project.enums;

public enum Size {
    XXS, XS, S, M, L, XL, XXL

    //convert string to enum
    public static Size fromDb(String dbValue) {
        return Size.valueOf(dbValue.replace(" ", "_"));
    }

    //convert enum to string
    public String toDb() {
        return this.name().replace("_", " ");
    }
}
