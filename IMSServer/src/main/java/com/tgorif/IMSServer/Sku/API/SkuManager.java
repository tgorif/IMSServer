package com.tgorif.IMSServer.Sku.API;

import com.tgorif.IMSServer.Sku.Core.Sku;
import com.tgorif.IMSServer.Sku.Core.SkuData;
import com.tgorif.IMSServer.Sku.Core.SkuEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SkuManager {
    // add SKU
    // return SKU
    Logger  log = LogManager.getLogger(SkuManager.class);
    @Autowired
    private SkuDataRepository skuDataRepository;
    @Autowired
    private SkuEntityRepository entityRepository;
    public SkuManager() {

    }

    public boolean skuExists(String id){
        log.error("SkuManager.skuExists " + id);
        return skuDataRepository.findById(id).isPresent();
    }
    public SkuData getSkuData(String id){
        if(!skuExists(id)){
            log.info(" '{}' SKU not found",id);
            return null;
        }
        return skuDataRepository.findById(id).get();
    }
    public SkuData saveSkuData(SkuData sku){
        if(sku==null) return null;
        return skuDataRepository.save(sku);
    }

    public void deleteSku(String id){
        if(getSkuData(id) == null){
            return;
        }
        skuDataRepository.delete(getSkuData(id));
    }
    public void deleteEntities(String id){
        if(!skuExists(id)){
            return;
        }
        for (SkuEntity skuEntity : getEntityForSku(id)){
            entityRepository.delete(skuEntity);
        }
    }

    public boolean saveEntity(SkuEntity entity){
        if(!skuExists(entity.getBarcode())){
            return false;
        }
        entityRepository.save(entity);
        return true;
    }

    public Set<SkuEntity> getEntityForSku(String barcode){
        if(!skuExists(barcode)){
            return new HashSet<>();
        }
        return entityRepository.findByBarcode(getSkuData(barcode).getBarcode());
    }

}
