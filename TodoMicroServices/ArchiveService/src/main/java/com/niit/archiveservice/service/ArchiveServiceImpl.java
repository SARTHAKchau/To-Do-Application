package com.niit.archiveservice.service;

import com.niit.archiveservice.domain.Archive;
import com.niit.archiveservice.exception.TaskAlreadyExistsException;
import com.niit.archiveservice.exception.TaskNotFoundException;
import com.niit.archiveservice.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArchiveServiceImpl implements ArchiveService {

    private ArchiveRepository archiveRepository;

    @Autowired
    public ArchiveServiceImpl(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Override
    public Archive saveTask(Archive archive,String username)  {
        archive.setUsername(username);
        return archiveRepository.save(archive);
    }

    @Override
    public List<Archive> getTask(String username) {
        return archiveRepository.findByUsername(username);
    }

    @Override
    public boolean deleteTask(String taskId,String username) throws TaskNotFoundException {
        System.out.println("hi");
        boolean flag = false;
        if(archiveRepository.findByTaskIdAndUsername(taskId,username).isEmpty())
        {
            throw new TaskNotFoundException();
        }
        else {
            System.out.println(taskId);
           archiveRepository.deleteById(taskId);
            flag = true;
        }
        return flag;
    }

}
