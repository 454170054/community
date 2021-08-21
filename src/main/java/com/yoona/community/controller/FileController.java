package com.yoona.community.controller;

import com.yoona.community.dto.FileDTO;
import com.yoona.community.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;


@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        if(file != null){
            return fileService.saveFile(file);
        }else {
            return null;
        }
    }

    @GetMapping("/images/upload/{path}")
    public ResponseEntity<FileSystemResource> getFile(@PathVariable("path") String fileName){
        return fileService.getImage(fileName);
    }
}
