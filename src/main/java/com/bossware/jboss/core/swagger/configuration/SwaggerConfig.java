package com.bossware.jboss.core.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2  //	Enables Springfox swagger 2
public class SwaggerConfig {
    @Bean
    public Docket jbossApi() {
        Docket docketBuilder = new Docket(DocumentationType.SWAGGER_2)  //Docket, Springfox’s, primary api configuration mechanism is initialized for swagger specification 2.0
                .select() //select() returns an instance of ApiSelectorBuilder to give fine-grained control over the endpoints exposed via swagger.
                .apis(RequestHandlerSelectors.basePackage("com.bossware.jboss.api")) //	apis() allows selection of RequestHandler's using a predicate. The example here uses an any predicate (default). Out of the box predicates provided are any, none, withClassAnnotation, withMethodAnnotation and basePackage.
                .paths(PathSelectors.any()) //paths() allows selection of Path's using a predicate. The example here uses an any predicate (default). Out of the box we provide predicates for regex, ant, any, none.
                .build();//	The selector needs to be built after configuring the api and path selectors.
        return docketBuilder;
    }
}
