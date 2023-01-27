package com.jfarro.app.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FileDirectoryService {
    Resource loadFile(String filename, String directory) throws MalformedURLException;
    String copyFile(MultipartFile multipartFile, String directory) throws IOException;
    boolean deleteFile(String filename, String directory);
}
