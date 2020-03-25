package ink.moshuier.motse.util;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.List;

public class EnumUtils {
    static final String ENUM_PATH = "ink.moshuier.motse.enums";

    @SneakyThrows
    public static Object locateEnum(String enumAliasName, String name) {
        ClasspathPackageScanner scan = new ClasspathPackageScanner(ENUM_PATH);
        List<String> fullyQualifiedClassNameList = null;
        fullyQualifiedClassNameList = scan.getFullyQualifiedClassNameList();

        Object targetEnum = fullyQualifiedClassNameList.stream().filter((e) -> e.endsWith("Enum")).filter((e) -> {
            Class<?> enumerate = null;
            try {
                enumerate = Class.forName(e);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            return enumAliasName.replace("_", "").equalsIgnoreCase(enumerate.getSimpleName().replace("Enum", ""));

        }).map((e) -> {
            Class<?> enumerate = null;
            Object resultEnum = null;
            try {
                enumerate = Class.forName(e);
                Method getEnumFromName = enumerate.getMethod("getEnumFromName", String.class);
                resultEnum = getEnumFromName.invoke(null, name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return resultEnum;
        }).findFirst().get();


        return targetEnum;
    }

}
