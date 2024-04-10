package com.tgorif.IMSServer.Sku.Core;

public enum AutoExpirationDate {
    MANUAL(0),
    WEEK(7),
    TWO_WEEKS(14),
    MONTH(30),
    Three_MONTHS(90),
    HALF_YEAR(180),
    YEAR(365);

    private final int days;

    AutoExpirationDate(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
