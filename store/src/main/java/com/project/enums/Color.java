package com.project.enums;

public enum Color {
    Black, White, Grey, Red, Blue, Green, Yellow, Orange, Purple, Pink;

    public static Color fromDb(String dbValue) {
        return Color.valueOf(dbValue);
    }

    public String toDb() {
        return this.name();
    }
}
