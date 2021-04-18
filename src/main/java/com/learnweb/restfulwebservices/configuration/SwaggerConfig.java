package com.learnweb.restfulwebservices.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Aanif", "http://localhost/myweb", "abc@xyz.com");
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("RESTful Web Services",
            "Description", "1.0",
            "urn:tos",DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    private static final Set<String> PRODUCES_CONSUMES = new HashSet<>(Arrays.asList("application/json","application/xml"));


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(PRODUCES_CONSUMES)
                .consumes(PRODUCES_CONSUMES);
    }
}
