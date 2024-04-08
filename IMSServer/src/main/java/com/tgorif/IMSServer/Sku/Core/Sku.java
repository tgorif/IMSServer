package com.tgorif.IMSServer.Sku.Core;

import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class Sku {
    public void setBarcode(@NonNull String barcode) {
        this.barcode = barcode;
    }

    @NonNull
    @MongoId
    private String barcode;

    public Sku(String barcode) {
        this.barcode = barcode;
    }

    public Sku() {}
    @NonNull
    public String getBarcode() {
        return barcode;
    }
}
