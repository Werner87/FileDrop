package org.example.filedrop.controllers;

import org.example.filedrop.models.FileData;
import org.example.filedrop.services.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class FileDataController {

    private  final FileDataService fileDataService;

    @Autowired
    public FileDataController(FileDataService fileDataService){
        this.fileDataService = fileDataService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileDataService.storeFile(file);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }
    @GetMapping("/files")
    public List<FileData> getAllFiles() {
        return fileDataService.getAllFileData();
    }
}
