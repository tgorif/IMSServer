package com.tgorif.IMSServer.Sku.Persistance;

import com.tgorif.IMSServer.Sku.Core.Sku;
import com.tgorif.IMSServer.Sku.Core.SkuEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Set;

public interface SkuEntityRepository extends MongoRepository<SkuEntity, String> {

    Set<SkuEntity> findByBarcode(String barcode);
}
