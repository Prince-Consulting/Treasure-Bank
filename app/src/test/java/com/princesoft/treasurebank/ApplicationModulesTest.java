package com.princesoft.treasurebank;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ApplicationModulesTest {

    @Test
    void verifiesApplicationModules() {
        ApplicationModules.of(TreasureBankApplication.class).verify();
    }
}
