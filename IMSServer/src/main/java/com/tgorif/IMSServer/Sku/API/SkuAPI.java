package com.tgorif.IMSServer.Sku.API;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.tgorif.IMSServer.Sku.Core.AutoExpirationDate;
import com.tgorif.IMSServer.Sku.Core.Sku;
import com.tgorif.IMSServer.Sku.Core.SkuData;
import com.tgorif.IMSServer.Sku.Core.SkuEntity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.time.LocalDate;

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
        logger.log(Level.DEBUG,"request to get SkuData");
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
}
