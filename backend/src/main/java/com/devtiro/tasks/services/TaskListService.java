package com.devtiro.tasks.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.devtiro.tasks.domain.entities.TaskList;

public interface TaskListService {

  List<TaskList> listTaskLists();
  TaskList createTaskList(TaskList taskList);
  Optional<TaskList> getTaskList(UUID id);
  TaskList updateTaskList(UUID taskListId, TaskList tasklist);
  void deleteTaskList(UUID tasklistId);
}
