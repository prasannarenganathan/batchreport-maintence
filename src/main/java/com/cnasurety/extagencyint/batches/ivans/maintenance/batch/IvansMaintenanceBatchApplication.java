package com.cnasurety.extagencyint.batches.ivans.maintenance.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.cnasurety.extagencyint.batches.ivans.maintenance.*" })
public class IvansMaintenanceBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(IvansMaintenanceBatchApplication.class, args);
        System.exit(0);
    }

}

