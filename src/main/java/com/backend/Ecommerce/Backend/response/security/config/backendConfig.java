package com.backend.Ecommerce.Backend.response.security.config;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class backendConfig {
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
