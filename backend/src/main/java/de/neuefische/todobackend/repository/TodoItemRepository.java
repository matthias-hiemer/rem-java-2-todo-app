package de.neuefische.todobackend.repository;

import de.neuefische.todobackend.model.TodoItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    List<TodoItem> findByStatus(String status);
}
