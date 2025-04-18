package dev.leandro.kotlin_spring_boot.config


import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("RESTful API with Kotlin 1.6.10 and Spring Boot 3.4.4")
                    .version("v2")
                    .description("Some description about your API.")
                    .termsOfService("https://leandrogoncalves.com.br")
                    .license(
                        License().name("Apache 2.0")
                            .url("https://leandrogoncalves.com.br")
                    )
            )
    }
}