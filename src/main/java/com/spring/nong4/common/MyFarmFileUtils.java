//package com.spring.nong4.common;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//
//@Component
//public class MyFarmFileUtils {
//
//    @Value("${spring.farmImg.multipart.location}")
//    private String resourcePath;
//
//    //폴더 생성
//    public void makeFolders(String path) {
//        File folder = new File(path);
//        folder.mkdirs();
//    }
//
//    //저장 경로 생성
//    public String getSavePath(String path) {
//
//        return resourcePath + "/" + path;
//    }
//}
