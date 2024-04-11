package com.tgorif.IMSServer.Sku.Persistance;

import com.tgorif.IMSServer.Sku.Core.InventoryHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryHistoryRepository extends MongoRepository<InventoryHistory, String> {
}
