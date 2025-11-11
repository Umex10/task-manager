package com.devtiro.tasks.domain.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter

// This is a standalone task. In this exact class, we will also create the table for it.
// Since we exactly need the same columns = fields of a single Task. 

public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "due_date")
  private LocalDateTime dueDate;

  @Column(name = "status", nullable = false)
  private TaskStatus status;

  @Column(name = "priority", nullable = false)
  private TaskPriority priority;

  // LAZY will ensure that taskList only gets loaded, when I need it
  @ManyToOne(fetch = FetchType.LAZY)
  // foreign key
  @JoinColumn(name = "task_list_id")
  private TaskList taskList;
  
  @Column(name = "created", nullable = false)
  private LocalDateTime created;

  @Column(name = "updated", nullable = false)
  private LocalDateTime updated;

}
