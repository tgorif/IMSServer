package com.tgorif.IMSServer.Sku.Core;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.mongodb.lang.NonNull;

import java.time.LocalDate;

public class SkuEntity {
    private LocalDate expiration;
    @NonNull
    private String barcode;


    public SkuEntity(LocalDate expiration, String barcode) {
        this.expiration = expiration;
        this.barcode = barcode;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

}
