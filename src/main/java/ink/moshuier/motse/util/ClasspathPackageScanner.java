package ink.moshuier.motse.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @author : Sarah Xu
 * @date : 2020-01-20
 **/
public class ClasspathPackageScanner {
    private Logger logger = LoggerFactory.getLogger(ClasspathPackageScanner.class);
    private String basePackage;
    private ClassLoader cl;

    public static void main(String[] args) throws Exception {
        ClasspathPackageScanner scan = new ClasspathPackageScanner("ink.moshuier.motse.enums");
        scan.getFullyQualifiedClassNameList();
    }

    /**
     * 初始化
     *
     * @param basePackage
     */
    public ClasspathPackageScanner(String basePackage) throws IOException {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();
    }

    public ClasspathPackageScanner(String basePackage, ClassLoader cl) throws IOException {
        this.basePackage = basePackage;
        this.cl = cl;
    }

    /**
     * 34      *获取指定包下的所有字节码文件的全类名
     * 35
     */
    public List<String> getFullyQualifiedClassNameList() throws IOException {
        logger.info("开始扫描包{}下的所有类", basePackage);
        return doScan(basePackage, new ArrayList<String>());
    }

    public List<String> doScan(String basePackage, List<String> nameList) throws IOException {
        String splashPath = dotToSplash(basePackage);
        URL url = cl.getResource(splashPath);   //file:/D:/WorkSpace/java/ScanTest/target/classes/com/scan
        String filePath = getRootPath(url);
        List<String> names = null; // contains the name of the class file. e.g., Apple.class will be stored as "Apple"
        if (isJarFile(filePath)) {// 先判断是否是jar包，如果是jar包，通过JarInputStream产生的JarEntity去递归查询所有类
            if (logger.isDebugEnabled()) {
                logger.debug("{} 是一个JAR包", filePath);
            }
            names = readFromJarFile(filePath, splashPath);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("{} 是一个目录", filePath);
            }
            names = readFromDirectory(filePath);
        }
        for (String name : names) {
            if (isClassFile(name)) {
                nameList.add(toFullyQualifiedName(name, basePackage));
            } else {
                doScan(basePackage + "." + name, nameList);
            }
        }
        if (logger.isDebugEnabled()) {
            for (String n : nameList) {
                logger.debug("找到{}", n);
            }
        }
        return nameList;

    }

    private String toFullyQualifiedName(String shortName, String basePackage) {
        StringBuilder sb = new StringBuilder(basePackage);
        sb.append('.');
        sb.append(trimExtension(shortName));
        //打印出结果
        System.out.println(sb.toString());
        return sb.toString();
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
        JarInputStream jarIn = new JarInputStream(new FileInputStream(jarPath));
        JarEntry entry = jarIn.getNextJarEntry();
        List<String> nameList = new ArrayList<String>();
        while (null != entry) {
            String name = entry.getName();
            if (name.startsWith(splashedPackageName) && isClassFile(name)) {
                nameList.add(name);
            }

            entry = jarIn.getNextJarEntry();
        }

        return nameList;
    }

    private List<String> readFromDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();

        if (null == names) {
            return null;
        }

        return Arrays.asList(names);
    }

    private boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    private boolean isJarFile(String name) {
        return name.endsWith(".jar");
    }

    public static String getRootPath(URL url) {
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf('!');

        if (-1 == pos) {
            return fileUrl;
        }

        return fileUrl.substring(5, pos);
    }

    public static String trimExtension(String name) {
        int pos = name.indexOf('.');
        if (-1 != pos) {
            return name.substring(0, pos);
        }

        return name;
    }

    public static String dotToSplash(String name) {
        return name.replaceAll("\\.", "/");
    }
}
