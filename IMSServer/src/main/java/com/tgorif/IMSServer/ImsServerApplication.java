package com.tgorif.IMSServer;



import com.tgorif.IMSServer.Sku.Persistance.SkuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
