package com.princesoft.auth_internal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.princesoft.auth_internal.application.impl")
public class AuthModuleConfiguration {
}
