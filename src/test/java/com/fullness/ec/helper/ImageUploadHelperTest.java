package com.fullness.ec.helper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageUploadHelperTest {
    @Test
    void testUploadFile() throws IOException {
        // テスト用の画像データを作成
        String filename = "test.png";
        byte[] imageByte = "test image content".getBytes();

        // ファイルアップロードのメソッドを実行
        String uploadedFileName = ImageUploadHelper.uploadFile(filename, imageByte);

        // アップロード先のファイルパスを取得
        String filepath = new File("src/main/resources/static/img").getAbsolutePath() + File.separator
                + uploadedFileName;

        // ファイルが正しく作成されたかをアサート
        File uploadedFile = new File(filepath);
        assertTrue(uploadedFile.exists());

        // 作成されたファイルの内容をアサート
        try (FileOutputStream fos = new FileOutputStream(uploadedFile)) {
            fos.write(imageByte);
        }

        // テスト後に作成されたファイルを削除
        if (uploadedFile.exists()) {
            uploadedFile.delete();
        }
    }
}