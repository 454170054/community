package com.yoona.community.service;


import com.yoona.community.dto.FileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService {
    private final Logger logger = LoggerFactory.getLogger("com.yoona.community");

    public FileDTO saveFile(MultipartFile file){
        assert file != null;
        String[] fileSeg = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String dir = "E:/springboot/community/src/main/resources/static/images/upload/";
        String fileName = UUID.randomUUID().toString() + '.' + fileSeg[fileSeg.length - 1];
        File newFile = new File(dir + fileName);
        FileDTO fileDTO = new FileDTO();
        try {
            file.transferTo(newFile);
            fileDTO.setMessage("上传成功");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            fileDTO.setMessage("上传失败");
        }
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/upload/" + fileName);
        return fileDTO;
    }

    public ResponseEntity<FileSystemResource> getImage(String fileName) {
        String filePath = "E:/springboot/community/src/main/resources/static/images/upload";
        File file = new File(filePath, fileName);
        if (file.exists()) {
            return export(file);
        }
        return null;
    }

    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }
}
