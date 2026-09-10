package com.princesoft.treasurebank;

import com.princesoft.auth_internal.config.AuthModuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AuthModuleConfiguration.class)
public class TreasureBankApplication {

    public static void main(String[] args) {

        SpringApplication.run(TreasureBankApplication.class, args);
    }

}
