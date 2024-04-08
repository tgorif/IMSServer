package com.tgorif.IMSServer.Sku.Core;

import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;

public class SkuData {
    @MongoId
    @NonNull
    String barcode;
    @NonNull
    String name;
    String brand;

    public NutritionData getNutritionData() {
        return nutritionData;
    }

    public void setNutritionData(NutritionData nutritionData) {
        this.nutritionData = nutritionData;
    }

    NutritionData nutritionData;

    public SkuData(@NonNull String barcode, @NonNull String name,NutritionData nutritionData) {
        this.barcode = barcode;
        this.name = name;
        this.nutritionData=nutritionData;
    }

    @NonNull
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(@NonNull String barcode) {
        this.barcode = barcode;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
