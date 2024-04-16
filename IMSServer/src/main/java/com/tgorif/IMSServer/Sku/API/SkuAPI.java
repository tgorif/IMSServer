package com.tgorif.IMSServer.Sku.API;

import com.tgorif.IMSServer.Sku.Core.AutoExpirationDate;
import com.tgorif.IMSServer.Sku.Core.Sku;
import com.tgorif.IMSServer.Sku.Core.SkuData;
import com.tgorif.IMSServer.Sku.Core.SkuEntity;
import com.tgorif.IMSServer.Sku.Persistance.SkuManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
public class SkuAPI {

    SkuManager skuManager;
    Logger logger = LogManager.getLogger(SkuAPI.class);

    @Autowired
    public SkuAPI(SkuManager skuManager) {
        this.skuManager = skuManager;
    }


    //add sku

    // add entity
    @PostMapping("/quickPostEntity")
    public SkuAPIResponse quickAddEntity(@RequestBody Sku sku) {
        logger.log(Level.DEBUG,"request to add entity");
        if(sku== null) {
            // Handle the case where the id is null
            logger.log(Level.DEBUG, "The given id must not be null");
            return new SkuAPIResponse(SKUResponseCode.REQUEST_NULL);
        }
        else if(!skuManager.skuExists(sku.getBarcode())){
            logger.log(Level.DEBUG,String.format("%s entity not saved" , sku.getBarcode()));
            return new SkuAPIResponse(SKUResponseCode.SKU_NOT_FOUND);
        }
        else if(skuManager.getSkuData(sku.getBarcode()).getAutoExpirationDate()==AutoExpirationDate.MANUAL){
            logger.log(Level.ERROR, "EXPDATE required".formatted(sku.getBarcode()));
            return new SkuAPIResponse(SKUResponseCode.EXPDATE_REQUIRED);
        }
        else{
            skuManager.saveEntity(new SkuEntity(LocalDate.now().plusDays(skuManager.getSkuData(sku.getBarcode()).getAutoExpirationDate().getDays()),sku.getBarcode()));
            return new SkuAPIResponse(SKUResponseCode.ACCEPTED);
        }
    }

    @PostMapping("/postEntity")
    public SkuAPIResponse addEntity(@RequestBody SkuEntity entity) {
        logger.log(Level.DEBUG,"request to add entity");
        if(entity.getBarcode()== null) {
            // Handle the case where the id is null
            logger.log(Level.DEBUG, "The given id must not be null");
            return new SkuAPIResponse(SKUResponseCode.REQUEST_NULL);
        }
        else if(skuManager.skuExists(entity.getBarcode())){
            skuManager.saveEntity(entity);
            logger.log(Level.ERROR, "saved entity%s".formatted(entity.getBarcode()));
            return new SkuAPIResponse(SKUResponseCode.ACCEPTED);
        }
        else{
            logger.log(Level.DEBUG,String.format("%s entity not saved" , entity.getBarcode()));
            return new SkuAPIResponse(SKUResponseCode.SKU_NOT_FOUND);
        }
    }
    @PostMapping("/postSkuData")
    public SkuAPIResponse addSku(@RequestBody SkuData data) {
        logger.log(Level.DEBUG,"request to add SkuData");
        if(data==null) {
            // Handle the case where the id is null
            logger.log(Level.ERROR, "The given id must not be null");
            return new SkuAPIResponse(SKUResponseCode.REQUEST_NULL);
        }
        else if(skuManager.skuExists(data.getBarcode())){
            logger.log(Level.ERROR,"failed to save duplicate entity " + data.getBarcode());
            return new SkuAPIResponse(SKUResponseCode.SKU_DUPLICATE);
        }
        else{
            skuManager.saveSkuData(data);
            logger.log(Level.DEBUG,"data saved" + data.getBarcode());
            return new SkuAPIResponse(SKUResponseCode.ACCEPTED);
        }
    }
    @GetMapping("/getSkuStatus/{id}")
    public SkuAPIResponse getSkuInformation(@PathVariable String id) {
        logger.log(Level.DEBUG,"request to get Sku Information");
        if(id==null){
            return new SkuAPIResponse(SKUResponseCode.REQUEST_NULL);
        }
        if(!skuManager.skuExists(id)){
            return new SkuAPIResponse(SKUResponseCode.SKU_NOT_FOUND);
        }
        else{
            if(skuManager.getSkuData(id).getAutoExpirationDate()==AutoExpirationDate.MANUAL){
                return new SkuAPIResponse(SKUResponseCode.EXPDATE_REQUIRED);
            }
            else{
                return new SkuAPIResponse(SKUResponseCode.AUTOEXPDATE_FOUND);
            }
        }
    }
    @GetMapping("/getSkuData/{id}")
    public SkuData getSkuData(@PathVariable String id) {
        logger.log(Level.DEBUG,"request to get SkuData");
        if(id==null){
            return null;
        }
        else return skuManager.getSkuData(id);
    }
    @DeleteMapping("/deleteEntity/{id}")
    public SkuAPIResponse deleteEntity(@PathVariable String id) {
        logger.log(Level.DEBUG,"request to delete Entity");
        if(!skuManager.skuExists(id)){
            return new SkuAPIResponse(SKUResponseCode.SKU_NOT_FOUND);
        }
        else{
            skuManager.deleteEntity(id);
            return new SkuAPIResponse(SKUResponseCode.ACCEPTED);
        }
    }
    @GetMapping("/getSkuList")
    public Set<SkuData> getSkuList() {
        logger.log(Level.DEBUG,"request to get SkuList");
        return skuManager.getAllSkuData();
    }
    @GetMapping("/getEntityList/{id}")
    public Set<SkuEntity> getEntityList(@PathVariable String id) {
        logger.log(Level.DEBUG,"request to get EntityList");
        if(!skuManager.skuExists(id)){
            return null;
        }
        else{
            return skuManager.getEntitiesForSku(id);
        }
    }
}
