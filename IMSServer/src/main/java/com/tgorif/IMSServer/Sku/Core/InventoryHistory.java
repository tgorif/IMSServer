package com.tgorif.IMSServer.Sku.Core;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class InventoryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String barcode;
    private LocalDate date;
    private InventoryChangeCode operation;

    public InventoryHistory(String barcode, LocalDate date, InventoryChangeCode operation) {
        this.barcode = barcode;
        this.date = date;
        this.operation = operation;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public InventoryChangeCode getOperation() {
        return operation;
    }

    public void setOperation(InventoryChangeCode operation) {
        this.operation = operation;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
