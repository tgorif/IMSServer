package com.tgorif.IMSServer.Sku.API;

import com.tgorif.IMSServer.Sku.Core.Sku;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkuRepository extends MongoRepository<Sku, String> {
}
