package org.example.filedrop.services;

import org.example.filedrop.models.FileData;
import org.example.filedrop.repositories.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileDataService {

    private final FileDataRepository fileDataRepository;
    private static final String UPLOAD_DIR = "D://Pulpit/odebrane/";
    @Autowired
    public FileDataService(FileDataRepository fileDataRepository){
        this.fileDataRepository = fileDataRepository;
    }

    public void storeFile(MultipartFile file) throws IOException {
        File destinationFile = new File(UPLOAD_DIR + file.getOriginalFilename());

        // Zapis pliku na dysk
        file.transferTo(destinationFile);

        // Tworzenie obiektu FileData i zapis do bazy
        FileData fileData = new FileData();
        fileData.setFileName(file.getOriginalFilename());
        fileData.setFilePath(destinationFile.getPath());
        fileData.setFileSize(file.getSize());

        fileDataRepository.save(fileData);
    }

    public List<FileData> getAllFileData() {
        return fileDataRepository.findAll();
    }
}
