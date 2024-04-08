package com.tgorif.IMSServer.Sku.API;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.tgorif.IMSServer.Sku.Core.SkuEntity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;

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

    @PostMapping("/entity")
    public int addEntity(@RequestBody SkuEntity entity) {
        logger.log(Level.DEBUG,"request to add entity");
        HttpStatus
        logger.log(Level.DEBUG,entity.getBarcode() + entity.getExpiration());
        if(entity.getBarcode()== null) {
            // Handle the case where the id is null
            logger.log(Level.ERROR, "The given id must not be null");
            return HttpURLConnection.HTTP_BAD_REQUEST;
        }
        else if(skuManager.skuExists(entity.getBarcode())){
            skuManager.saveEntity(entity);
            logger.log(Level.ERROR,"saved entity" + entity.getBarcode());
            return HttpURLConnection.HTTP_ACCEPTED;
        }
        else{
            logger.log(Level.ERROR,"entity not saved" + entity.getBarcode());
            return HttpURLConnection.HTTP_BAD_REQUEST;
        }
    }
}
