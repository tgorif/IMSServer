package com.tgorif.IMSServer.Sku.Core;

import java.util.Map;
import java.util.Set;

public class NutritionData {
    int grams;
    int carbs;
    int fats;
    int protein;
    int sodium;
    int fiber;
    int saturatedFat;
    int transFat;
    int sugars;

    static NutritionData combine(Map<NutritionData,Integer> map){
        NutritionData res = new NutritionData();
        int g=0;
       for(Map.Entry<NutritionData,Integer> entry : map.entrySet()){
           g+= entry.getValue();
           res.setGrams(res.getGrams()+entry.getValue());
           res.setCarbs(res.getCarbs()+entry.getKey().getCarbs()*entry.getValue());
           res.setFats(res.getFats()+entry.getKey().getFats()*entry.getValue());
           res.setProtein(res.getProtein()+entry.getKey().getProtein()*entry.getValue());
           res.setSodium(res.getSodium()+entry.getKey().getSodium()*entry.getValue());
           res.setFiber(res.getFiber()+entry.getKey().getFiber()*entry.getValue());
           res.setSaturatedFat(res.getSaturatedFat()+entry.getKey().getSaturatedFat()*entry.getValue());
           res.setTransFat(res.getTransFat()+entry.getKey().getTransFat()*entry.getValue());
       }
       res.setCarbs(res.getCarbs()/g*100);
       res.setFats(res.getFats()/g*100);
       res.setProtein(res.getProtein()/g*100);
       res.setSodium(res.getSodium()/g*100);
       res.setFiber(res.getFiber()/g*100);
       res.setSaturatedFat(res.getSaturatedFat()/g*100);
       res.setTransFat(res.getTransFat()/g*100);
       return res;
    }

    public NutritionData(){}

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCalories() {
        return fats*9+carbs*4+protein*4;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getFiber() {
        return fiber;
    }

    public void setFiber(int fiber) {
        this.fiber = fiber;
    }

    public int getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(int saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public int getTransFat() {
        return transFat;
    }

    public void setTransFat(int transFat) {
        this.transFat = transFat;
    }

    public int getSugars() {
        return sugars;
    }

    public void setSugars(int sugars) {
        this.sugars = sugars;
    }
}
