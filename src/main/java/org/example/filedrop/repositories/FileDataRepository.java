package org.example.filedrop.repositories;

import org.example.filedrop.models.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDataRepository extends JpaRepository<FileData,Long> {
}
