package com.tgorif.IMSServer.Sku.Core;

import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class SkuData {
    @MongoId
    @NonNull
    String barcode;
    @NonNull
    String name;
    String brand;
    AutoExpirationDate autoExpirationDate;

    public AutoExpirationDate getAutoExpirationDate() {
        return autoExpirationDate;
    }

    public void setAutoExpirationDate(AutoExpirationDate autoExpirationDate) {
        this.autoExpirationDate = autoExpirationDate;
    }


    public NutritionData getNutritionData() {
        return nutritionData;
    }

    public void setNutritionData(NutritionData nutritionData) {
        this.nutritionData = nutritionData;
    }

    NutritionData nutritionData;

    public SkuData(@NonNull String barcode, @NonNull String name,NutritionData nutritionData,AutoExpirationDate autoExpirationDate) {
        this.barcode = barcode;
        this.name = name;
        this.nutritionData = nutritionData;
        this.autoExpirationDate = autoExpirationDate;
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
