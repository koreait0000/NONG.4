package com.spring.nong4.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class MyFileUtils {

    @Value("${spring.servlet.multipart.location}")
    private String uploadImagePath; //"D:/springImg"

    //폴더 생성
    public void makeFolders(String path) {
        File folder = new File(path);
        folder.mkdirs();
    }

    //저장 경로 생성
    public String getSavePath(String path) {

        return uploadImagePath + "/" + path;
    }

    //랜덤 파일명 생성
    public String getRandomFileNm() {
        return UUID.randomUUID().toString();
    }

    //랜덤 파일명 생성 (with 확장자)    "aaa.jpg"
    public String getRandomFileNm(String originFileNm) {
        return getRandomFileNm() + "." + getExt(originFileNm);
    }

    //랜덤 파일명 생성
    public String getRandomFileNm(MultipartFile file) {
        return getRandomFileNm(file.getOriginalFilename());
    }

    //확장자 얻기               "aaa.jpg"
    public String getExt(String fileNm) {
        return fileNm.substring(fileNm.lastIndexOf(".") + 1);
    }

    //파일 저장 & 랜덤 파일명 리턴                  target = "profile/10"
    public String transferTo(MultipartFile mf, String target) {
        String fileNm   = getRandomFileNm(mf); // "aslkdfjaslkf2130asdwds.jpg"
        String basePath = getSavePath(target); //이미지를 저장할 절대경로값을 셍성한다. "D:/springImg/profile/10"
        makeFolders(basePath); //(폴더가 없을 수 있기 때문에)폴더를 생성한다.
        File saveFile = new File(basePath, fileNm);

        try {
            mf.transferTo(saveFile);
            return fileNm;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
