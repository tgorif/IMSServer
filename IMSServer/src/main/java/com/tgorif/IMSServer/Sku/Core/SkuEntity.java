package com.tgorif.IMSServer.Sku.Core;

import com.mongodb.lang.NonNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class SkuEntity {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
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
