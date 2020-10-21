package io.adana.infinite.common.utils;

import com.google.gson.Gson;

/**
 * @author simon
 * @email merin@outlook.com
 * @date 2019/3/7
 * @description the utility class of Gson
 */
public class GsonUtil {

    private static final Gson gson = new Gson();

    /**
     * Object cast json
     *
     * @param sourceObject the source object
     * @return String
     */
    public static String toJson(Object sourceObject) {
        if (sourceObject instanceof Number || sourceObject instanceof Boolean || sourceObject instanceof String) {
            return String.valueOf(sourceObject);
        }
        return gson.toJson(sourceObject);
    }

    /**
     * json cast Object
     *
     * @param jsonStr the string of json
     * @param clazz   class
     * @param <T>     type
     * @return <T>
     */
    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        return gson.fromJson(jsonStr, clazz);
    }
}
