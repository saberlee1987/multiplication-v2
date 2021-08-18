package com.saber.gimificationv2.domains;

public enum BadgeType {

    BRONZE("Bronze"),
    SILVER("Silver"),
    GOLD("Gold"),
    FIRST_WON("First_time"),
    LUCK_NUMBER("Lucky_Number");

    private String description;

    BadgeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
