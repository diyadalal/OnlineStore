package com.project.enums;

public enum Color {
    Black, White, Grey, Red, Blue, Green, Yellow, Orange, Purple, Pink
    
   //convert string to enum
    public static Color fromDb(String dbValue) {
        return Color.valueOf(dbValue.replace(" ", "_"));
    }
    //convert enum to string
    public String toDb() {
        return this.name().replace("_", " ");
    }
}
