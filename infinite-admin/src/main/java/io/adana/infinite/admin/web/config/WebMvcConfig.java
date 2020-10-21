package io.adana.infinite.admin.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author simonyang
 * @description webmvc 配置类
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 处理时间的格式
     *
     * @return LocalDateTimeSerializer
     */
    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 跨域请求过滤器
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

    /**
     * 配置转化消息
     *
     * @param converters List
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper build = new Jackson2ObjectMapperBuilder()
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .serializerByType(LocalDateTime.class, localDateTimeSerializer())
                .build();
        // 处理Null
        build.setSerializerFactory(
                build.getSerializerFactory()
                        .withSerializerModifier(new BeanSerializerModifier() {
                            @Override
                            public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                                             List<BeanPropertyWriter> beanProperties) {
                                for (BeanPropertyWriter beanProperty : beanProperties) {
                                    JavaType type = beanProperty.getType();
                                    if (type.isArrayType() || type.isCollectionLikeType()) {
                                        beanProperty.assignNullSerializer(new JsonSerializer<Object>() {
                                            @Override
                                            public void serialize(Object o, JsonGenerator jsonGenerator,
                                                                  SerializerProvider serializerProvider) throws IOException {
                                                jsonGenerator.writeStartArray();
                                                jsonGenerator.writeEndArray();
                                            }
                                        });
                                    }
                                    if (type.isTypeOrSubTypeOf(String.class)) {
                                        beanProperty.assignNullSerializer(new JsonSerializer<Object>() {
                                            @Override
                                            public void serialize(Object o, JsonGenerator jsonGenerator,
                                                                  SerializerProvider serializerProvider) throws IOException {
                                                jsonGenerator.writeString("");
                                            }
                                        });
                                    }
                                    if (type.isTypeOrSubTypeOf(Boolean.class)) {
                                        beanProperty.assignNullSerializer(new JsonSerializer<Object>() {
                                            @Override
                                            public void serialize(Object o, JsonGenerator jsonGenerator,
                                                                  SerializerProvider serializerProvider) throws IOException {
                                                jsonGenerator.writeBoolean(false);
                                            }
                                        });
                                    }
                                    if (type.isMapLikeType()) {
                                        beanProperty.assignNullSerializer(new JsonSerializer<Object>() {
                                            @Override
                                            public void serialize(Object o, JsonGenerator jsonGenerator,
                                                                  SerializerProvider serializerProvider) throws IOException {
                                                jsonGenerator.writeStartObject();
                                                jsonGenerator.writeEndObject();
                                            }
                                        });
                                    }
                                    if (type.isTypeOrSubTypeOf(Integer.class) ||
                                            type.isTypeOrSubTypeOf(Long.class) ||
                                            type.isTypeOrSubTypeOf(Double.class) ||
                                            type.isTypeOrSubTypeOf(Float.class)) {
                                        beanProperty.assignNullSerializer(new JsonSerializer<Object>() {
                                            @Override
                                            public void serialize(Object o, JsonGenerator jsonGenerator,
                                                                  SerializerProvider serializerProvider) throws IOException {
                                                jsonGenerator.writeNumber(0);
                                            }
                                        });
                                    }
                                    if (type.isTypeOrSuperTypeOf(LocalDateTime.class) || type.isTypeOrSuperTypeOf(LocalDate.class)) {
                                        beanProperty.assignNullSerializer(new JsonSerializer<Object>() {
                                            @Override
                                            public void serialize(Object o, JsonGenerator jsonGenerator,
                                                                  SerializerProvider serializerProvider) throws IOException {
                                                jsonGenerator.writeString("");
                                            }
                                        });
                                    }
                                }
                                return beanProperties;
                            }
                        }));
        converters.add(new MappingJackson2HttpMessageConverter(build));
    }
}
