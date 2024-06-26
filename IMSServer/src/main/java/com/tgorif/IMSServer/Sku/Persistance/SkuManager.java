package com.tgorif.IMSServer.Sku.Persistance;

import com.tgorif.IMSServer.Sku.Core.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class SkuManager {

    Logger  log = LogManager.getLogger(SkuManager.class);
    @Autowired
    private SkuDataRepository skuDataRepository;
    @Autowired
    private SkuEntityRepository entityRepository;
    @Autowired
    private InventoryHistoryRepository inventoryHistoryRepository;
    public SkuManager() {

    }

    public boolean skuExists(String id){
        if(id==null || id.isEmpty()) return false;
        return skuDataRepository.findById(id).isPresent();
    }
    public SkuData getSkuData(String id){
        Optional<SkuData> res= skuDataRepository.findById(id);
        if(res.isEmpty()) log.info(" '{}' SKU not found",id);
        return res.orElse(null);
    }
    public SkuData saveSkuData(SkuData sku){
        if(sku==null){
            log.debug("sku is null");
            return null;
        }
        return skuDataRepository.save(sku);
    }

    public void deleteSku(String id){
        if(!skuExists(id)){
            log.debug("sku not found");
            return;
        }
        skuDataRepository.deleteById(id);
    }
    public void deleteEntities(String id){
        for (SkuEntity skuEntity : getEntitiesForSku(id)){
            entityRepository.delete(skuEntity);
        }
    }
    public void deleteEntity(String barcode){
        if(!skuExists(barcode)){
            return;
        }
        //find entity with the oldest expiration date
        SkuEntity oldestEntity = null;
        for(SkuEntity entity :entityRepository.findByBarcode(barcode)){
            if(oldestEntity == null || entity.getExpiration().isBefore(oldestEntity.getExpiration())){
                oldestEntity = entity;
            }
        }
        if(oldestEntity!=null){
            entityRepository.delete(oldestEntity);
            inventoryHistoryRepository.save(new InventoryHistory(barcode,LocalDateTime.now(),InventoryChangeCode.DELETE));
        }
    }
    public boolean saveEntity(SkuEntity entity){
        if(!skuExists(entity.getBarcode())){
            return false;
        }
        entityRepository.save(entity);
        inventoryHistoryRepository.save(new InventoryHistory(entity.getBarcode(), LocalDateTime.now(), InventoryChangeCode.ADD));

        return true;
    }

    public Set<SkuEntity> getEntitiesForSku(String barcode){
        if(!skuExists(barcode)){
            return new HashSet<>();
        }
        return entityRepository.findByBarcode(getSkuData(barcode).getBarcode());
    }

    public Set<SkuData> getAllSkuData() {
        return new HashSet<>(skuDataRepository.findAll());
    }
}
