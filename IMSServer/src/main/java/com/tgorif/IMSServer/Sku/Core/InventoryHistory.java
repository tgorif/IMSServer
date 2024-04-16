package com.tgorif.IMSServer.Sku.Core;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

public class InventoryHistory {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String barcode;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private LocalDateTime date;
    private InventoryChangeCode operation;

    public InventoryHistory(String barcode, LocalDateTime date, InventoryChangeCode operation) {
        this.barcode = barcode;
        this.date = date;
        this.operation = operation;
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
