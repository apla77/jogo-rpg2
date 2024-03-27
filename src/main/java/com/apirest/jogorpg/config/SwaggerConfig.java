package com.apirest.jogorpg.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

// Url acesso Api RPG Swagger, http://localhost:8080/swagger-ui/index.html

public class SwaggerConfig {

    @Bean
    public Docket productApi( ) {
        return new Docket( DocumentationType.SWAGGER_2 )
                .select( )
                .apis( RequestHandlerSelectors.basePackage("com.apirest.jogorpg" ) )
                .paths( regex( "/rpg.*" ) )
                .build( )
                .apiInfo( metaInfo( ) );
    }
    private ApiInfo metaInfo( ) {
        ApiInfo apiInfo = new ApiInfo( "TODO List", "RPG list API REST.", "1.0.0",
                                       "Terms of Service", new Contact( "Mauricio Fernandes",
                                                              "https://github.com/apla77",
                                                              "verocidade77@gmail.com" ),
                                       "Apache License Version 2.0",
                                       "https://www.apache.org/licesen.html",
                                       new ArrayList<VendorExtension>( ) );
        return apiInfo;
    }
}
