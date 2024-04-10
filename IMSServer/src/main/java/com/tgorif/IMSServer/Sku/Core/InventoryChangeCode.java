package com.tgorif.IMSServer.Sku.Core;

public enum InventoryChangeCode {
    ADD("A"),
    DELETE("D");
    private String code;
    InventoryChangeCode(String code) {
        this.code = code;
    }
}
