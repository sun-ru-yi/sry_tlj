package com.srytzj.controller.journalism;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @ClassName: JournalismController
 * @description: 实现处理图片和文本的存储
 * @author: wrc
 * @Date: 2019年9月24日 下午3:15:42
 */
@Controller
public class JournalismController {
    //存储路径
    String path = "C:\\Users\\Administrator\\Desktop\\";


    @GetMapping("/journalism")
    public String index() {
        System.out.println("图片文字处理");
        return "compile";
    }

    @ResponseBody
    @PostMapping("/journalism")
    public String uploadJournalism(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);

        //获取图片集合
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("blFile");

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            try {
                //循环写入
                saveImg(file, path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //获取文本
        String id = request.getParameter("agentName");
        System.out.println(id);

        return "OK";
    }


    //写入
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