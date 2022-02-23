package com.srytzj.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author sry
 * @description
 * @date 2022/2/18 2:12 下午
 * @Version 1.0
 */
public class FileUtil {

    public static String saveImg(MultipartFile multipartFile, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = multipartFile.getOriginalFilename();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }
}
