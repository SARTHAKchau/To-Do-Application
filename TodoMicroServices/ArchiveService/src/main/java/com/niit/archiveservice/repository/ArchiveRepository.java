package com.niit.archiveservice.repository;

import com.niit.archiveservice.domain.Archive;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveRepository extends MongoRepository<Archive,String> {

    List<Archive> findByUsername(String username);

    String findByTaskIdAndUsername(String taskId, String username);
}
