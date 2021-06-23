package dinamismo.app.personapi.config;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage m201   = simpleMessage(201, "Created");
    private final ResponseMessage m204put= simpleMessage(204, "Updated");
    private final ResponseMessage m204del= simpleMessage(204, "Deleted");
    private final ResponseMessage m403   = simpleMessage(403, "Forbidden");
    private final ResponseMessage m404   = simpleMessage(404, "Not Found");
    //Necessario tratar
    //private final ResponseMessage m422   = simpleMessage(422, "Erro de validação");
    private final ResponseMessage m500   = simpleMessage(500,  "Internal Server Error");
    
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)

                .useDefaultResponseMessages(false)  
                .globalResponseMessage( RequestMethod.GET, Arrays.asList(m403, m404, m500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
                
                .select()
                .apis(RequestHandlerSelectors.basePackage("dinamismo.app.personapi.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API REST para gerenciamento de pessoas de uma empresa",
                "Esta api foi desenvolvida para o curso da Digital Innovation: Expert class - Desenvolvendo um sistema de gerenciamento de pessoas em API REST com Spring Boot",
                "Versão 0.0",
                "https://app.digitalinnovation.one/terms/",
                new Contact("Jeferson L P Silva", "https://www.linkedin.com/in/jefersonluispassossilva/?originalSubdomain=br", "jefersonsilvadev@hotmail.com"),
                "Permitido o uso para estudantes",
                "https://app.digitalinnovation.one/terms/",
                Collections.emptyList()         
        );
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder()
                                  .code(code)
                                  .message(msg).build();
    }

    private ResponseMessage customMessage201() {
        Map<String, Header> map= new HashMap<>();
        map.put("location", new Header("location",
                "URI do novo recurso", new ModelRef("string")));
        return new ResponseMessageBuilder().code(201).message("Created")
                   .headersWithDescription(map).build();
    }
    
    
}
