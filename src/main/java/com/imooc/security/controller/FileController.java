package com.imooc.security.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping
    //这里传入一个MultipartFile对象
    //这种Multipart适合在一个表单里上传文件，也就是最通用的清情况
    public FileInfo upload(MultipartFile file) throws Exception {

        //打印这个对象的内容看看
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        String path = "D:\\Coding\\imooc-security\\src\\main\\java\\com\\imooc\\security\\controller\\";

        File localfile = new File(path, new Date().getTime() + ".txt");

        file.transferTo(localfile);

        return new FileInfo(localfile.getAbsolutePath());
    }

    //由于是下载文件，不能返回响应，只能设置响应头然后写响应流
    @GetMapping("/{id:\\d+}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filename = "D:\\Coding\\imooc-security\\src\\main\\java\\com\\imooc\\security\\controller\\" + id + ".txt";
        //文件输入流。设置头部信息很重要，和一般的响应不同
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment;filename=mytest.txt");
        try {
            //JDK 7 新加入功能，在try语句中打开输入输出流，会自动关闭
            InputStream inputStream = new FileInputStream(filename);
            OutputStream outputStream = response.getOutputStream();
            //向响应里写输出流
            //使用commons.io库把输入流复制到输出流，否则需要手工复制
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}














