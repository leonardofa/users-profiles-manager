package br.com.leonardo.estudo.usermanager.infrastructure.openapi;

import br.com.leonardo.estudo.usermanager.api.exception.Problem;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@SecurityScheme(name = "security_basic_auth", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class OpenApiConfiguration {

  private static final String badRequestResponse = "BadRequestResponse";
  private static final String notFoundResponse = "NotFoundResponse";
  private static final String notAcceptableResponse = "NotAcceptableResponse";
  private static final String internalServerErrorResponse = "InternalServerErrorResponse";

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Users and Profiles Management API")
            .version("v1")
            .license(new License()
                .name("MIT")
                .url("https://opensource.org/license/mit/")
            )
        ).externalDocs(new ExternalDocumentation()
            .description("Leonardo Ferreira Alves")
            .url("https://leonardofa.com")
        ).components(new Components()
            .responses(responses())
        );
  }

  private Map<String, ApiResponse> responses() {
    final Map<String, ApiResponse> apiResponseMap = new HashMap<>();

    Content content = new Content()
        .addMediaType(APPLICATION_JSON_VALUE,
            new MediaType().schema(new Schema<Problem>().$ref("Problem")));

    apiResponseMap.put(badRequestResponse, new ApiResponse()
        .description("invalid request")
        .content(content));

    apiResponseMap.put(notFoundResponse, new ApiResponse()
        .description("resource not found")
        .content(content));

    apiResponseMap.put(notAcceptableResponse, new ApiResponse()
        .description("bad format resource")
        .content(content));

    apiResponseMap.put(internalServerErrorResponse, new ApiResponse()
        .description("internal error")
        .content(content));

    return apiResponseMap;
  }

}
