package com.magicrepokit.i18n.config;

import com.magicrepokit.i18n.constant.I18nConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class LoadMessageResourceWithProperties implements LoadMessageResource {
    @Value("classpath:i18n/")
    private Resource resource;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public Map<String, Map<String, String>> load(){
        return load(resource);
    }

    @Override
    public Map<String, Map<String, String>> loadBase() {
        Resource baseResource = resourceLoader.getResource(I18nConstant.BASE_RESOURCE);
        return load(baseResource);
    }

    public Map<String, Map<String, String>> load(Resource resource) {
        Map<String, Map<String, String>> result;
        try {
            File parentFile = resource.getFile();
            result = load(parentFile.listFiles());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Map<String, Map<String, String>> load(File[] files) throws IOException {
        Map<String, Map<String, String>> result = new HashMap<>();
        Properties properties = new Properties();
        for (File file : files) {
            FileInputStream fileInputStream = new FileInputStream(file);
            // 指定正确的字符编码
            Reader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            properties.load(reader);
            Map<String, String> localeMap = new HashMap<>();
            for (String key : properties.stringPropertyNames()) {
                localeMap.put(key, properties.getProperty(key));
            }
            properties.clear();
            result.put(getKey(file.getName()), localeMap);
        }
        return result;
    }

    private String getKey(String fileName) {
        //去掉后后缀
        String[] keys = fileName.split("\\.")[0].split("_");
        if (keys.length == 1) {
            return "";
        } else if (keys.length == 2) {
            return keys[1];
        } else if (keys.length == 3) {
            return keys[1] + "_" + keys[2];
        } else {
            throw new RuntimeException("i18n-file's name is error.");
        }

    }
}
