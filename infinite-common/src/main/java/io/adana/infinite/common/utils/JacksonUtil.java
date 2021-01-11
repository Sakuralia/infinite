package io.adana.infinite.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author sakura
 * @description java与json之间的转换工具类
 * @date 2020-12-07 19:00
 */
public class JacksonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    public static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * java 对象转化为json字符串
     *
     * @param object java 对象
     * @return json 字符串
     */
    public static String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.info("the exception happened in the processor toJsonString is:", e.getCause());
            throw new RuntimeException();
        }
    }

    /**
     * java 对象转化为json字符串美化输出
     *
     * @param obj java 对象
     * @return json 字符串
     */
    public static String toPrettyJsonString(Object obj) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("the exception happened in the processor toJsonString with PrettyPrinter is:", e.getCause());
            throw new RuntimeException();
        }
    }

    /**
     * java 对象转化为byte数组
     *
     * @param object java 对象
     * @return byte数组
     */
    public static byte[] toJsonByteArray(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            logger.error(" the exception happened in toJsonBytes is:", e.getCause());
            throw new RuntimeException();
        }
    }

    /**
     * Json 转化为 Java 对象
     *
     * @param json  Json
     * @param clazz Java 对象
     * @param <T>   类型
     * @return 该对象的类型
     */
    public static <T> T parseToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            logger.error(" the exception happened in parseObject is:", e.getCause());
            throw new RuntimeException();
        }
    }

    /**
     * Json 转化为 List 集合
     *
     * @param json    json字符串
     * @param classes List集合数组
     * @param <T>     List内所在的类型
     * @return List集合
     */
    public static <T> List<T> parseToList(String json, Class<T>[] classes) {
        try {
            return objectMapper.readValue(json, getCollectionType(objectMapper, List.class, classes));
        } catch (JsonProcessingException e) {
            logger.error("the exception happened in parseToList is:", e.getCause());
            throw new RuntimeException();
        }
    }

    /**
     * Json 转化为 Set 集合
     *
     * @param json    Json字符串
     * @param classes Set 集合
     * @param <T>     引用类型
     * @return Set 集合
     */
    public static <T> Set<T> parseToSet(String json, Class<T>[] classes) {
        try {
            return objectMapper.readValue(json, getCollectionType(objectMapper, Set.class, classes));
        } catch (JsonProcessingException e) {
            logger.error(" the exception happened in parseToSet is:", e.getCause());
            throw new RuntimeException();
        }
    }

    /**
     * JSON字符串转Collection集合
     *
     * @param json    Json字符串
     * @param classes Collection数组
     * @param <T>     引用类型
     * @return Collection集合
     */
    public static <T> Collection<T> parseToCollection(String json, Class<T>[] classes) {
        try {
            return objectMapper.readValue(json, getCollectionType(objectMapper, Collection.class, classes));
        } catch (JsonProcessingException e) {
            logger.error(" the exception happened in parseToCollection is:", e.getCause());
            throw new RuntimeException();
        }
    }

    /**
     * 获取泛型的Collection类型
     *
     * @param mapper          objectMapper对象
     * @param collectionClass 集合类型
     * @param elementClasses  元素类型数组
     * @return JavaType
     */
    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
