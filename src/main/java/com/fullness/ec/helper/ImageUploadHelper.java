package com.fullness.ec.helper;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Base64;

import java.util.UUID;
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

    public static String uploadFile(String filename, byte[] imageByte){
        String uuidFileName = UUID.randomUUID().toString()+filename;
        String filepath = new File("src/main/webapp/img").getAbsolutePath()+File.separator+uuidFileName;
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(filepath);
            fos.write(imageByte);
        } catch (FileNotFoundException e){
            throw new RuntimeException("ファイル指定エラー");
        } catch (IOException e){
            throw new RuntimeException("ファイル書き込みエラー");
        } finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException("ファイル閉じる時のエラー");
                }
            }
        }
        return uuidFileName;
    }
}
