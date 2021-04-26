package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileUploadAndDownController {
    @Autowired
    HttpServletRequest request;

    //可上传文件类型定义
    private List fileTypes = new ArrayList(){
        {
            add("png");
            add("jpeg");
            add("pdf");
        }
    };

    @RequestMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        JSONObject jsonResult = new JSONObject();
        if (file.isEmpty()){
            jsonResult.put("code",201);
            jsonResult.put("msg","请选择文件");
            return jsonResult.toJSONString();
        }
        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        if (!fileTypes.contains(fileType)){
            jsonResult.put("code",201);
            jsonResult.put("msg","仅支持 .pdf/.png/.jpeg 文件上传");
            return jsonResult.toJSONString();
        }
        System.out.println(fileType);
//        String path = "D:/idea-workspace/upload-file-demo/src/main/resources/files/";
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/file/";
        // 判断文件保存的物理路径是否存在，不存在创建
        File f = new File(path) ;
        if(! f.exists()) {
            f.mkdirs() ;
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String initFileName = uuid + "." +fileType;
        Path filePath = Paths.get(path + initFileName);
        System.out.println("filePath-------------"+filePath);
        try {
            byte[] fileBytes = file.getBytes();
            Files.write(filePath, fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonResult.put("code",0);
        jsonResult.put("msg","加载成功");
        jsonResult.put("data",initFileName);
        return jsonResult.toJSONString();
    }

    @RequestMapping("/show")
    public String downFile(@RequestParam("file") MultipartFile file){
//        // 获取文件路径前缀
//        String filePrefix = RuoYiConfig.getProfile();
//        // 获取文件名
//        String fileName = tPath.substring(tPath.lastIndexOf("/") + 1);
//        // 文件下载
//        FileUtils.downloadUtil(response, filePrefix + tPath, fileName);

        return null;
    }
}

