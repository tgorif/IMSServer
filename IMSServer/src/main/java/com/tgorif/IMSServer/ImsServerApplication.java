package com.tgorif.IMSServer;



import com.tgorif.IMSServer.Sku.Persistance.SkuManager;
import com.tgorif.IMSServer.Sku.Core.AutoExpirationDate;
import com.tgorif.IMSServer.Sku.Core.NutritionData;
import com.tgorif.IMSServer.Sku.Core.SkuData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@SpringBootApplication
public class ImsServerApplication implements CommandLineRunner {

	@Autowired
	SkuManager skuManager;

	public static void main(String[] args) {
		SpringApplication.run(ImsServerApplication.class, args);
	}
    @Override
	public void run(String[] args) {

	}
}
