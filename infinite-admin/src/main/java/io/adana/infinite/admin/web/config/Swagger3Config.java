package io.adana.infinite.admin.web.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author sakura
 * @version 1.0
 * @description <p>
 * swagger 接口文档配置类
 * &lt;pre&gt;@EnableOpenApi&lt;/pre&gt; enable open api doc 3.0.0
 * </p>
 * @date 2020/10/23 14:58
 */
@Configuration
@EnableOpenApi
public class Swagger3Config {
    /**
     * @return
     * @description <p>
     * the function generated some api from the annotation of method.
     * </p>
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * @return ApiInfo
     * @description <p>
     * this describes some information about api,
     * title, connection,version and etc.
     * </p>
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Infinite—Swagger3.0.0接口文档")
                .description("注意当前接口仅提供给开发测试环境,产线环境应禁用该访问路径")
                .contact(new Contact("Sakura", "https://github.com/singularnost/infinite.git", "merin@outlook.com"))
                .version("1.1.0")
                .build();
    }
}
