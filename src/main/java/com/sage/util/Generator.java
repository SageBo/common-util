package com.sage.util;

import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sage on 2018/8/6.
 */
public class Generator {

    public static final String BASE_PACKAGE = "com.company.project";//生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）

    public static final String API_PACKAGE = BASE_PACKAGE + ".api";//生成的Model所在包
//    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";//生成的Mapper所在包

    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/generator/templates";//模板位置

    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径

    private static final String PACKAGE_PATH_API = packageConvertPath(API_PACKAGE);//生成的Service存放路径

    private static final String AUTHOR = "CodeGenerator";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date


    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }


    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    public static void genBean(String beanName, String beanContext, String beanType) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            data.put("beanName", beanName);
            data.put("beanContext", beanContext);
            data.put("beanType", beanType);
            data.put("apiPackage", API_PACKAGE);

//            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_API +  beanName + beanType + ".java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("bean.ftl").process(data,
                    new FileWriter(file));
            System.out.println(beanName + beanType + ".java 生成成功");

        } catch (Exception e) {
            throw new RuntimeException("生成bean失败", e);
        }
    }
    public static void genApi(String url, String reqJson, String respJson) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            String apiName = "getApkVer";
            String respBean = "ApkVerResp";
            String reqBean = "ApkVerReq";
            String method = "get";

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            data.put("apiPackage", API_PACKAGE);
            data.put("respBean", respBean);
            data.put("apiName", apiName);
            data.put("reqBean", reqBean);
            data.put("method", method);
            data.put("url", url);


            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_API +  "TestGen" + apiName + ".java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("api.ftl").process(data,
                    new FileWriter(file));
            System.out.println("TestGen" + apiName + ".java 生成成功");

        } catch (Exception e) {
            throw new RuntimeException("生成bean失败", e);
        }
    }

    public static void main(String[] args) {
        /// 读取json字符串
//        String json = FileUtils.readToString(new File(
//                "temp" + File.separator + "Json" + File.separator + "JsonString.txt"), "UTF-8");
        String json = "{\"versionCode\":2, \"versionName\":\"2.0\", \"desc\":\"这是一个灰常流弊的版本, 快来下载吧\",\"downloadUrl\":\"http://192.168.51.105:8080/MobileSafe89.apk\"}";


//        genBean("ApkVer", JsonUtils.parseJson2javaBeanStr(json), "Req");
        String curl = "curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' --header 'x-snbps-account-id: test' --header 'x-snbps-cplc: 479044204700E753010060850741739377064810000000510000044715B3E0388001000000000000tttt' --header 'x-snbps-imei: 111222333' --header 'x-snbps-module: Lakala' --header 'x-snbps-vendor: lakala' --header 'x-snbps-os-version: test os 1.0' --header 'x-snbps-partner-id: 1504209900006291' --header 'x-snbps-rom-version: test rom 1.0' -d '{\n" +
                "  \"app_code\": \"string\",\n" +
                "  \"order_type\": 0,\n" +
                "  \"payment_amount\": 0,\n" +
                "  \"payment_channel\": \"string\",\n" +
                "  \"shift_order_no\": \"string\"\n" +
                "}' 'http://tsmtest.snowballtech.com:8084/gateway/v1.0/order/get_order_no'";

        String methodPatern = "^curl -X (.*?) --head(.*)";
        Pattern r = Pattern.compile(methodPatern);
        Matcher m = r.matcher(curl);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );

        } else {
            System.out.println("NO MATCH");
        }

//        genApi("http://tsmtest.snowballtech.com:8084/gateway/v1.0/order/get_order_no", null, null);
    }


}
