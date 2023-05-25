package yeinyeonha.SMooD.config;

import com.fasterxml.classmate.TypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import yeinyeonha.SMooD.domain.User;
import yeinyeonha.SMooD.dto.*;
import yeinyeonha.SMooD.websocket.ChatRoomResponseDto;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {
    private final TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .additionalModels(typeResolver.resolve(ResponseDto.class),
                        typeResolver.resolve(ChatRoomResponseDto.class),
                        typeResolver.resolve(User.class),
                        typeResolver.resolve(PostResponseDto.class),
                        typeResolver.resolve(CommentResponseDto.class),
                        typeResolver.resolve(TableauResponseDto.class),
                        typeResolver.resolve(StoreResponseDto.class))
                .securityContexts(Arrays.asList(securityContext())) // 추가
                .securitySchemes(Arrays.asList(apiKey())) // 추가
                .useDefaultResponseMessages(false) // Swagger 에서 제공해주는 기본 응답 코드를 표시할 것이면 true
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("yeinyeonha.SMooD.controller")) // Controller가 들어있는 패키지. 이 경로의 하위에 있는 api만 표시됨.
                .paths(PathSelectors.any()) // 위 패키지 안의 api 중 지정된 path만 보여줌. (any()로 설정 시 모든 api가 보여짐)
                .build();
    }
    // 추가
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }
    // 추가
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

    // 추가
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SMooD Application Rest API Documentation")
                .description("SMooD Server")
                .version("1.0")
                .build();
    }
}
