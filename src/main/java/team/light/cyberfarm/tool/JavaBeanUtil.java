package team.light.cyberfarm.tool;

import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

public class JavaBeanUtil {
    private static Object convertStringToParameter(String value, Class<?> parameterType) {
        if (parameterType.equals(String.class)) {
            return value;
        } else if (parameterType.equals(Integer.class) || parameterType.equals(int.class)) {
            return Integer.parseInt(value);
        } else if (parameterType.equals(Double.class) || parameterType.equals(double.class)) {
            return Double.parseDouble(value);
        } else if (parameterType.equals(Boolean.class) || parameterType.equals(boolean.class)) {
            return Boolean.parseBoolean(value);
        }
        // 可以添加更多类型的转换，例如Long、Float等

        // 如果无法识别参数类型，则返回null
        return null;
    }
    private static String convertSetterToFieldName(String setterName) {
        char[] chars = setterName.substring(3).toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
    public static <T> T mappingObject(Class<T> clazz, Set<String> ignore, Map<String, String> mapping,
                                      HttpServletRequest request)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Stream<Method> set = Arrays.stream(clazz.getMethods()).filter(method -> method.getName().startsWith("set"));
        if (ignore != null) {
            set = set.filter(method -> !ignore.contains(method.getName()));
        }
        Method[] methods = set.toArray(Method[]::new);
        T beenObject = clazz.getConstructor().newInstance();
        for (Method method : methods) {
            Class<?> parameterType = method.getParameterTypes()[0];
            String mappingName = mapping.getOrDefault(method.getName(), convertSetterToFieldName(method.getName()));
            String parameter = request.getParameter(mappingName);
            method.invoke(beenObject, convertStringToParameter(parameter, parameterType));
        }


        return beenObject;
    }

    public static <T> T mappingObject(Class<T> clazz, HttpServletRequest request, String...ignore)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return mappingObject(clazz, new HashSet<>(List.of(ignore)), new HashMap<>(), request);
    }
    public static <T> T mappingObject(Class<T> clazz, Map<String, String> mapping,HttpServletRequest request)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return mappingObject(clazz, null, mapping, request);
    }

}
