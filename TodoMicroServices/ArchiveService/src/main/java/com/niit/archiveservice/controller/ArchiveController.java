package com.niit.archiveservice.controller;

import com.niit.archiveservice.domain.Archive;
import com.niit.archiveservice.exception.TaskAlreadyExistsException;
import com.niit.archiveservice.exception.TaskNotFoundException;
import com.niit.archiveservice.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class ArchiveController {

    private ArchiveService archiveService;
    private ResponseEntity responseEntity;

    @Autowired
    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @GetMapping("archive/{username}")
    public ResponseEntity taskLists(@PathVariable("username") String username)
    {
        List<Archive> list=archiveService.getTask(username);
        responseEntity = new ResponseEntity(list, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("archiveTask/{username}")
    public ResponseEntity saveTask(@RequestBody Archive archive,@PathVariable("username") String username) throws TaskAlreadyExistsException {
        Archive task=archiveService.saveTask(archive,username);
        return new ResponseEntity("Task Added",HttpStatus.CREATED);
    }

    @DeleteMapping("archive/{email}/{taskId}")
    public ResponseEntity deleteTask(@PathVariable ("taskId") String taskId,@PathVariable("email") String email) throws TaskNotFoundException {
        try {
            archiveService.deleteTask(taskId,email);
            responseEntity = new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
        } catch (TaskNotFoundException e) {
          throw new TaskNotFoundException();
        }
        return responseEntity;
    }

}
