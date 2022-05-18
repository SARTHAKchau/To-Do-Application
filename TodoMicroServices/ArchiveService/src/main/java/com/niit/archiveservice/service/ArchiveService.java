package com.niit.archiveservice.service;

import com.niit.archiveservice.domain.Archive;
import com.niit.archiveservice.exception.TaskAlreadyExistsException;
import com.niit.archiveservice.exception.TaskNotFoundException;

import java.util.List;

public interface ArchiveService {
    Archive saveTask(Archive archive,String username);

    List<Archive> getTask(String email);

    boolean deleteTask(String taskId,String email) throws TaskNotFoundException;
}
