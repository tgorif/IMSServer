package com.tgorif.IMSServer.Sku.API;

import com.tgorif.IMSServer.Sku.Core.SkuData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkuDataRepository extends MongoRepository<SkuData, String> {

    SkuData findByBarcode(String barcode);
}
