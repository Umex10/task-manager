package com.devtiro.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.repositories.TaskListRepository;
import com.devtiro.tasks.services.TaskListService;

@Service
public class TaskListServiceImpl implements TaskListService {

  private final TaskListRepository taskListRepository;

  public TaskListServiceImpl(TaskListRepository taskListRepository) {
    this.taskListRepository = taskListRepository;
  }

  @Override
  public List<TaskList> listTaskLists() {
    return taskListRepository.findAll();
  }

  @Override
  public TaskList createTaskList(TaskList taskList) {
    // It would be not good, when client is sending an id, since we are creating it
    // internally. Needed to make sure that we accidentaly do not an update on that
    // id

    if (null != taskList.getId()) {
      throw new IllegalArgumentException("Task list has an ID! No need to add one");
    }

    if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
      throw new IllegalArgumentException("Task list title must be there");
    }

    LocalDateTime now = LocalDateTime.now();

    return taskListRepository.save(new TaskList(
        null,
        taskList.getTitle(),
        taskList.getDescription(),
        null,
        now,
        now));

  }

  @Override
  public Optional<TaskList> getTaskList(UUID id) {
    return taskListRepository.findById(id);
  }

  @Transactional
  @Override
  public TaskList updateTaskList(UUID taskListId, TaskList tasklist) {
    if (null == tasklist.getId()) {
      throw new IllegalArgumentException("Task list must have an ID");
    }

    // To ensure that the taskList id from the url
    // matches with the body tasklist id, so nobody can manipulate
    // the put request
    if (!Objects.equals(taskListId, tasklist.getId())) {
      throw new IllegalArgumentException("Attempting to change task list ID, you cannot do that");
    }

    TaskList existingTaskList = taskListRepository.findById(taskListId)
        .orElseThrow(() -> new IllegalArgumentException("Task list not found!"));

    existingTaskList.setTitle(tasklist.getTitle());
    existingTaskList.setDescription(tasklist.getDescription());
    existingTaskList.setUpdated(LocalDateTime.now());

    return taskListRepository.save(existingTaskList);
  }

  @Override
  public void deleteTaskList(UUID tasklistId) {
      taskListRepository.deleteById(tasklistId);
  }

}
