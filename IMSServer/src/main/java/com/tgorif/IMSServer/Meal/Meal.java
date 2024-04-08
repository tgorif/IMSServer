package com.tgorif.IMSServer.Meal;

import com.mongodb.lang.NonNull;
import com.tgorif.IMSServer.Sku.Core.NutritionData;
import com.tgorif.IMSServer.Sku.Core.SkuData;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Map;

public class Meal {
    @MongoId
    @NonNull
    String name;
    @NonNull
    Map<SkuData,Integer> ingredients;
    @NonNull
    NutritionData  nutritionData;

    public Meal(@NonNull String name, @NonNull Map<SkuData, Integer> ingredients, @NonNull NutritionData nutritionData) {
        this.name = name;
        this.ingredients = ingredients;
        this.nutritionData = nutritionData;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Map<SkuData, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(@NonNull Map<SkuData, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    public NutritionData getNutritionData() {
        return nutritionData;
    }

    public void setNutritionData(@NonNull NutritionData nutritionData) {
        this.nutritionData = nutritionData;
    }
}
