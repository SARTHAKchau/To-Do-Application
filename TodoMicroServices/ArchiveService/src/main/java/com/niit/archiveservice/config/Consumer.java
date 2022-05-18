package com.niit.archiveservice.config;

import com.niit.archiveservice.domain.Archive;
import com.niit.archiveservice.exception.TaskAlreadyExistsException;
import com.niit.archiveservice.exception.TaskNotFoundException;
import com.niit.archiveservice.service.ArchiveServiceImpl;
import com.niit.rabbitmq.domain.ArchiveDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private ArchiveServiceImpl archiveService;

    @RabbitListener(queues = "archive_task_queue")
    public Object getArchiveDtoFromRabbitMq(ArchiveDTO archiveDTO) {
        Archive archive=new Archive();
        archive.setTaskId(archiveDTO.getTaskId());
        archive.setTitle(archiveDTO.getTitle());
        archive.setDescription(archiveDTO.getDescription());
        archive.setCategory(archiveDTO.getCategory());
        archive.setStartDate(archiveDTO.getStartDate());
        archive.setEndDate(archiveDTO.getEndDate());
        archive.setUsername(archiveDTO.getUsername());
        archive.setPriority(archiveDTO.getPriority());
        Object o=archiveService.saveTask(archive,archiveDTO.getUsername());
        return o;
    }

    @RabbitListener(queues = "get_archive_tasks")
    public Object getAllArchByUsername(String username){
        System.out.println("inside consumer "+username);
        return archiveService.getTask(username);
    }

    @RabbitListener(queues = "del_archive_queue")
    public Object deleteByIdAndEmail(ArchiveDTO archiveDTO) throws TaskNotFoundException {
        String username=archiveDTO.getUsername();
        String taskId=archiveDTO.getTaskId();
        System.out.println(username+taskId);
        return archiveService.deleteTask(taskId,username);
    }
}
