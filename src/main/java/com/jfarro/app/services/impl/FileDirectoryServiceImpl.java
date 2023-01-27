package com.jfarro.app.services.impl;

import com.jfarro.app.services.FileDirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileDirectoryServiceImpl implements FileDirectoryService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Resource loadFile(String filename, String directory) throws MalformedURLException {
        Path absolutePath = Paths.get(directory).resolve(filename).toAbsolutePath();
        Resource resource = new UrlResource(absolutePath.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("ERROR: No se pudo leer el recurso del archivo: ".concat(filename));
        }
        return resource;
    }

    @Override
    public String copyFile(MultipartFile multipartFile, String directory) throws IOException {
        String uniqueFilename = UUID.randomUUID().toString().concat("_".concat(multipartFile.getOriginalFilename()));
        Path absolutePath = Paths.get(directory).resolve(uniqueFilename).toAbsolutePath();
        log.info("ABSOLUTE PATH: " + absolutePath);
        Files.copy(multipartFile.getInputStream(), absolutePath);
        return uniqueFilename;
    }

    @Override
    public boolean deleteFile(String filename, String directory) {
        Path absolutePath = Paths.get(directory).resolve(filename).toAbsolutePath();
        File file = absolutePath.toFile();
        if (file.exists() && file.canRead()) {
            return file.delete();
        }
        return false;
    }
}
