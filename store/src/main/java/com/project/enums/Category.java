package com.project.enums;

public enum Category {
    Shirt, Blouse, Sweater, Hoodie, Jacket, Coat, Tank_Top, Pants, Shorts, Skirt, Dress, Suit;

    public static Category fromDb(String dbValue) {
        return Category.valueOf(dbValue.replace(" ", "_"));
    }

    public String toDb() {
        return this.name().replace("_", " ");
    }
}
