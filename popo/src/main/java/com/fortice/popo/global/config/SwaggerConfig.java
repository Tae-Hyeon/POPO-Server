package com.fortice.popo.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration      // to Scan
public class SwaggerConfig {
    /**
     * Swagger 주요 설정
     */
    @Bean
    public Docket popoApi() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false) // 200, 404 등 기본 응답 코드를 노출하지 않음
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/popo/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("POPO API")
                .description("POPO API Swagger")
                .version("0.0.1")
                .build();
    }
}
