package com.fullness.ec.helper;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadHelper {
    public static String createBase64ImageString(MultipartFile file){
        StringBuffer data = new StringBuffer();
        String base64 = "";
        try{
            base64 = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e){
            throw new RuntimeException("画像ファイルをBase64エンコード時のエラー",e);
        }
        data.append("data:"+file.getContentType()+";base64,");
        data.append(base64);
        return data.toString();
    }
}
