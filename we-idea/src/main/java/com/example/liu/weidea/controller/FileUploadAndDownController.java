package com.example.liu.weidea.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.liu.weidea.bean.ResponseData;
import com.example.liu.weidea.service.UserService;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
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

    @Autowired
    UserService userService ;

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

    @GetMapping("/getFileByFileName")
    public void getFileByFileName(HttpServletResponse response,@RequestParam(value = "file",required = false) String file) throws Exception {
        //获取resources文件夹的绝对地址
        String sourcePath = ClassUtils.getDefaultClassLoader().getResource("static/file/"+file).getPath();
        System.out.println(sourcePath);
        FileInputStream fis = null;
        OutputStream os = null ;
        fis = new FileInputStream(sourcePath);
        // 得到文件大小
        int size = fis.available();
        byte data[] = new byte[size];
        // 读数据
        fis.read(data);
        fis.close();
        // 设置返回的文件类型
        String fileType = file.substring(file.lastIndexOf(".") + 1, file.length());
        if(fileType.equals("pdf")){
            fileType="application/pdf";
        }else {
            fileType=getFileType(sourcePath);
        }
        response.setContentType(fileType);
        os = response.getOutputStream();
        os.write(data);
        os.flush();
        os.close();
    }




    private static String getFileType(String path){
        String fileType = "";
        try{
            InputStream file = new BufferedInputStream(new FileInputStream(path));
            fileType = URLConnection.guessContentTypeFromStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return fileType;
    }

//    //获取头像地址
//    @GetMapping("/photoUrl/{name}")
//    public String getVideos(HttpServletResponse response, @PathVariable("name") String name) throws Exception{
//        String photoUrl=userService.getPhotoUrl(name);
//        //获取resources文件夹的绝对地址
//        String sourcePath = ClassUtils.getDefaultClassLoader().getResource("static/img/"+photoUrl).getPath();
////        System.out.println(sourcePath);
//        FileInputStream fis = null;
//        OutputStream os = null ;
//        fis = new FileInputStream(sourcePath);
//        // 得到文件大小
//        int size = fis.available();
//        byte data[] = new byte[size];
//        // 读数据
//        fis.read(data);
//        fis.close();
////        fis = null;
//        // 设置返回的文件类型
//        String fileType=getFileType(sourcePath);
////        System.out.println("................."+fileType);
//        response.setContentType(fileType);
//        os = response.getOutputStream();
//        os.write(data);
//        os.flush();
//        os.close();
////        os = null;
//        return null;
//    }


}

