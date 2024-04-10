package com.tgorif.IMSServer.Sku.API;

public enum SKUResponseCode {
    REQUEST_NULL(401),
    EXPDATE_REQUIRED(402),
    UNKNOWN_ERROR(501),
    SKU_NOT_FOUND(499),
    SKU_DUPLICATE(498),
    ACCEPTED(200),
    AUTOEXPDATE_FOUND(201);

    private final int code;
    SKUResponseCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }






}
