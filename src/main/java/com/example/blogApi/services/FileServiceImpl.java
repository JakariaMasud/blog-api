package com.example.blogApi.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        log.info("name of the file is " + name);
        String randomId = UUID.randomUUID().toString();
        String fileExt = name.substring(name.lastIndexOf("."));
        String fileName = randomId.concat(fileExt);
        String filePath = path + File.separator + fileName;
        File f = new File(path);
        if (!f.exists()) {
            log.info("folder is not exist ");
            f.mkdir();
        }
        log.info("File is uploading");
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
